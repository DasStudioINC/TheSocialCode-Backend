package com.yurphagames.thesocialcode.usermanagement.presentationlayer.service;

import com.yurphagames.thesocialcode.postmanagement.presentationlayer.service.PostService;
import com.yurphagames.thesocialcode.usermanagement.domainlayer.*;
import com.yurphagames.thesocialcode.usermanagement.infrastructurelayer.repository.UserRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PostService postService;
    public UserService(UserRepository repo, PostService postService){
        this.userRepository = repo;
        this.postService = postService;
    }

    public User getUserByUserHandle(String userhandle){
        return userRepository.findByUserHandle_userHandle(userhandle).orElseThrow(() ->
                new RuntimeException("USER NOT FOUND: " + userhandle)
        );
    }

    public User newUser(User user, MultipartFile file){
        if(userRepository.findByUserHandle_userHandle(user.getUserHandle().getUserHandle()).isPresent()){
            throw new RuntimeException("User with that handle (" + user.getUserHandle().getUserHandle() + ")  already exits");
        };



        return userRepository.save(user);
    }

    public User save(User user){
        return userRepository.save(user);
    }
}
