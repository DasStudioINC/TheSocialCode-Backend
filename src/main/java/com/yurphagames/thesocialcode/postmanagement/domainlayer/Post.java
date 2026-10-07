package com.yurphagames.thesocialcode.postmanagement.domainlayer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post",
        uniqueConstraints = {
            @UniqueConstraint(
                    columnNames = {"username", "post_number"}
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

    @Column(nullable = false)
    private String username;

    @Column(name = "post_number", nullable = false)
    private int postNumber;

    @Column(unique = true)
    private String path;

    public Post(int postNumber, String username, String path){
        this.postNumber = postNumber;
        this.username = username;
        this.path = path;
    }

}
