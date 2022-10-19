export interface RelevanceCase {
    id: string
    title: string
    tags: {
        [key: string]: string
    }
    relevanceScore: number
    resultCount: number
}