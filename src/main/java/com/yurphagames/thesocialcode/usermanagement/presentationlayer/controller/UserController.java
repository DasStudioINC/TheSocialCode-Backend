package com.yurphagames.thesocialcode.usermanagement.presentationlayer.controller;

import com.yurphagames.thesocialcode.usermanagement.domainlayer.*;
import com.yurphagames.thesocialcode.usermanagement.infrastructurelayer.repository.UserRepository;
import com.yurphagames.thesocialcode.usermanagement.presentationlayer.service.UserService;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository repo, UserService service){
        this.userRepository = repo;
        this.userService = service;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    //Getters
    // This is for getting by the id
    @GetMapping("/id/{id}")
    public User getUserByID(@PathVariable Integer id){
        return userRepository.findById(id).orElse(null);
    }

    @GetMapping("/handle/{handle}")
    public User getUserByHandle(@PathVariable String handle){
        return userService.getUserByUserHandle(handle);
    }

    //Adding a user
    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.newUser(user);
    }

}
