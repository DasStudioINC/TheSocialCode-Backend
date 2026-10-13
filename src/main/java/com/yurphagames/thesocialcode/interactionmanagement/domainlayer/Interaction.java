package com.yurphagames.thesocialcode.interactionmanagement.domainlayer;

import com.yurphagames.thesocialcode.postmanagement.domainlayer.Post;
import com.yurphagames.thesocialcode.usermanagement.domainlayer.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "interactions")
@Getter
@Setter
@NoArgsConstructor
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userHandleInteracted", nullable = false, unique = false)
    private String userHandleInteracted;

    @Column(name = "userHandlePoster", nullable = false, unique = false)
    private String userHandlePoster;

    @Column(name = "content", nullable = false, unique = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Enumerated(EnumType.STRING)
    private InteractionType type;



    public Interaction(String userHandleInteracted, String userHandlePoster, String content, User user, Post post, InteractionType type){
        this.userHandleInteracted = userHandleInteracted;
        this.userHandlePoster = userHandlePoster;
        this.content = content;
        this.user = user;
        this.post = post;
        this.type = type;
    }

    public Interaction(String userHandleInteracted, String userHandlePoster, User user, InteractionType type){
        this.userHandleInteracted = userHandleInteracted;
        this.userHandlePoster = userHandlePoster;
        this.user = user;
        this.type = type;
        this.content = "";
    }

    public Interaction(String userHandleInteracted, String userHandlePoster, Post post, InteractionType type){
        this.userHandleInteracted = userHandleInteracted;
        this.userHandlePoster = userHandlePoster;
        this.post = post;
        this.type = type;
        this.content = "";
    }

    public Interaction(String userHandleInteracted, String userHandlePoster, Post post, String comment,InteractionType type){
        this.userHandleInteracted = userHandleInteracted;
        this.userHandlePoster = userHandlePoster;
        this.post = post;
        this.type = type;
        this.content = comment;
    }


    public int Follow(){
        user.getAccountData().setFollowerCount(user.getAccountData().getFollowerCount() + 1);
        return user.getAccountData().getFollowerCount();
    }

    public int UnFollow(){
        user.getAccountData().setFollowerCount(Math.max(user.getAccountData().getFollowerCount() - 1, 0));
        return user.getAccountData().getFollowerCount();
    }

    public int like(){
        post.getPostData().setLikeCount(post.getPostData().getLikeCount() + 1);
        return post.getPostData().getLikeCount();
    }

    public int dislike(){
        post.getPostData().setLikeCount(post.getPostData().getLikeCount() - 1);
        return post.getPostData().getLikeCount();
    }

    public int view(){
        post.getPostData().setViewCount(post.getPostData().getViewCount() + 1);
        return post.getPostData().getViewCount();
    }

}

