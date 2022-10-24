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
  query: {
    type: string,
    searchTerm: string,
    parameters: {
      [key: string]: string
    }
  },
  metric: {
    name: string,
    cutoff: number,
  };
  results: any[]
}
