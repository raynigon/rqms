package com.raynigon.rqms.api.integration.controllers;

import com.raynigon.rqms.api.integration.models.SearchQueryDto;
import com.raynigon.rqms.infrastructure.search.SearchEngine;
import com.raynigon.rqms.infrastructure.search.SearchEngineRepository;
import com.raynigon.rqms.infrastructure.search.SearchQueryFactory;
import com.raynigon.rqms.infrastructure.search.SearchResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/integration/search-engines")
public class SearchEngineController {

    private final SearchEngineRepository repository;

    private final SearchQueryFactory queryFactory;

    @GetMapping
    List<SearchEngine> listSearchEngines() {
        return repository.findAll();
    }

    @GetMapping("{name}")
    SearchEngine getSearchEngine(@PathVariable("name") String name) {
        return repository.findByName(name);
    }

    @PostMapping("{name}/search")
    List<SearchResult> search(@PathVariable("name") String name, @RequestBody SearchQueryDto query) {
        SearchEngine searchEngine = repository.findByName(name);
        if (searchEngine == null) {
            // TODO update with proper exception
            throw new RuntimeException("Search Engine was not found");
        }
        return searchEngine.search(queryFactory.create(query.type(), query.searchTerm(), query.parameters()));
    }
}
