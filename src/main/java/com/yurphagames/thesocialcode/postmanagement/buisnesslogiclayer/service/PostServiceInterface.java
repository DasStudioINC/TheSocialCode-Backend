package com.yurphagames.thesocialcode.postmanagement.buisnesslogiclayer.service;

import com.yurphagames.thesocialcode.postmanagement.domainlayer.Post;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface PostServiceInterface {

    ResponseEntity<InputStreamResource> getBysearchTitle(String searchTitle);

    ResponseEntity<InputStreamResource> getById(Long id);

    public List<Post> getAll();
    public List<Post> getPostByParams(String userhandle, String postname, int index);

    public List<Post> findByUserHandle(String userhandle, int index);
    public Post fingBySearchTitle(String searchTitle);

    void upload(MultipartFile file, String userhandle, String description) throws IOException;

    void save(Post post);

    void deleteBySearchTitle(String searchTitle) throws IOException;
}
