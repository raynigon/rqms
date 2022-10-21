package com.raynigon.rqms.domain.integration.entities;

import com.raynigon.rqms.domain.integration.valueobjects.SearchUsageListener;

public interface SearchUsageDataSource {

    String getName();

    void registerListener(SearchUsageListener listener);
}
