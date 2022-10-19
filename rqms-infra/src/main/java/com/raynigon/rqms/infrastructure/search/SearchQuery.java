package com.raynigon.rqms.infrastructure.search;

import java.io.Serializable;

public interface SearchQuery extends Serializable {

    String getType();

    String build();
}
