package com.yurphagames.thesocialcode.interactionmanagement.presentationlayer.models;


import com.yurphagames.thesocialcode.interactionmanagement.domainlayer.InteractionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentInteractionResponseModel {
    private InteractionType type;
    private String searchTitle;
    private String content;
    private String interactedUserhandle;
    private String posterUserhandle;
}
