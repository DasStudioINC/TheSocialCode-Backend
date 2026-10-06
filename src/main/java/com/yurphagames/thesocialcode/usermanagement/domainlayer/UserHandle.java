package com.yurphagames.thesocialcode.usermanagement.domainlayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Setter
@Getter
@NoArgsConstructor
public class UserHandle {

    @Column(name = "user_handle", unique = true, nullable = false)
    private String userHandle;

    public UserHandle(String handle){
        this.userHandle = handle;
    }
}
