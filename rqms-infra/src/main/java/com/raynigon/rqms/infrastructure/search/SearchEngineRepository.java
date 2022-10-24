package com.raynigon.rqms.infrastructure.search;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SearchEngineRepository {

    private final Map<String, SearchEngine> searchEngines = new HashMap<>();

    public SearchEngineRepository(List<SearchEngine> searchEngines) {
        this.searchEngines.putAll(searchEngines.stream()
                .collect(Collectors.toMap(SearchEngine::getName, Function.identity())));
    }

    public List<SearchEngine> findAll() {
        return searchEngines.values().stream().toList();
    }

    public SearchEngine findByName(String name) {
        return searchEngines.get(name);
    }

    public void save(SearchEngine searchEngine) {
        searchEngines.put(searchEngine.getName(), searchEngine);
    }

    public void delete(SearchEngine searchEngine) {
        searchEngines.remove(searchEngine.getName());
    }


}
