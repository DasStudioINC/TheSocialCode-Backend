package com.yurphagames.thesocialcode.usermanagement.domainlayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class UserName {
    @NotBlank(message = "Username Cannot be left BLANK!")
    @Column(name = "user_name")
    private String username;
}
