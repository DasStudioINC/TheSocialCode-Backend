package com.yurphagames.thesocialcode.postmanagement.presentationlayer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseModel {
    private String userHandle;
    private String postName;
    private int viewCount;
    private int likeCount;
    private String postDate;
}
