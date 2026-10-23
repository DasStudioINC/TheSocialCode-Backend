package com.yurphagames.thesocialcode.postmanagement.infrustructurelayer.repository;

import com.yurphagames.thesocialcode.postmanagement.domainlayer.Post;
import com.yurphagames.thesocialcode.postmanagement.domainlayer.PostData;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findBySearchTitle(String searchTitle);

    @Transactional
    void deleteBySearchTitle(String searchTitle);
}
