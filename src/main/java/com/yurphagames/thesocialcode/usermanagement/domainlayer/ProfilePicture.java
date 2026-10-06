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
public class ProfilePicture {

    @Column(name = "profile_picture")
    private String path_to_profile_picture;

    public ProfilePicture(String path) {
        if (path == null || path.isBlank()) {
            this.path_to_profile_picture = "path to default profile picture!";
        } else {
            this.path_to_profile_picture = path;
        }
    }
}