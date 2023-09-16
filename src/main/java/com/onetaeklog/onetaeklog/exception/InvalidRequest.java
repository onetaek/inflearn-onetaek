package com.onetaeklog.onetaeklog.exception;

public class InvalidRequest extends OnetaekException {

    private static final String MESSAGE = "잘못된 요청입니다.";

    public InvalidRequest() {
        super(MESSAGE);
    }

    public InvalidRequest(String fileName, String message) {
        super(MESSAGE);
        addValidation(fileName, message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
