package com.yurphagames.thesocialcode.usermanagement.presentationlayer.controller;

import com.yurphagames.thesocialcode.usermanagement.datamappinglayer.UserMapper;
import com.yurphagames.thesocialcode.usermanagement.domainlayer.*;
import com.yurphagames.thesocialcode.usermanagement.presentationlayer.models.UserRequetsModel;
import com.yurphagames.thesocialcode.usermanagement.presentationlayer.models.UserResponseModel;
import com.yurphagames.thesocialcode.usermanagement.businesslogiclayer.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;


    public UserController(UserService service, UserMapper userMapper){
        this.userMapper = userMapper;
        this.userService = service;

    }

    @GetMapping
    public ResponseEntity getAllUsers(){
        List userResonseModel =
                this.userMapper.entityToResponseModelList(userService.All());
        return ResponseEntity.ok(userResonseModel);
    }


    //Getters
    // This is for getting by the id
    @GetMapping("/id/{id}")
    public ResponseEntity getUserByID(@PathVariable Integer id){
        try{
            UserResponseModel userResponseModel =
                    this.userMapper.entityToResponseModel(userService.Id(id));
            return ResponseEntity.ok(userResponseModel);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/handle/{handle}")
    public ResponseEntity getUserByHandle(@PathVariable String handle){
        try{
            UserResponseModel userResponseModel =
                    this.userMapper.entityToResponseModel(userService.getUserByUserHandle(handle));
            return ResponseEntity.ok(userResponseModel);
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/handle/{handle}/picture")
    public ResponseEntity<InputStreamResource> getUserProfile(@PathVariable String handle){
       return userService.getUserProfile(handle);
    }

    //Adding a user
    @PostMapping
    public ResponseEntity createUser(@RequestBody UserRequetsModel userRequetsModel){
        User user = this.userMapper.requestModelToEntity(userRequetsModel);
        User savedUser = this.userService.newUser(user);

        UserResponseModel userResponseModel =
                this.userMapper.entityToResponseModel(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseModel);
    }

    @PutMapping(value = "/profile/set/handle/{handle}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void setProfile(
            @PathVariable String handle,
            @RequestPart(value = "file") MultipartFile file
    ) throws IOException {
        userService.setProfile(file, handle);
    }

    @PutMapping("/handle/{handle}")
    public ResponseEntity<UserResponseModel> updateUser(
            @PathVariable String handle,
            @RequestBody UserRequetsModel userRequetsModel
    ){
        try{
            User user = this.userService.getUserByUserHandle(handle);

            this.userMapper.updateEntityFromRequestModel(userRequetsModel, user);

            User upUser =
                    this.userService.updateUser(user);

            return ResponseEntity.ok(
                    this.userMapper.entityToResponseModel(upUser)
            );
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }


    @DeleteMapping("/id/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        try{
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        }catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

}
