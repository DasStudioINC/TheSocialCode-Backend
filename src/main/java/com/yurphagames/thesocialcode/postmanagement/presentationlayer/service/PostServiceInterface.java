package com.yurphagames.thesocialcode.postmanagement.presentationlayer.service;

import com.yurphagames.thesocialcode.postmanagement.domainlayer.Post;

import java.util.HashMap;
import java.util.List;

public interface PostServiceInterface {

    public List<Post> getAll();
    public List<Post> getPostByParams(String userhandle, String postname, int index);
    public Post findByPath(String path);
    public List<Post> findByUserHandle(String userhandle, int index);
    public Post findByUsernameAndPostNumber(String username, int postnumber);



}
