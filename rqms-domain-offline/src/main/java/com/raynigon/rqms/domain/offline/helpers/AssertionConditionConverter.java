package com.raynigon.rqms.domain.offline.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raynigon.rqms.domain.offline.valueobjects.AssertionCondition;
import com.raynigon.rqms.domain.offline.valueobjects.ExpectedResult;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;

@Converter
@RequiredArgsConstructor
public class AssertionConditionConverter implements AttributeConverter<Set<AssertionCondition>, String> {

    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(Set<AssertionCondition> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<AssertionCondition> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<Set<AssertionCondition>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
