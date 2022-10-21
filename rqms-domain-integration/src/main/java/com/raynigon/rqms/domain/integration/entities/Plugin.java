package com.raynigon.rqms.domain.integration.entities;

public interface Plugin {

    String getName();

    void initialize(Object context);

    void shutdown(Object context);
}
