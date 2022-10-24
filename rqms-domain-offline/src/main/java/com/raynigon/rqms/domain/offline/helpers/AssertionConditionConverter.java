package com.raynigon.rqms.domain.offline.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raynigon.rqms.domain.offline.valueobjects.AssertionCondition;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class AssertionConditionConverter implements AttributeConverter<AssertionCondition, String> {

    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(AssertionCondition attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AssertionCondition convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, AssertionCondition.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
