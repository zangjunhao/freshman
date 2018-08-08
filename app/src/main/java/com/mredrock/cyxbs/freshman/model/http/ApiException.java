package com.mredrock.cyxbs.freshman.model.http;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
