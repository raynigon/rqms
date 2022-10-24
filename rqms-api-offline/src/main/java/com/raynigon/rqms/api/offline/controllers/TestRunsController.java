package com.raynigon.rqms.api.offline.controllers;

import com.raynigon.rqms.api.offline.models.CreateTestRunRequest;
import com.raynigon.rqms.domain.offline.aggregations.RelevanceTestRunAggregate;
import com.raynigon.rqms.domain.offline.services.RelevanceTestRunService;
import com.raynigon.rqms.infrastructure.search.SearchEngine;
import com.raynigon.rqms.infrastructure.search.SearchEngineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/offline/test-runs")
public class TestRunsController {

    private final SearchEngineRepository searchEngineRepository;
    private final RelevanceTestRunService service;

    @GetMapping
    List<RelevanceTestRunAggregate> listTestRuns() {
        return service.findAll();
    }

    @GetMapping("{id}")
    Object getTestRun(@PathVariable("id") UUID id) {
        return service.findById(id);
    }


    @PostMapping
    Object createTestRun(@RequestBody CreateTestRunRequest body) {
        SearchEngine searchEngine = searchEngineRepository.findByName(body.searchEngine());
        return service.createRun(searchEngine, body.filter(), body.description());
    }
}
