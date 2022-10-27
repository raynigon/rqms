export interface SearchQuery {
    type: string,
    searchTerm: string,
    parameters: {
        [key: string]: string
    }
}