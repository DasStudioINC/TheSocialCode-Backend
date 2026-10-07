package com.yurphagames.thesocialcode.postmanagement.presentationlayer.controller;

import com.yurphagames.thesocialcode.postmanagement.domainlayer.Post;
import com.yurphagames.thesocialcode.postmanagement.presentationlayer.service.PostService;
import com.yurphagames.thesocialcode.usermanagement.presentationlayer.service.UserService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;
    private final UserService userService;
    public PostController(PostService postService, UserService userService){
        this.postService = postService;
        this.userService = userService;
    }




    @GetMapping
    public List<Post> getAllPosts(){
        return postService.getAll();
    }

    @GetMapping("/search")
    public List<Post> searchPosts(
            @RequestParam(required = false, defaultValue = "") String username,
            @RequestParam(required = false, defaultValue = "") String postname,
            @RequestParam(required = true, defaultValue = "1") int index
    ){
        return postService.getPostByParams(username, postname, index);
    }


    @GetMapping("/{id:\\d+}/post")
    public ResponseEntity<InputStreamResource> getPostById(@PathVariable long id){
        return postService.getById(id);
    }


    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestParam("post") MultipartFile post,
            @RequestParam("username") String username
    )
    {

    }


}
