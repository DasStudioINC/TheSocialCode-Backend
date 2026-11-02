package com.yurphagames.thesocialcode.usermanagement.datamappinglayer;

import com.yurphagames.thesocialcode.usermanagement.domainlayer.*;
import com.yurphagames.thesocialcode.usermanagement.presentationlayer.models.UserRequetsModel;
import com.yurphagames.thesocialcode.usermanagement.presentationlayer.models.UserResponseModel;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.lang.reflect.Member;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class UserMapper {
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ISO_LOCAL_DATE;


    public UserResponseModel entityToResponseModel(User user){
        if(user == null) return null;

        UserResponseModel responseModel = new UserResponseModel();

        responseModel.setUserHandle(
                user.getUserHandle() == null
                ? null : String.valueOf(user.getUserHandle().getUserHandle())
        );

        if(user.getUserName() != null){
            responseModel.setUserName(user.getUserName().getUsername());
        }

        if(user.getUserInfo() != null){
            responseModel.setFirstName(user.getUserInfo().getFirstName());
            responseModel.setLastName(user.getUserInfo().getLastName());
        }

        if(user.getEmail() != null){
            responseModel.setEmail(user.getEmail().getEmail());
        }

        if(user.getUserInfo().getDescription() != null){
            responseModel.setDescription(user.getUserInfo().getDescription());
        }

        if(user.getSubscriptionType() != null){
            responseModel.setSubscriptionType(
                    user.getSubscriptionType().name()
            );
        }

        if(user.getCreationDate() != null){
            responseModel.setCreationDate(
                    user.getCreationDate().format(DATE_FORMAT)
            );
        }

        if(user.getAccountData() != null){
            responseModel.setFollowerCount(user.getAccountData().getFollowerCount());
            responseModel.setPostCount(user.getAccountData().getPostCount());
        }

        return responseModel;
    }

    public List<UserResponseModel> entityToResponseModelList(List<User> users){
        if(users == null) return List.of();

        return users.stream().map(this::entityToResponseModel).collect(Collectors.toList());
    }

    public User requestModelToEntity(UserRequetsModel requetsModel){
        if(requetsModel == null) return null;

        User user = new User();

        user.setUserHandle(new UserHandle(requetsModel.getUserHandle()));
        user.setUserName(new UserName(requetsModel.getUserName()));
        user.setEmail(new EmailAddress(requetsModel.getEmail()));
        user.setUserInfo(new UserInfo(
                requetsModel.getFirstName(),
                requetsModel.getLastName(),
                requetsModel.getDescription(),
                requetsModel.getPassword()
        ));

        user.setSubscriptionType(requetsModel.getType());

        return user;
    }

    public void updateEntityFromRequestModel(
            @NotNull UserRequetsModel requetsModel,
            @NotNull User user
    ){
        user.setUserHandle(new UserHandle(requetsModel.getUserHandle()));
        user.setUserName(new UserName(requetsModel.getUserName()));
        user.setEmail(new EmailAddress(requetsModel.getEmail()));
        user.setUserInfo(new UserInfo(
                requetsModel.getFirstName(),
                requetsModel.getLastName(),
                requetsModel.getDescription(),
                requetsModel.getPassword()
        ));

        user.setSubscriptionType(requetsModel.getType());
    }
}
