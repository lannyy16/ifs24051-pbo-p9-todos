package org.delcom.todos.types;

public enum ESource {
    CASH("Cash"),
    INVESTMENT("Investment"),
    LOANS("Loans"),
    SAVINGS("Savings"),
    OTHERS("Others");

    private final String value;

    ESource(String value) { this.value = value; }

    public String getValue() { return value; }
}
