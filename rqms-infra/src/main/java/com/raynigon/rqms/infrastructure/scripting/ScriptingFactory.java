package com.raynigon.rqms.infrastructure.scripting;

import lombok.AccessLevel;
import lombok.Getter;

@Getter
public class ScriptingFactory {

    @Getter(AccessLevel.NONE)
    public static final ScriptingFactory INSTANCE = new ScriptingFactory();

    private ScriptEvaluator scriptEvaluator;
}
