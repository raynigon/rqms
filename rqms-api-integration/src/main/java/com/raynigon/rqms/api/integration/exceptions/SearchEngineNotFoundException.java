package com.raynigon.rqms.api.integration.exceptions;

public class SearchEngineNotFoundException extends RuntimeException {

    public SearchEngineNotFoundException(String name) {
        super("Search Engine '" + name + "' was not found");
    }
}
