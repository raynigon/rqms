package com.raynigon.rqms.domain.integration.factories;

import com.raynigon.rqms.infrastructure.search.SearchQuery;

import java.util.Map;

public interface SearchQueryFactory {

    SearchQuery createQuery(String searchTerm, Map<String, String> parameters);
}
