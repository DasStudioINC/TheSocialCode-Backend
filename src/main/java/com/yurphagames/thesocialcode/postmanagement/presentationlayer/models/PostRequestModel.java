package com.yurphagames.thesocialcode.postmanagement.presentationlayer.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestModel {

    @NotBlank
    private String userHandle;

    @NotBlank
    private String postName;

    @NotBlank
    private int viewCount;

    @NotBlank
    private int likeCount;
}
