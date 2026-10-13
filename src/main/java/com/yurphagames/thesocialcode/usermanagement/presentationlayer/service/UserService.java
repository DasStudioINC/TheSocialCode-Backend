package com.yurphagames.thesocialcode.usermanagement.presentationlayer.service;

import com.yurphagames.thesocialcode.postmanagement.presentationlayer.service.PostService;
import com.yurphagames.thesocialcode.usermanagement.domainlayer.*;
import com.yurphagames.thesocialcode.usermanagement.infrastructurelayer.repository.UserRepository;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

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

    public void setProfile(MultipartFile file, String userhandle) throws IOException {
        try{
            if(userhandle.isEmpty() || userhandle == null)
                throw new IOException("userhandle cannot be empty");

            User user;

            try{
                user = getUserByUserHandle(userhandle);
            }catch (RuntimeException e){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }

            if(file.isEmpty())
                throw new IOException("Empty File");


            Path folder = Paths.get("uploads").resolve(userhandle).resolve("user_profile").normalize();
            if(!Files.exists(folder))
                Files.createDirectories(folder);

            String org_filename = file.getOriginalFilename();

            Path finalPath = folder.resolve(org_filename);

            Files.copy(file.getInputStream(), finalPath, StandardCopyOption.REPLACE_EXISTING);

            user.getProfilePicture().setPath_to_profile_picture(finalPath.toString());
            userRepository.save(user);
        }catch (IOException e){
            throw new IOException("Store exception");
        }
    }

    public User save(User user){
        return userRepository.save(user);
    }
}
