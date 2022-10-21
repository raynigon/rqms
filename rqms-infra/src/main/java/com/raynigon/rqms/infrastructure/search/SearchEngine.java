package com.raynigon.rqms.infrastructure.search;

import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * The Search Engine Interface is part of the infrastructure layer and represents the configuration of a search service.
 * This can correspond to an index in Elasticsearch, or a separate application, which in turn uses a search engine.
 * An implementation must therefore be provided by the respective plugin.
 * By assigning labels, an instance can be easily filtered out of the set of all existing instances.
 */
public interface SearchEngine extends Serializable {


    /**
     * Each Search Engine has to have a unique Name.
     * This name cannot be altered.
     *
     * @return The name of this search engine
     */
    @NonNull
    String getName();

    /**
     * Labels can be assigned to each Search Engine instance,
     * so an instance can be easily filtered out of the set of all existing instances.
     *
     * @return The assigned Labels for this search Engine
     */
    @NonNull
    Map<String, String> getLabels();

    /**
     * Labels can be assigned to each Search Engine instance,
     * so an instance can be easily filtered out of the set of all existing instances.
     *
     * @param labels the new labels for this instance
     */
    void setLabels(@NonNull Map<String, String> labels);

    /**
     * This method returns a list of search results by taking a {@link SearchQuery} object.
     *
     * @param query The query which should be executed in the search service.
     * @return The search results returned by the search service.
     */
    @NonNull
    List<SearchResult> search(SearchQuery query);

    /**
     * Create a new {@link SearchQuery} for a given Search Term and parameters
     *
     * @param searchTerm The search term for the new query
     * @param parameters parameters for the new search query
     * @return A new {@link SearchQuery} Object for the search term and parameters
     */
    SearchQuery createQuery(String searchTerm, Map<String, String> parameters);
}
