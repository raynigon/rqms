package com.raynigon.rqms.api.offline.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/offline/test-run-schedules")
public class TestRunSchedulesController {

    @GetMapping
    List<Object> listTestRunSchedules() {
        return List.of();
    }
}
