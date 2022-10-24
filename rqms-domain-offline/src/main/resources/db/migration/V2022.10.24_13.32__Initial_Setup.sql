create schema if not exists "offline";
create extension if not exists "uuid-ossp";

create table if not exists offline.relevance_cases
(
    id                uuid default uuid_generate_v4() not null
        constraint relevance_case_pkey
            primary key,
    name              varchar(255)                    not null,
    labels            text                            not null,
    metric_name       varchar(255)                    not null,
    metric_cutoff     varchar(255)                    not null,
    query_type        varchar(255)                    not null,
    query_search_term varchar(255)                    not null,
    query_parameters  text                            not null,
    expected_results  text                            not null
);

create table if not exists offline.relevance_test_runs
(
    id                  uuid default uuid_generate_v4() not null
        constraint relevance_test_run_pkey
            primary key,
    description         text                            not null,
    execution_timestamp timestamptz                     not null,
    assertions_failed   boolean                         not null,
    label_filter        text                            not null,
    search_engine       text                            not null,
    test_run_cases      text                            not null
);

create table if not exists offline.assertion_tests
(
    id                uuid default uuid_generate_v4() not null
        constraint assertion_test_pkey
            primary key,
    query_type        varchar(255)                    not null,
    query_search_term varchar(255)                    not null,
    query_parameters  text                            not null,
    conditions        text                            not null
);