package com.raynigon.rqms.infrastructure.search;

import java.io.Serializable;
import java.util.Map;

public interface SearchQuery extends Serializable {

    String getType();

    String getSearchTerm();

    Map<String, String> getParameters();
}
