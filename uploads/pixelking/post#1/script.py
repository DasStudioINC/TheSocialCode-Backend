import argparse
import os

import torch
from PIL import Image

from transformers import CLIPTokenizer, CLIPTextModel

from diffusion_core import (
    SimpleConditionalUNet,
    get_diffusion_schedule
)

import model as model_params


@torch.no_grad()
def sample_image_to_image(
    model,
    starting_image,
    text_embeddings,
    strength,
    timesteps
):
    """
    Takes an existing image, adds controlled diffusion noise,
    then uses the diffusion model to generate a modified image.
    """

    device = next(model.parameters()).device

    betas, alphas, alphas_cumprod = (
        get_diffusion_schedule(timesteps)
    )

    betas = betas.to(device)
    alphas = alphas.to(device)
    alphas_cumprod = alphas_cumprod.to(device)

    strength = max(
        0.0,
        min(1.0, strength)
    )

    start_timestep = int(
        strength * (timesteps - 1)
    )

    if start_timestep <= 0:
        return torch.clamp(
            starting_image,
            -1.0,
            1.0
        )

    # --------------------------------------------------
    # FORWARD DIFFUSION
    # --------------------------------------------------

    t = torch.tensor(
        [start_timestep],
        dtype=torch.long,
        device=device
    )

    alpha_bar = alphas_cumprod[t].view(
        -1,
        1,
        1,
        1
    )

    noise = torch.randn_like(
        starting_image
    )

    img = (
        torch.sqrt(alpha_bar)
        * starting_image
        +
        torch.sqrt(1.0 - alpha_bar)
        * noise
    )

    # --------------------------------------------------
    # REVERSE DIFFUSION
    # --------------------------------------------------

    model.eval()

    for i in reversed(
        range(start_timestep + 1)
    ):

        t = torch.full(
            (1,),
            i,
            dtype=torch.long,
            device=device
        )

        predicted_noise = model(
            img,
            t,
            text_embeddings
        )

        alpha = alphas[i]
        alpha_bar = alphas_cumprod[i]
        beta = betas[i]

        if i > 0:
            noise = torch.randn_like(img)
        else:
            noise = torch.zeros_like(img)

        img = (
            1.0 / torch.sqrt(alpha)
        ) * (
            img
            -
            (
                (1.0 - alpha)
                /
                torch.sqrt(
                    1.0 - alpha_bar
                )
            )
            * predicted_noise
        ) + torch.sqrt(beta) * noise

    return torch.clamp(
        img,
        -1.0,
        1.0
    )


def load_image(
    image_path,
    image_shape,
    device
):
    """
    Loads an image and converts it into the
    same [-1, 1] format used during training.
    """

    image = Image.open(
        image_path
    ).convert("RGB")

    width = image_shape[2]
    height = image_shape[1]

    image = image.resize(
        (width, height),
        Image.Resampling.BILINEAR
    )

    image_tensor = torch.from_numpy(
        __import__("numpy").array(image)
    ).float()

    image_tensor = (
        image_tensor / 255.0
    )

    image_tensor = image_tensor.permute(
        2,
        0,
        1
    )

    image_tensor = (
        image_tensor * 2.0
    ) - 1.0

    image_tensor = (
        image_tensor
        .unsqueeze(0)
        .to(device)
    )

    return image_tensor


def tensor_to_image(
    tensor
):
    """
    Converts a diffusion tensor from [-1,1]
    back into a PIL image.
    """

    image_tensor = (
        tensor
        .squeeze(0)
        .cpu()
    )

    image_tensor = (
        image_tensor + 1.0
    ) / 2.0

    image_tensor = torch.clamp(
        image_tensor,
        0.0,
        1.0
    )

    image_tensor = (
        image_tensor * 255
    ).byte()

    image_tensor = image_tensor.permute(
        1,
        2,
        0
    ).numpy()

    return Image.fromarray(
        image_tensor
    )


def encode_prompt(
    prompt,
    tokenizer,
    text_encoder,
    device
):
    """
    Converts the prompt into CLIP embeddings.
    """

    text_inputs = tokenizer(
        prompt,
        padding=True,
        truncation=True,
        max_length=77,
        return_tensors="pt"
    ).to(device)

    with torch.no_grad():

        text_embeddings = (
            text_encoder(
                **text_inputs
            ).pooler_output
        )

    return text_embeddings


