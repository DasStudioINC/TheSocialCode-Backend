package com.yurphagames.thesocialcode.postmanagement.presentationlayer.controller;

import com.yurphagames.thesocialcode.postmanagement.domainlayer.Post;
import com.yurphagames.thesocialcode.postmanagement.presentationlayer.service.PostService;
import com.yurphagames.thesocialcode.usermanagement.presentationlayer.service.UserService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService){
        this.postService = postService;
    }




    @GetMapping
    public List<Post> getAllPosts(){
        return postService.getAll();
    }

    @GetMapping("/search")
    public List<Post> searchPosts(
            @RequestParam(required = false, defaultValue = "") String userhandle,
            @RequestParam(required = false, defaultValue = "") String postname,
            @RequestParam(required = true, defaultValue = "1") int index
    ){
        return postService.getPostByParams(userhandle, postname, index);
    }


    @GetMapping("/{id:\\d+}/post")
    public ResponseEntity<InputStreamResource> getPostById(@PathVariable long id){
        return postService.getById(id);
    }


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadFile(
            @RequestPart(value = "file") MultipartFile file,
            @RequestParam("userhandle") String userhandle
    ) throws IOException {
        postService.upload(file, userhandle);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


}
