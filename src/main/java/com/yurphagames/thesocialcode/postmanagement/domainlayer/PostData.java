package com.yurphagames.thesocialcode.postmanagement.domainlayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class PostData {
    @Column(name = "like_count", nullable = false, unique = false)
    private int likeCount;
    @Column(name = "view_count", nullable = false, unique = false)
    private int viewCount;


    public PostData(int like, int view){
        this.likeCount = like;
        this.viewCount = view;
    }
}
