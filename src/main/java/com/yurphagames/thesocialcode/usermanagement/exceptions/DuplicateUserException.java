package com.yurphagames.thesocialcode.usermanagement.exceptions;

public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException(String message) {
        super(message);
    }


    public DuplicateUserException(String message, Throwable cause){
        super(message, cause);
    }


    public static DuplicateUserException forUserHandle(String userHandle){
        return new DuplicateUserException("User with userhandle: " + userHandle + " already exists!");
    }

}
