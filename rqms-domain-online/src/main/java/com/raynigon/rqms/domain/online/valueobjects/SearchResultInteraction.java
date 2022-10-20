package com.raynigon.rqms.domain.online.valueobjects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public class SearchResultInteraction {

    private final OffsetDateTime timestamp;
    private final String documentId;
    private final Long rank;

    @Getter(AccessLevel.NONE)
    private final Map<String, String> properties;

    public Set<String> listPropertyNames() {
        return properties.keySet();
    }

    public String getProperty(String name) {
        return properties.get(name);
    }
}
