package com.yurphagames.thesocialcode.postmanagement.domainlayer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

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


    @Column(name = "searchTitle", nullable = false, unique = true)
    private String searchTitle;

    @Embedded
    private PostData postData;

    @Column(name = "posted_At", nullable = false, unique = false )
    private LocalDate postDate;

    public Post(int postNumber, String userHandle, PostDirectories postDirectories){
        this.postNumber = postNumber;
        this.userHandle = userHandle;
        this.postDirectories = postDirectories;
        this.postData = new PostData();
        this.postDate = LocalDate.now();
        this.searchTitle = "";
    }

    // this creates a unique search index for you to use
    // it is what will be used to search instead of
    // the actual id of the internal post id
    //
    public void generateSearchTitle() {
        this.searchTitle = LocalDate.now()
                .atStartOfDay()
                .toString()
                + userHandle + postNumber
                + UUID.randomUUID().toString().substring(0, 5);
    }

}
