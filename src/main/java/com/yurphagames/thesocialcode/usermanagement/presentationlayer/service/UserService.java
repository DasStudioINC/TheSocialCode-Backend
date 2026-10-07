package com.yurphagames.thesocialcode.usermanagement.presentationlayer.service;

import com.yurphagames.thesocialcode.usermanagement.domainlayer.*;
import com.yurphagames.thesocialcode.usermanagement.infrastructurelayer.repository.UserRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository repo){
        this.userRepository = repo;
    }

    public User getUserByUserHandle(String userhandle){
        return userRepository.findByUserHandle_userHandle(userhandle).orElseThrow(() ->
                new RuntimeException("USER NOT FOUND: " + userhandle)
        );
    }

    public User newUser(User user){
        if(userRepository.findByUserHandle_userHandle(user.getUserHandle().getUserHandle()).isPresent()){
            throw new RuntimeException("User with that handle (" + user.getUserHandle().getUserHandle() + ")  already exits");
        };

        return userRepository.save(user);
    }

    public User save(User user){
        return userRepository.save(user);
    }
}
