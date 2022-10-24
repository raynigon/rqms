package com.raynigon.rqms.domain.offline.helpers;

import com.raynigon.rqms.infrastructure.search.SearchEngine;
import com.raynigon.rqms.infrastructure.search.SearchEngineRepository;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class SearchEngineConverter implements AttributeConverter<SearchEngine, String> {

    private final SearchEngineRepository repository;

    @Override
    public String convertToDatabaseColumn(SearchEngine attribute) {
        return attribute.getName();
    }

    @Override
    public SearchEngine convertToEntityAttribute(String dbData) {
        return repository.findByName(dbData);
    }
}