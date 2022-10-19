package com.raynigon.rqms.domain.offline.valueobjects;

public record TestRunSearchResult(
        String documentId,
        double relevance,
        String title,
        String imageUrl
) {
}
