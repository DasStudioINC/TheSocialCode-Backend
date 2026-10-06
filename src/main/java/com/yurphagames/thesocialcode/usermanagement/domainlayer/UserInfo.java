package com.yurphagames.thesocialcode.usermanagement.domainlayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
   @NotBlank(message = "First name is REQUIRED!")
   @Column(name = "first_name")
   private String firstName;
   @NotBlank(message = "Last name is REQUIRED!")
   @Column(name = "last_name")
   private String lastName;

   @NotBlank(message = "You must enter a PASSWORD!")
   @Column(name = "password")
   private String password;
}
