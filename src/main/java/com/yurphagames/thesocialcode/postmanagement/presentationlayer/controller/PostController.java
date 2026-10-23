package com.yurphagames.thesocialcode.postmanagement.presentationlayer.controller;

import com.yurphagames.thesocialcode.postmanagement.datamappinglayer.PostMapper;
import com.yurphagames.thesocialcode.postmanagement.domainlayer.Post;
import com.yurphagames.thesocialcode.postmanagement.presentationlayer.service.PostService;
import com.yurphagames.thesocialcode.usermanagement.presentationlayer.service.UserService;
import jakarta.persistence.EntityNotFoundException;
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
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;
    public PostController(PostService postService, PostMapper postMapper){
        this.postService = postService;
        this.postMapper = postMapper;
    }




    @GetMapping
    public ResponseEntity getAllPosts(){
        return ResponseEntity.ok(postMapper.entityToResponseModelList(postService.getAll()));
    }

    // This is for getting raw post data
    @GetMapping("/filter")
    public ResponseEntity filterPosts(
            @RequestParam(required = false, defaultValue = "") String userhandle,
            @RequestParam(required = false, defaultValue = "") String postname,
            @RequestParam(required = true, defaultValue = "1") int index
    ){
        return ResponseEntity.ok(postMapper.entityToResponseModelList(postService.getPostByParams(userhandle, postname, index)));
    }

    @GetMapping("/search/{searchTitle}/post")
    public ResponseEntity<InputStreamResource> getPostBySearchTitle(
            @PathVariable String searchTitle
    ){
        return postService.getBysearchTitle(searchTitle);
    }

    // Can be used but theirs no reason too because
    // I expose searchTitle not id to anyone

    @GetMapping("/{id:\\d+}/post")
    public ResponseEntity<InputStreamResource> getPostById(@PathVariable long id){
        return postService.getById(id);
    }





    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadFile(
            @RequestPart(value = "file") MultipartFile file,
            @RequestParam("userhandle") String userhandle,
            @RequestParam("password") String password
    ) throws IOException {
        postService.upload(file, userhandle, password);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deletePost(
            @RequestParam("searchTitle") String searchTitle,
            @RequestParam("password") String password
    ){
        try{
            postService.deleteBySearchTitle(searchTitle, password);
            return ResponseEntity.noContent().build();
        }catch(EntityNotFoundException | IOException e){
            return ResponseEntity.notFound().build();
        }
    }


}
