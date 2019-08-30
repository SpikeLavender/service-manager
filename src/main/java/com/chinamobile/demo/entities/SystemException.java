package com.chinamobile.demo.entities;

public class SystemException extends Exception {

    private int errorCode;

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
