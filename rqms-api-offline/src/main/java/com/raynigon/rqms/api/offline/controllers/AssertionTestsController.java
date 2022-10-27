package com.raynigon.rqms.api.offline.controllers;

import com.raynigon.rqms.api.offline.mappers.AssertionTestMapper;
import com.raynigon.rqms.api.offline.models.AssertionConditionDto;
import com.raynigon.rqms.api.offline.models.AssertionTestDto;
import com.raynigon.rqms.api.offline.models.RelevanceCaseDto;
import com.raynigon.rqms.domain.offline.aggregations.AssertionTestAggregate;
import com.raynigon.rqms.domain.offline.aggregations.RelevanceCaseAggregate;
import com.raynigon.rqms.domain.offline.factories.AssertionTestFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/offline/assertion-tests")
public class AssertionTestsController {

    private final AssertionTestFactory factory;

    private final AssertionTestMapper mapper;

    @GetMapping
    List<AssertionTestDto> listAssertionTests() {
        return factory.findAll().stream().map(mapper::mapToDto).toList();
    }

    @GetMapping("{id}")
    AssertionTestDto getAssertionTest(@PathVariable("id") UUID id) {
        AssertionTestAggregate aggregate = factory.findById(id);
        if (aggregate == null) {
            throw new RuntimeException("Assertion Test not found");
        }
        return mapper.mapToDto(aggregate);
    }

    @PostMapping
    AssertionTestDto createAssertionTest(@RequestBody AssertionTestDto assertionTest) {
        AssertionTestAggregate aggregate = factory.create(
                assertionTest.query().type(),
                assertionTest.query().searchTerm(),
                assertionTest.query().parameters(),
                assertionTest.conditions().stream().map(AssertionConditionDto::code).toList()
        );
        return mapper.mapToDto(aggregate);
    }

    @PutMapping("{id}")
    AssertionTestDto updateAssertionTest(@PathVariable("id") UUID id, @RequestBody AssertionTestDto assertionTest) {
        AssertionTestAggregate aggregate = factory.findById(id);
        if (aggregate == null) {
            throw new RuntimeException("Assertion Test not found");
        }
        aggregate.setQuery(assertionTest.query().type(), assertionTest.query().searchTerm(), assertionTest.query().parameters());
        aggregate.setConditions(assertionTest.conditions().stream().map(AssertionConditionDto::code).toList());
        aggregate.save();
        return mapper.mapToDto(aggregate);
    }

    @DeleteMapping("{id}")
    void deleteRelevanceCase(@PathVariable("id") UUID id) {
        factory.deleteById(id);
    }
}
