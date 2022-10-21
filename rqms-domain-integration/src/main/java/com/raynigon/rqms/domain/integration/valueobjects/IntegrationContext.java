package com.raynigon.rqms.domain.integration.valueobjects;

import com.raynigon.rqms.infrastructure.search.SearchEngine;

import java.util.Map;

public interface IntegrationContext {

    Map<String, Object> getProperties();

    void register(SearchEngine a);

    void register(Object a);

    void remove(SearchEngine a);

    void remove(Object a);
}
