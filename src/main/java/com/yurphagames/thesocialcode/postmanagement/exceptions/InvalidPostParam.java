package com.yurphagames.thesocialcode.postmanagement.exceptions;

public class InvalidPostParam extends RuntimeException {
    public InvalidPostParam(String message) {
        super(message);
    }

    public InvalidPostParam(String message, Throwable cause){
        super(message, cause);
    }

    public static InvalidPostParam forFileMissing(){
        return new InvalidPostParam("You must enter a file to complete a successful post");
    }

    public static InvalidPostParam forFileType(String type){
        return new InvalidPostParam("File type : " + type + " is not a compatible file type chose another");
    }

    public static InvalidPostParam forUserHandle(){
        return new InvalidPostParam("You must enter a userHandle");
    }
}
