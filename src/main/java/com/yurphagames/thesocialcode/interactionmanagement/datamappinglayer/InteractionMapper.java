package com.yurphagames.thesocialcode.interactionmanagement.datamappinglayer;

import com.yurphagames.thesocialcode.interactionmanagement.domainlayer.Interaction;
import com.yurphagames.thesocialcode.interactionmanagement.presentationlayer.models.CommentInteractionResponseModel;
import com.yurphagames.thesocialcode.usermanagement.domainlayer.User;
import com.yurphagames.thesocialcode.usermanagement.presentationlayer.models.UserResponseModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InteractionMapper {


    public CommentInteractionResponseModel entityToResponseModel(Interaction interaction){
        if(interaction == null) return null;

        CommentInteractionResponseModel responseModel = new CommentInteractionResponseModel();

        if(!interaction.getUserHandleInteracted().isEmpty()){
            responseModel.setInteractedUserhandle(interaction.getUserHandleInteracted());
        }

        if(!interaction.getUserHandlePoster().isEmpty()){
            responseModel.setPosterUserhandle(interaction.getUserHandlePoster());
        }


        if(interaction.getType() != null){
            responseModel.setType(interaction.getType());
        }

        if(!interaction.getContent().isEmpty()){
            responseModel.setContent(interaction.getContent());
        }



        return responseModel;
    }

    public List<CommentInteractionResponseModel> entityToResponseModelList(List<Interaction> interactions){
        if(interactions == null) return List.of();

        return interactions.stream().map(this::entityToResponseModel).collect(Collectors.toList());
    }
}
