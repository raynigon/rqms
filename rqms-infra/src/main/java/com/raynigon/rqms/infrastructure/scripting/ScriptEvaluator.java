package com.raynigon.rqms.infrastructure.scripting;

public interface ScriptEvaluator {

    boolean evaluate(String code, String contextName, Object context);
}
