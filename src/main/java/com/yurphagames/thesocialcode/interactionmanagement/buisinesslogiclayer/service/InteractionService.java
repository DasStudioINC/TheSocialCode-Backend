package com.yurphagames.thesocialcode.interactionmanagement.buisinesslogiclayer.service;

import com.yurphagames.thesocialcode.interactionmanagement.domainlayer.Interaction;
import com.yurphagames.thesocialcode.interactionmanagement.domainlayer.InteractionType;
import com.yurphagames.thesocialcode.interactionmanagement.infrustructurelayer.repository.InteractionRepository;
import com.yurphagames.thesocialcode.postmanagement.domainlayer.Post;
import com.yurphagames.thesocialcode.postmanagement.buisnesslogiclayer.service.PostService;
import com.yurphagames.thesocialcode.usermanagement.domainlayer.User;
import com.yurphagames.thesocialcode.usermanagement.businesslogiclayer.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Interaction> getAll(){
        if(interactionRepository.findAll().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No interactions to get");
        }

        return interactionRepository.findAll();
    }

    @Override
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

    @Override
    public void Like(String Iuserhandle, String Puserhandle, String PsearchTitle){
        // Even though the method I will call
        // in post service returns a list of posts,
        // You will need to have the parameters for it
        // and those params will indeed return a specific
        // video from that use and that is their
        // userHandle and their searchTitle

        Iuserhandle = Iuserhandle.trim();
        Puserhandle = Puserhandle.trim();
        PsearchTitle = PsearchTitle.trim();

        if(Iuserhandle.isEmpty())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You must enter a userHandle for the interactor");

        if(Puserhandle.isEmpty())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You must enter a userHandle for the poster");

        if(PsearchTitle.isEmpty())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You must enter a search Title");


        Interaction interaction = interactionRepository.findByUserHandleInteractedAndUserHandlePosterAndType(Iuserhandle, Puserhandle, InteractionType.LIKE);

        Post post = postService.fingBySearchTitle(PsearchTitle);

        if(interaction == null){
            // this time I need to get the post not the user

            interaction = new Interaction(Iuserhandle, Puserhandle, post, InteractionType.LIKE);

            interaction.like();
            interactionRepository.save(interaction);
            postService.save(post);
            throw new ResponseStatusException(HttpStatus.OK, "Success you liked the post called (" + PsearchTitle + ") by poster (" + Puserhandle + ") and relation created!");
        }

        interaction.dislike();
        interactionRepository.delete(interaction);
        postService.save(post);
        throw new ResponseStatusException(HttpStatus.OK, "Success you disliked the post called (" + PsearchTitle + ") by poster (" + Puserhandle + ") and relation deleted!");
    }

    @Override
    public void Comment(String Iuserhandle, String Puserhandle, String PsearchTitle, String comment){

        Iuserhandle = Iuserhandle.trim();
        Puserhandle = Puserhandle.trim();
        PsearchTitle = PsearchTitle.trim();
        comment = comment.trim();

        if(Iuserhandle.isEmpty())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You must enter a userHandle for the interactor");

        if(Puserhandle.isEmpty())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You must enter a userHandle for the poster");

        if(PsearchTitle.isEmpty())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You must enter a search title");

        if(comment.isEmpty())
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "You must give a comment to use this");




        Post post = postService.fingBySearchTitle(PsearchTitle);

        Interaction interaction = new Interaction(Iuserhandle, Puserhandle, post, comment, InteractionType.COMMENT);

        interactionRepository.save(interaction);

    }

    @Override
    public List<Interaction> getAllBySearchTitle(String searchTitle) {
        if(searchTitle.isEmpty()) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No Search Title entered");

        Post post = postService.fingBySearchTitle(searchTitle);

        List<Interaction> refInteraction = this.getAll();
        List<Interaction> interactions = new ArrayList<>();

        for (Interaction interaction : refInteraction){
            if(interaction.getPost().getSearchTitle().equals(searchTitle)){
                interactions.add(interaction);
            }
        }

        return interactions;
    }
}
