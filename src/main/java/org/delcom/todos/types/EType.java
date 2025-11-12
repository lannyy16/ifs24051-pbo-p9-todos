package org.delcom.todos.types;

public enum EType {
    INFLOW("Inflow"),
    OUTFLOW("Outflow");

    private final String value;

    EType(String value) { this.value = value; }

    public String getValue() { return value; }
}
