package com.yurphagames.thesocialcode.postmanagement.exceptions;



public class PostNotFoundExecption extends RuntimeException {
    public PostNotFoundExecption(String message) {
        super(message);
    }

    public PostNotFoundExecption(String message, Throwable cause){
        super(message, cause);
    }

    public static PostNotFoundExecption forSearchTitle(String searchTitle){
        return new PostNotFoundExecption("Post with searchTitle: " + searchTitle + " was not found");
    }

    public static PostNotFoundExecption forId(Long id){
        return new PostNotFoundExecption("post with id: " + id + " was not found");
    }
}
