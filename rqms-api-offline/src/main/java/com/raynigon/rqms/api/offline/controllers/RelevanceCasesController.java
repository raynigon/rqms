package com.raynigon.rqms.api.offline.controllers;

import com.raynigon.rqms.api.offline.mappers.RelevanceCaseMapper;
import com.raynigon.rqms.api.offline.models.RelevanceCaseListItem;
import com.raynigon.rqms.app.offline.services.RelevanceCaseOverviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/offline/relevance-cases")
public class RelevanceCasesController {

    private final RelevanceCaseMapper mapper;

    private final RelevanceCaseOverviewService service;

    @GetMapping
    List<RelevanceCaseListItem> listRelevanceCases() {
        return service.listRelevanceCases()
                .stream()
                .map(it -> mapper.mapToRelevanceCaseListItem(it.getKey(), it.getValue()))
                .collect(Collectors.toList());
    }
}
