package com.yurphagames.thesocialcode.usermanagement.businesslogiclayer.service;

import com.yurphagames.thesocialcode.usermanagement.domainlayer.User;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserServiceInterface {
    User getUserByUserHandle(String userhandle);

    User newUser(User user);

    void setProfile(MultipartFile file, String userhandle) throws IOException;

    User save(User user);

    User Id(Integer id);

    List<User> All();

    User updateUser(User existing);

    void deleteById(Integer id);

    ResponseEntity<InputStreamResource> getUserProfile(String handle);
}
