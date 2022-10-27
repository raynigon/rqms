import { SearchQuery } from "./search-query"

export interface RelevanceCaseListItem {
  id: string
  name: string
  labels: {
    [key: string]: string
  }
  relevanceScore: number
  resultCount: number
}


export interface RelevanceCaseDto {
  id: string
  name: string
  labels: {
    [key: string]: string
  },
  query: SearchQuery,
  metric: {
    name: string,
    cutoff: number,
  };
  results: any[]
}
