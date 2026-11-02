import argparse
import re
import os

import torch
from PIL import Image
from transformers import CLIPTokenizer, CLIPTextModel

from diffusion_core import (
    SimpleConditionalUNet,
    sample_reverse_diffusion
)

import model as model_params


def generate():
    parser = argparse.ArgumentParser(
        description="Generate an image from a text prompt."
    )

    parser.add_argument(
        "--prompt",
        type=str,
        default="a cow riding a horse",
        help="The text prompt to guide image generation."
    )

    args = parser.parse_args()

    device = torch.device(
        "cuda" if torch.cuda.is_available() else "cpu"
    )

    print(f"Using inference device: {device}")

    print(
        f"Encoding text prompt: "
        f"'{args.prompt}'..."
    )

    tokenizer = CLIPTokenizer.from_pretrained(
        "openai/clip-vit-base-patch32"
    )

    text_encoder = CLIPTextModel.from_pretrained(
        "openai/clip-vit-base-patch32"
    ).to(device)

    text_encoder.eval()

    text_inputs = tokenizer(
        args.prompt,
        padding=True,
        truncation=True,
        max_length=77,
        return_tensors="pt"
    ).to(device)

    with torch.no_grad():
        text_embeddings = text_encoder(
            **text_inputs
        ).pooler_output

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
            f"'{model_params.model_name}' not found. "
            f"Run training first."
        )
        return

    image_shape = model_params.image_shape
    timesteps = model_params.gen_timesteps

    print(
        "Running text-conditioned "
        "reverse sampling loop..."
    )

    generated_tensor = sample_reverse_diffusion(
        model,
        image_shape,
        text_embeddings,
        timesteps=timesteps
    )

    img_tensor = generated_tensor.squeeze(0).cpu()

    img_tensor = (
        img_tensor + 1.0
    ) / 2.0

    img_tensor = torch.clamp(
        img_tensor,
        0.0,
        1.0
    )

    img_tensor = (
        img_tensor * 255
    ).byte().permute(
        1,
        2,
        0
    ).numpy()

    output_image = Image.fromarray(
        img_tensor
    )

    generation_number = (
        model_params.image_Gen_Count()
    )

    safe_name = re.sub(
        r'[^a-zA-Z0-9]',
        '_',
        args.prompt
    ).strip('_')

    safe_name = (
        safe_name[:20]
        if len(safe_name) > 0
        else "generated"
    )

    output_filename = (
        f"{safe_name}"
        f"#{generation_number}.png"
    )

    models_dir = "./model_storage"

    os.makedirs(
        models_dir,
        exist_ok=True
    )

    output_path = os.path.join(
        models_dir,
        output_filename
    )

    output_image.save(
        output_path
    )

    output_image.save(
        output_filename
    )

    print(
        f"Finished creating image: "
        f"{output_path}"
    )


if __name__ == "__main__":
    generate()