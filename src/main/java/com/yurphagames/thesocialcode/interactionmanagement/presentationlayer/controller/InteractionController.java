package com.yurphagames.thesocialcode.interactionmanagement.presentationlayer.controller;

import com.yurphagames.thesocialcode.interactionmanagement.datamappinglayer.InteractionMapper;
import com.yurphagames.thesocialcode.interactionmanagement.presentationlayer.models.CommentInteractionResponseModel;
import com.yurphagames.thesocialcode.interactionmanagement.buisinesslogiclayer.service.InteractionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/interactions")
public class InteractionController {

    private final InteractionService interactionService;
    private final InteractionMapper mapper;
    public InteractionController(InteractionService service, InteractionMapper mapper){
        this.interactionService = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CommentInteractionResponseModel>> getAll(){
      return ResponseEntity.ok(mapper.entityToResponseModelList(interactionService.getAll()));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<CommentInteractionResponseModel>> getAllBySearchTitle(
            @RequestParam String searchTitle
    ){
        return ResponseEntity.ok(mapper.entityToResponseModelList(interactionService.getAllBySearchTitle(searchTitle)));
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
            @RequestParam String searchTitle
    ){
        interactionService.Like(interactedUserHandle, posterUserHandle, searchTitle);

        return ResponseEntity.ok("Post like task success");
    }

    @PostMapping("/comment")
    public ResponseEntity<String> comment(
            @RequestParam String interactedUserHandle,
            @RequestParam String posterUserHandle,
            @RequestParam String searchTitle,
            @RequestParam String comment
    ){
        interactionService.Comment(interactedUserHandle, posterUserHandle, searchTitle, comment);
        return ResponseEntity.ok("Comment task success");
    }

}
