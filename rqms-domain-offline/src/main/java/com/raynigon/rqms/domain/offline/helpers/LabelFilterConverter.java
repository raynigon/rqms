package com.raynigon.rqms.domain.offline.helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raynigon.rqms.domain.offline.aggregations.LabelFilter;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class LabelFilterConverter implements AttributeConverter<LabelFilter, String> {

    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(LabelFilter attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LabelFilter convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, LabelFilter.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
