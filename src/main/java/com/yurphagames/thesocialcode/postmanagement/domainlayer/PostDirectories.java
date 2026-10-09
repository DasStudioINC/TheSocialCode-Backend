package com.yurphagames.thesocialcode.postmanagement.domainlayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.C;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class PostDirectories {
    @Column(name = "post_path", nullable = false, unique = true)
    private String postPath;
    @Column(name = "interaction_path", nullable = false, unique = true)
    private String interactionPath;

    public PostDirectories(String pp, String ip){
        this.postPath = pp;
        this.interactionPath = ip;
    }
}
