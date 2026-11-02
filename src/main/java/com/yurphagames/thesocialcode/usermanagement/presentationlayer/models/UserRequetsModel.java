package com.yurphagames.thesocialcode.usermanagement.presentationlayer.models;

import com.yurphagames.thesocialcode.usermanagement.domainlayer.EmailAddress;
import com.yurphagames.thesocialcode.usermanagement.domainlayer.SubscriptionType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequetsModel {

    @NotBlank
    private String userHandle;

    @NotBlank
    private String userName;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    @NotBlank
    private String password;

    @NotNull
    private String description;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private SubscriptionType type;
}
