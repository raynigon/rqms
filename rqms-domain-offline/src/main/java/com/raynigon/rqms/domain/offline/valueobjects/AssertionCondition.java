package com.raynigon.rqms.domain.offline.valueobjects;

import com.raynigon.rqms.infrastructure.scripting.ScriptEvaluator;
import com.raynigon.rqms.infrastructure.search.SearchResult;

import java.util.List;

public record AssertionCondition(String code) {

    public boolean evaluate(ScriptEvaluator evaluator, List<SearchResult> results) {
        return evaluator.evaluate(code, "results", results);
    }
}
