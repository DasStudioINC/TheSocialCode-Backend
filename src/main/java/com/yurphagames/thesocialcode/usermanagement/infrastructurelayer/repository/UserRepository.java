package com.yurphagames.thesocialcode.usermanagement.infrastructurelayer.repository;

import com.yurphagames.thesocialcode.usermanagement.domainlayer.User;
import com.yurphagames.thesocialcode.usermanagement.domainlayer.UserHandle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUserHandle_userHandle(String userHandle);
}
