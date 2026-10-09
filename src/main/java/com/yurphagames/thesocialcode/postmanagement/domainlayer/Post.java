package com.yurphagames.thesocialcode.postmanagement.domainlayer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post",
        uniqueConstraints = {
            @UniqueConstraint(
                    columnNames = {"userhandle", "post_number"}
            )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userhandle", nullable = false)
    private String userHandle;

    @Column(name = "post_number", nullable = false)
    private int postNumber;

    @Embedded
    private PostDirectories postDirectories;

    @Embedded
    private PostData postData;

    public Post(int postNumber, String userHandle, PostDirectories postDirectories){
        this.postNumber = postNumber;
        this.userHandle = userHandle;
        this.postDirectories = postDirectories;
        this.postData = new PostData();
    }

}
