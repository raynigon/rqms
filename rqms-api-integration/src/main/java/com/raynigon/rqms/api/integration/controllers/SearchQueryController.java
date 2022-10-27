package com.raynigon.rqms.api.integration.controllers;

import com.raynigon.rqms.infrastructure.search.SearchEngine;
import com.raynigon.rqms.infrastructure.search.SearchQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/integration/search-queries")
public class SearchQueryController {

    private final SearchQueryFactory queryFactory;

    @GetMapping
    List<String> listSearchQueries() {
        return queryFactory.listQueryTypes().stream().toList();
    }
}
