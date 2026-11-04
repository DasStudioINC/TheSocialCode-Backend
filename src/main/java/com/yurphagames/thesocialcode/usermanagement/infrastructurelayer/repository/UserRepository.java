package com.yurphagames.thesocialcode.usermanagement.infrastructurelayer.repository;

import com.yurphagames.thesocialcode.usermanagement.domainlayer.User;
import com.yurphagames.thesocialcode.usermanagement.domainlayer.UserHandle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserHandle_userHandle(String userHandle);
    void deleteByUserHandle(String userHandle);
    void existsByUserHandle(String userHandle);
}
