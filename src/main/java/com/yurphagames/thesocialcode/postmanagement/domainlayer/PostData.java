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
    @Column(name = "post_name", nullable = false, unique = false)
    private String postName;





    public PostData(int like, int view, String postName){
        this.likeCount = like;
        this.viewCount = view;
        this.postName = postName == null ? "Default Post Name" : postName;

    }


}
