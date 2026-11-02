package com.yurphagames.thesocialcode.usermanagement.presentationlayer.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseModel {
    private String userHandle;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String subscriptionType;
    private String creationDate;
    private String description;
    private int followerCount;
    private int postCount;
}
