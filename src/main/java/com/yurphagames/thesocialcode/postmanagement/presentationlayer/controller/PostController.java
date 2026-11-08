package com.yurphagames.thesocialcode.postmanagement.presentationlayer.controller;

import com.yurphagames.thesocialcode.postmanagement.datamappinglayer.PostMapper;
import com.yurphagames.thesocialcode.postmanagement.buisnesslogiclayer.service.PostService;
import com.yurphagames.thesocialcode.postmanagement.domainlayer.Post;
import com.yurphagames.thesocialcode.postmanagement.presentationlayer.models.PostRequestModel;
import com.yurphagames.thesocialcode.postmanagement.presentationlayer.models.PostResponseModel;
import com.yurphagames.thesocialcode.usermanagement.domainlayer.User;
import com.yurphagames.thesocialcode.usermanagement.presentationlayer.models.UserRequetsModel;
import com.yurphagames.thesocialcode.usermanagement.presentationlayer.models.UserResponseModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
            @RequestParam(value = "postname", defaultValue = "") String postname,
            @RequestParam("description") String description
    ) throws IOException {
        try {
            postService.upload(file, userhandle, postname,description);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PutMapping("/update/searchTitle/{searchTitle}/post")
    public ResponseEntity<PostResponseModel> updateUser(
            @PathVariable String searchTitle,
            @RequestBody PostRequestModel postRequetsModel
    ){
        try{
            Post post = postService.fingBySearchTitle(searchTitle);

            this.postMapper.updateEntityFromRequestModel(postRequetsModel, post);

            Post upPost =
                    this.postService.updatePost(post);

            return ResponseEntity.ok(
                    this.postMapper.entityToResponseModel(upPost)
            );
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity deletePost(
            @RequestParam("searchTitle") String searchTitle
    ){
        try{
            postService.deleteBySearchTitle(searchTitle);
            return ResponseEntity.noContent().build();
        }catch(EntityNotFoundException | IOException e){
            return ResponseEntity.notFound().build();
        }
    }




}
