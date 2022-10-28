package com.raynigon.rqms.infrastructure.scripting;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class GroovyScriptEvaluator implements ScriptEvaluator {

    private final Timer timer;

    public GroovyScriptEvaluator(MeterRegistry meterRegistry) {
        timer = meterRegistry.timer("raynigon.rqms.groovy.script");
    }

    @Override
    @SneakyThrows
    public boolean evaluate(String code, String contextName, Object context) {
        Binding contextBinding = new Binding();
        contextBinding.setProperty(contextName, context);
        GroovyShell shell = new GroovyShell(contextBinding);
        Object result = timer.recordCallable(() -> shell.evaluate(code));
        if (!(result instanceof Boolean)) {
            // TODO replace with proper exception
            throw new RuntimeException("Result has unexpected Data Type");
        }
        return (Boolean) result;
    }
}
