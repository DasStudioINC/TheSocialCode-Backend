package com.yurphagames.thesocialcode.interactionmanagement.exceptions;

import com.yurphagames.thesocialcode.postmanagement.exceptions.InvalidPostParam;
import com.yurphagames.thesocialcode.postmanagement.exceptions.PostNotFoundExecption;

public class InvalidParamException extends RuntimeException {
    public InvalidParamException(String message) {
        super(message);
    }

    public InvalidParamException(String message, Throwable cause){
        super(message, cause);
    }

    public static InvalidPostParam forParam(String param){
        return new InvalidPostParam("You must enter a " + param);
    }

    public static InvalidParamException forSelfInjection(){
        return new InvalidParamException("You cannot inject into yourself");
    }
}
