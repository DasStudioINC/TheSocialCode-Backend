package com.yurphagames.thesocialcode.usermanagement.businesslogiclayer.service;

import com.yurphagames.thesocialcode.usermanagement.domainlayer.*;
import com.yurphagames.thesocialcode.usermanagement.infrastructurelayer.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface{

    private final UserRepository userRepository;

    public UserService(UserRepository repo){
        this.userRepository = repo;

    }

    @Override
    public User getUserByUserHandle(String userhandle){
        return userRepository.findByUserHandle_userHandle(userhandle).orElseThrow(() ->
                new RuntimeException("USER NOT FOUND: " + userhandle)
        );
    }

    @Override
    public User newUser(User user){
        if(userRepository.findByUserHandle_userHandle(user.getUserHandle().getUserHandle()).isPresent()){
            throw new RuntimeException("User with that handle (" + user.getUserHandle().getUserHandle() + ")  already exits");
        };


        user.setAccountData(new AccountData());
        user.setProfilePicture(new ProfilePicture(null));
        if(user.getCreationDate() == null){
            user.setCreationDate(LocalDate.now());
        }

        return userRepository.save(user);
    }

    @Override
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
            System.out.println("\n\nUSER PROFILE PITURE: " + user.getProfilePicture().getPath_to_profile_picture());
            userRepository.save(user);
        }catch (IOException e){
            throw new IOException("Store exception");
        }
    }

    @Override
    public User save(User user){
        return userRepository.save(user);
    }

    @Override
    public User Id(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Could not find User with id : " + id));
    }

    @Override
    public List<User> All() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User existing){
        return this.userRepository.save(existing);
    }


    @Override
    public void deleteById(Integer id){
        if(!userRepository.existsById(id)) throw new EntityNotFoundException("User not found");
        this.userRepository.deleteById(id);
    }

    @Override
    public void deleteByUserHandle(String userhandle) {
        Optional<User> user = userRepository.findByUserHandle_userHandle(userhandle);
        if(user.isEmpty()) throw new EntityNotFoundException("User not found");
        deleteById(user.get().getId());
    }

    // User Profile Panel Small

    @Override
    public ResponseEntity<InputStreamResource> getUserProfile(String handle){
        User user = this.getUserByUserHandle(handle);

        if(user == null){
            throw new EntityNotFoundException("User not found");
        }

        String path = user.getProfilePicture().getPath_to_profile_picture();
        try{
            Path profile = Paths.get(path).normalize();

            InputStream input = Files.newInputStream(profile);
            InputStreamResource resource = new InputStreamResource(input);
            ResponseEntity.BodyBuilder builder = ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .header("Content-Disposition", "inline; filename=\"profilepic.png\"")
                    .header("Accept-Ranges", "bytes");


            builder.contentLength(Files.size(profile));
            return builder.body(resource);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
