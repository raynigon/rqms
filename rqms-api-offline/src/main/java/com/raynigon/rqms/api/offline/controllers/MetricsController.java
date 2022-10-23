package com.raynigon.rqms.api.offline.controllers;

import com.raynigon.rqms.domain.offline.factories.OfflineMetricsFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/offline/metrics")
public class MetricsController {

    private final OfflineMetricsFactory factory;

    @GetMapping
    List<Object> listMetrics() {
        return new ArrayList<>(factory.listMetrics());
    }
}
