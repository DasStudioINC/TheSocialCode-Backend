package com.yurphagames.thesocialcode.postmanagement.presentationlayer.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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


    @NotNull
    private String description;

    @NotBlank
    private int viewCount;

    @NotBlank
    private int likeCount;
}
