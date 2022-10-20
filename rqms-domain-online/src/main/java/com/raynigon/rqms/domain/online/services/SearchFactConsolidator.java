package com.raynigon.rqms.domain.online.services;

import com.raynigon.rqms.domain.online.repositories.SearchFactRepository;
import com.raynigon.rqms.domain.online.valueobjects.SearchResultInteraction;
import com.raynigon.rqms.infrastructure.search.SearchEngine;
import com.raynigon.rqms.infrastructure.search.SearchQuery;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;

@RequiredArgsConstructor
public class SearchFactConsolidator {

    private final SearchFactRepository repository;

    public void addSearchQuery(String searchId, SearchEngine engine, SearchQuery query) {
        throw new NotImplementedException("Not implemented yet");
    }

    public void addInteraction(String searchId, SearchResultInteraction interaction) {
        throw new NotImplementedException("Not implemented yet");
    }
}
