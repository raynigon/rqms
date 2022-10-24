export interface LabelFilter {
  operator: "AND" | "OR"
  conditions: {
    comparison: "ALWAYS" | "NEVER" | "EQUALS" | "NOT_EQUALS" | "CONTAINS_KEY" | "CONTAINS_VALUE" | "HAS_KEY" | "HAS_VALUE"
    key: string | null
    value: string | null
  }[]
}

/*
{
        operator: "AND",
        conditions: [
          {comparison: "EQUALS", key: ".id", value: "5ff4221e-660c-485e-aedf-f9c88cb34c6b"}
        ]
      }
*/
