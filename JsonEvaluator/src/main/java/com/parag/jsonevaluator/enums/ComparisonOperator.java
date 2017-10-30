package com.parag.jsonevaluator.enums;

/**
 * Contains comparison operators
 * 
 * @author parag
 */
public enum ComparisonOperator {

    EQUALS("=="), //
    EXISTS("EXISTS"); //

    private String value;

    private ComparisonOperator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
