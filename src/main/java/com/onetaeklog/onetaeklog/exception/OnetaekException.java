package com.onetaeklog.onetaeklog.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class OnetaekException extends RuntimeException {

    public final Map<String, String> validation = new HashMap<>();

    public OnetaekException(String message) {
        super(message);
    }

    public OnetaekException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String filedName, String message) {
        validation.put(filedName, message);
    }
}
