import { SearchQuery } from "./search-query"

export interface AssertionTest {
    id: string
    query: SearchQuery,
    conditions: { code: string }[]
}