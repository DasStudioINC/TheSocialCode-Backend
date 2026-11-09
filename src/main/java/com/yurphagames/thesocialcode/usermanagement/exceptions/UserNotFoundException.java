package com.yurphagames.thesocialcode.usermanagement.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public static UserNotFoundException forUserHandle(String userHandle){
        return new UserNotFoundException("User with userhandle: " + userHandle + " was not found");
    }

    public static UserNotFoundException forId(Integer id){
        return new UserNotFoundException("User with id: " + id + " was not found");
    }
}
