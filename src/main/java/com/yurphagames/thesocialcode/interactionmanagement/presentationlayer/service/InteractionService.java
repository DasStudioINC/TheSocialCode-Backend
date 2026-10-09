package com.yurphagames.thesocialcode.interactionmanagement.presentationlayer.service;

import com.yurphagames.thesocialcode.interactionmanagement.domainlayer.Interaction;
import com.yurphagames.thesocialcode.interactionmanagement.domainlayer.InteractionType;
import com.yurphagames.thesocialcode.interactionmanagement.infrustructurelayer.repository.InteractionRepository;
import com.yurphagames.thesocialcode.postmanagement.domainlayer.Post;
import com.yurphagames.thesocialcode.postmanagement.presentationlayer.service.PostService;
import com.yurphagames.thesocialcode.usermanagement.domainlayer.User;
import com.yurphagames.thesocialcode.usermanagement.presentationlayer.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class InteractionService implements InteractionServiceInterface{
    private final InteractionRepository interactionRepository;
    private final UserService userService;
    private final PostService postService;

    public InteractionService(InteractionRepository repository, UserService userService, PostService postService){
        this.interactionRepository = repository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Interaction> getAll(){
        if(interactionRepository.findAll().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No interactions to get");
        }

        return interactionRepository.findAll();
    }

    public void Follow(String Iuserhandle, String Puserhandle){
        if(Iuserhandle == null || Iuserhandle.isEmpty() || Puserhandle == null || Puserhandle.isEmpty())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You must input a userHandle for both interactor and poster");

        Iuserhandle = Iuserhandle.trim();
        User Iuser = userService.getUserByUserHandle(Iuserhandle);

        Puserhandle = Puserhandle.trim();
        User Puser = userService.getUserByUserHandle(Puserhandle);
        // A follow can only be done if you have not followed already
        // if you have it should unfollow


        // We should also check if they are the same
        // because I do not want you to have the ability
        // to follow yourself

        if(Iuserhandle.equals(Puserhandle))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Cannot Follow Yourself");



        Interaction interaction = interactionRepository.findByUserHandleInteractedAndUserHandlePosterAndType(Iuserhandle, Puserhandle, InteractionType.FOLLOW);
        if(interaction == null){
            // if we are in this if statement
            // it means that we have no data
            // for this relation

            // So we can create one and execute the addition
            // of the following count

            interaction = new Interaction(Iuserhandle, Puserhandle,Puser, InteractionType.FOLLOW);
            interaction.Follow();
            interactionRepository.save(interaction);
            userService.save(Puser);

            throw new ResponseStatusException(HttpStatus.OK, "Success (You followed: " + Puserhandle + ") and relation created!");
        }

        // If we are here that means that there is
        // a relation with these to users
        // so we need to remove the follow from
        // that user and delete that relation
        // to allow a future refollow

        interaction.UnFollow();
        interactionRepository.delete(interaction);
        userService.save(Puser);
        throw new ResponseStatusException(HttpStatus.OK, "Success (You un followed: " + Puserhandle + ") and relation deleted!");
    }

    public void Like(String Iuserhandle, String Puserhandle, String Ppostname){
        // Even though the method I will call
        // in post service returns a list of posts,
        // You will need to have the parameters for it
        // and those params will indeed return a specific
        // video from that use and that is their
        // userHandle and their postName

        Iuserhandle = Iuserhandle.trim();
        Puserhandle = Puserhandle.trim();
        Ppostname = Ppostname.trim();

        if(Iuserhandle.isEmpty())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You must enter a userHandle for the interactor");

        if(Puserhandle.isEmpty())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You must enter a userHandle for the poster");

        if(Ppostname.isEmpty())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You must enter a post name");


        Interaction interaction = interactionRepository.findByUserHandleInteractedAndUserHandlePosterAndType(Iuserhandle, Puserhandle, InteractionType.LIKE);

        Post post = postService.getPostByParams(Puserhandle, Ppostname, 1).get(0);

        if(interaction == null){
            // this time I need to get the post not the user

            interaction = new Interaction(Iuserhandle, Puserhandle, post, InteractionType.LIKE);

            interaction.like();
            interactionRepository.save(interaction);
            postService.save(post);
            throw new ResponseStatusException(HttpStatus.OK, "Success you liked the post called (" + Ppostname + ") by poster (" + Puserhandle + ") and relation created!");
        }

        interaction.dislike();
        interactionRepository.delete(interaction);
        postService.save(post);
        throw new ResponseStatusException(HttpStatus.OK, "Success you disliked the post called (" + Ppostname + ") by poster (" + Puserhandle + ") and relation deleted!");
    }
}
