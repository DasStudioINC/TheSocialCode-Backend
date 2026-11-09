package com.yurphagames.thesocialcode.postmanagement.exceptions;

public class SubscriptionException extends RuntimeException {
    public SubscriptionException(String message) {
        super(message);
    }

    public SubscriptionException(String message, Throwable cause){
        super(message, cause);
    }

    public static SubscriptionException forBasic(double size){
        return new SubscriptionException("Your file has a size of {" + size + "} while the BASIC plan only allows 10mb");
    }

    public static SubscriptionException forPremium(double size){
        return new SubscriptionException("Your file has a size of {" + size + "} while the PREMIUM plan only allows 40mb");
    }
}
