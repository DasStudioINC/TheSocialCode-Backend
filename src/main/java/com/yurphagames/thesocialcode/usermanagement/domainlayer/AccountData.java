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
public class AccountData {
    @Column(name = "follower_count")
    private int followerCount;
    @Column(name = "post_count")
    private int postCount;
}
