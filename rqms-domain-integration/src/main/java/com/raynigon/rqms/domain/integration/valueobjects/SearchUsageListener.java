package com.raynigon.rqms.domain.integration.valueobjects;

import com.raynigon.rqms.domain.integration.entities.SearchLogEntry;
import com.raynigon.rqms.domain.integration.entities.SearchResultInteraction;

public interface SearchUsageListener {

    void onSearchQuery(SearchLogEntry entry);

    void onResultInteraction(SearchResultInteraction interaction);
}
