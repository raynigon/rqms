package com.raynigon.rqms.infrastructure.search;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SearchQueryFactory {

    private final Map<String, SearchQuerySupplier> suppliers = new HashMap<>();

    public SearchQueryFactory() {
        // Add default queries
        suppliers.put(BasicSearchQuery.TYPE, BasicSearchQuery::new);
    }

    public SearchQueryFactory(Set<SearchQuerySupplier> suppliers) {
        this();
        this.suppliers.putAll(suppliers.stream()
                .collect(Collectors.toMap(SearchQuerySupplier::getType, Function.identity())));
    }

    public Set<String> listQueryTypes() {
        return suppliers.keySet();
    }

    public SearchQuery create(String type, String searchTerm, Map<String, String> parameters) {
        if (!suppliers.containsKey(type)) {
            throw new RuntimeException("Unknown Search Query Type");
        }
        return suppliers.get(type).supply(searchTerm, parameters);
    }

    public void registerSupplier(SearchQuerySupplier supplier) {
        String metricName = supplier.getType();
        if (metricName.toLowerCase().startsWith("default-")) {
            // TODO replace with proper exception
            throw new RuntimeException("Default Query Types cannot be overridden");
        }
        suppliers.put(metricName, supplier);
    }

    public interface SearchQuerySupplier {

        default String getType() {
            return supply("test", Map.of()).getType();
        }

        SearchQuery supply(String searchTerm, Map<String, String> parameters);
    }
}
