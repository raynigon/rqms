package com.raynigon.rqms.domain.offline.helpers;

import com.raynigon.rqms.infrastructure.converters.LabelConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class SearchQueryData {

    @Column(name = "query_type")
    private String type;

    @Column(name = "query_search_term")
    private String searchTerm;

    @Convert(converter = LabelConverter.class)
    @Column(name = "query_parameters")
    private Map<String, String> parameters;
}
