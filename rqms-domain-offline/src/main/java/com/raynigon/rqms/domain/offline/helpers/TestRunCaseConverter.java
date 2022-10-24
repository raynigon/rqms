package com.raynigon.rqms.domain.offline.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raynigon.rqms.domain.offline.valueobjects.TestRunCase;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Converter
@RequiredArgsConstructor
public class TestRunCaseConverter implements AttributeConverter<Set<TestRunCase>, String> {

    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(Set<TestRunCase> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<TestRunCase> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<Set<TestRunCase>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
