package com.raynigon.rqms.infrastructure.search;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface SearchResult {

    @NonNull
    String getDocumentId();

    @NonNull
    String getTitle();

    @Nullable
    String getImageUrl();
}
