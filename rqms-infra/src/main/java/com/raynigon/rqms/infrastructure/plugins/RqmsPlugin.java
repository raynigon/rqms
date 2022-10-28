package com.raynigon.rqms.infrastructure.plugins;

import org.pf4j.PluginWrapper;
import org.pf4j.spring.SpringPlugin;

public abstract class RqmsPlugin extends SpringPlugin {

    public RqmsPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    abstract String getName();

    abstract void initialize(Object context);

    abstract void shutdown(Object context);
}