def generate_video():

    parser = argparse.ArgumentParser(
        description=(
            "Generate a sequence of images "
            "using image-to-image diffusion."
        )
    )

    parser.add_argument(
        "--image",
        type=str,
        required=True,
        help="Starting image for the video."
    )

    parser.add_argument(
        "--prompt",
        type=str,
        required=True,
        help=(
            "Prompt describing the desired "
            "change or movement."
        )
    )

    parser.add_argument(
        "--strength",
        type=float,
        default=0.10,
        help=(
            "Amount of change between frames "
            "(0.0 - 1.0)."
        )
    )

    parser.add_argument(
        "--frames",
        type=int,
        default=30,
        help="Number of frames to generate."
    )

    parser.add_argument(
        "--name",
        type=str,
        required=True,
        help=(
            "Name of the folder where the "
            "generated frames will be stored."
        )
    )

    args = parser.parse_args()

    # --------------------------------------------------
    # VALIDATION
    # --------------------------------------------------

    if not os.path.exists(
        args.image
    ):
        print(
            f"Error: Image "
            f"'{args.image}' does not exist."
        )
        return

    if args.frames < 1:
        print(
            "Error: --frames must be at least 1."
        )
        return

    if args.strength < 0.0 or args.strength > 1.0:
        print(
            "Error: --strength must be "
            "between 0.0 and 1.0."
        )
        return

    if not args.name.strip():
        print(
            "Error: --name cannot be empty."
        )
        return

    # --------------------------------------------------
    # DEVICE
    # --------------------------------------------------

    device = torch.device(
        "cuda"
        if torch.cuda.is_available()
        else "cpu"
    )

    print(
        f"Using inference device: {device}"
    )

    # --------------------------------------------------
    # LOAD CLIP
    # --------------------------------------------------

    print(
        "Loading CLIP text encoder..."
    )

    tokenizer = CLIPTokenizer.from_pretrained(
        "openai/clip-vit-base-patch32"
    )

    text_encoder = CLIPTextModel.from_pretrained(
        "openai/clip-vit-base-patch32"
    ).to(device)

    text_encoder.eval()

    print(
        f"Encoding prompt: "
        f"'{args.prompt}'..."
    )

    text_embeddings = encode_prompt(
        args.prompt,
        tokenizer,
        text_encoder,
        device
    )

    # --------------------------------------------------
    # LOAD DIFFUSION MODEL
    # --------------------------------------------------

    print(
        "Loading diffusion model..."
    )

    model = SimpleConditionalUNet(
        in_channels=3,
        base_dims=model_params.gen_X_scale,
        time_dim=model_params.gen_Y_scale
    ).to(device)

    try:

        model.load_state_dict(
            torch.load(
                model_params.model_name,
                map_location=device
            )
        )

        print(
            f"Loaded "
            f"'{model_params.model_name}' successfully!"
        )

    except FileNotFoundError:

        print(
            f"Error: "
            f"'{model_params.model_name}' "
            f"was not found."
        )

        return

    # --------------------------------------------------
    # LOAD STARTING IMAGE
    # --------------------------------------------------

    image_shape = model_params.image_shape

    print(
        f"Loading starting image: "
        f"{args.image}"
    )

    current_frame = load_image(
        args.image,
        image_shape,
        device
    )

    # --------------------------------------------------
    # CREATE VIDEO FOLDER
    # --------------------------------------------------

    safe_name = args.name.strip()

    video_folder = os.path.join(
        "model_storage",
        safe_name
    )

    os.makedirs(
        video_folder,
        exist_ok=True
    )

    print(
        f"Creating {args.frames} frames..."
    )

    print(
        f"Strength: {args.strength}"
    )

    print(
        f"Output folder: {video_folder}"
    )

    # --------------------------------------------------
    # GENERATE FRAMES
    # --------------------------------------------------

    for frame_number in range(
        args.frames
    ):

        print(
            f"Generating frame "
            f"{frame_number + 1}"
            f"/{args.frames}..."
        )

        frame_image = tensor_to_image(
            current_frame
        )

        frame_path = os.path.join(
            video_folder,
            f"frame_{frame_number:04d}.png"
        )

        frame_image.save(
            frame_path
        )

        # The newly generated frame becomes
        # the source for the next frame.
        current_frame = sample_image_to_image(
            model,
            current_frame,
            text_embeddings,
            args.strength,
            model_params.gen_timesteps
        )

    print(
        "\nVideo frame generation complete!"
    )

    print(
        f"Frames saved to: "
        f"{video_folder}"
    )


if __name__ == "__main__":
    generate_video()