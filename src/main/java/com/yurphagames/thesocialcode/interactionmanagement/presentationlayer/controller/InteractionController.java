package com.yurphagames.thesocialcode.interactionmanagement.presentationlayer.controller;

import com.yurphagames.thesocialcode.interactionmanagement.domainlayer.Interaction;
import com.yurphagames.thesocialcode.interactionmanagement.presentationlayer.service.InteractionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/interactions")
public class InteractionController {

    private final InteractionService interactionService;

    public InteractionController(InteractionService service){
        this.interactionService = service;
    }

    @GetMapping
    public List<Interaction> getAll(){

       return interactionService.getAll();
    }


    @PostMapping("/follow")
    public ResponseEntity<String> follow(
            @RequestParam String interactedUserHandle,
            @RequestParam String posterUserHandle
    ){

        interactionService.Follow(interactedUserHandle, posterUserHandle);

        return ResponseEntity.ok("User follow task success");
    }

    @PostMapping("/like")
    public ResponseEntity<String> like(
            @RequestParam String interactedUserHandle,
            @RequestParam String posterUserHandle,
            @RequestParam String postName
    ){
        interactionService.Like(interactedUserHandle, posterUserHandle, postName);

        return ResponseEntity.ok("Post like task success");
    }

}
