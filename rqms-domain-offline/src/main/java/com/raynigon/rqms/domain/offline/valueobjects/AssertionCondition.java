package com.raynigon.rqms.domain.offline.valueobjects;

import com.raynigon.rqms.infrastructure.scripting.ScriptEvaluator;
import com.raynigon.rqms.infrastructure.scripting.ScriptingFactory;
import com.raynigon.rqms.infrastructure.search.SearchResult;

import java.util.List;

public record AssertionCondition(String code) {

    public boolean evaluate(List<SearchResult> results) {
        ScriptEvaluator evaluator = ScriptingFactory.INSTANCE.getScriptEvaluator();
        return evaluator.evaluate(code, "results", results);
    }
}
