package com.raynigon.rqms.infrastructure.search;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;

public interface SearchResult extends Serializable {

    @NonNull
    String getDocumentId();

    @NonNull
    String getTitle();

    @Nullable
    String getImageUrl();
}
