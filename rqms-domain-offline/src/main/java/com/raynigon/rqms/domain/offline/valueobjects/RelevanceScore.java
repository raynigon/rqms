package com.raynigon.rqms.domain.offline.valueobjects;

public record RelevanceScore(double value) {

    public RelevanceScore {
        if (value < 0.0 || value > 1.0) {
            // TODO replace with proper exception
            throw new RuntimeException("Illegal Value " + value);
        }
    }
}
