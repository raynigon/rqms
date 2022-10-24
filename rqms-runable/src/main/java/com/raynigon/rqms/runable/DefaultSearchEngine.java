package com.raynigon.rqms.runable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raynigon.rqms.infrastructure.search.SearchEngine;
import com.raynigon.rqms.infrastructure.search.SearchQuery;
import com.raynigon.rqms.infrastructure.search.SearchResult;
import lombok.SneakyThrows;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DefaultSearchEngine implements SearchEngine {

    private final List<DefaultDocument> documents = new ArrayList<>();
    private Map<String, String> labels = new HashMap<>();

    @SneakyThrows
    public DefaultSearchEngine(ObjectMapper objectMapper) {
        InputStream inputStream = getClass().getResourceAsStream("/default-search-engine-data.json");
        DiskDataFormat data = objectMapper.readValue(inputStream, DiskDataFormat.class);
        documents.addAll(data.docs);
    }

    @NonNull
    @Override
    public String getName() {
        return "default";
    }

    @NonNull
    @Override
    public Map<String, String> getLabels() {
        return labels;
    }

    @Override
    public void setLabels(@NonNull Map<String, String> labels) {
        this.labels = labels;
    }

    @NonNull
    @Override
    public List<SearchResult> search(SearchQuery query) {
        // Simple Tokenization ;)
        List<String> terms = Arrays.stream(query.getSearchTerm().toLowerCase().split(" ")).toList();
        return documents.stream()
                .filter(doc -> match(doc, terms))
                .limit(10)
                .collect(Collectors.toList());
    }

    private boolean match(DefaultDocument doc, List<String> terms) {
        String documentId = doc.id.toString();
        String normalizedTitle = doc.title.toLowerCase();
        return terms.stream().anyMatch(normalizedTitle::contains) ||
                terms.stream().anyMatch(documentId::contains);
    }

    record DiskDataFormat(List<DefaultDocument> docs) {
    }

    record DefaultDocument(
            UUID id,
            String title,
            String imageUrl,
            int price,
            boolean discounted
    ) implements SearchResult {

        @NonNull
        @Override
        public String getDocumentId() {
            return id.toString();
        }

        @NonNull
        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public String getImageUrl() {
            return imageUrl;
        }
    }
}
