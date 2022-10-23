export interface RelevanceCaseListItem {
  id: string
  name: string
  labels: {
    [key: string]: string
  }
  relevanceScore: number
  resultCount: number
}
