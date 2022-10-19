package com.raynigon.rqms.domain.offline.valueobjects;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public record ExpectedResult(
        @NonNull
        String documentId,
        double relevance,
        @NonNull
        String title,
        @Nullable
        String imageUrl
) {

    public ExpectedResult withRelevance(double relevance) {
        return new ExpectedResult(
                documentId,
                relevance,
                title,
                imageUrl
        );
    }
}
