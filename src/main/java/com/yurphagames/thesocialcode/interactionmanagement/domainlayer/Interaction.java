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


    @Enumerated(EnumType.STRING)
    private InteractionType type;



    public Interaction(String userHandleInteracted, String userHandlePoster, String content, InteractionType type){
        this.userHandleInteracted = userHandleInteracted;
        this.userHandlePoster = userHandlePoster;
        this.content = content;
        this.type = type;
    }

    public Interaction(String userHandleInteracted, String userHandlePoster, InteractionType type){
        this.userHandleInteracted = userHandleInteracted;
        this.userHandlePoster = userHandlePoster;
        this.type = type;
        this.content = "";
    }



}

