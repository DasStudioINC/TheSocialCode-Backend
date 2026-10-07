package com.yurphagames.thesocialcode.postmanagement.infrustructurelayer.repository;

import com.yurphagames.thesocialcode.postmanagement.domainlayer.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
