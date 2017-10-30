package com.parag.jsonevaluator.parser;

import org.json.JSONObject;

import com.parag.jsonevaluator.enums.LogicalOperator;
import com.parag.jsonevaluator.evaluator.LogicalEvaluator;

/**
 * Responsible for parsing the expression passed
 * 
 * @author parag
 *
 */
public class ExpressionParser {

    private LogicalEvaluator logicalEvaluator;

    /**
     * Constructor
     * 
     * @param logicalEvaluator
     */
    public ExpressionParser(LogicalEvaluator logicalEvaluator) {
        this.logicalEvaluator = logicalEvaluator;
    }

    /**
     * Starting point of our program
     * 
     * @param jsonObject
     * @param expression
     * @return Boolean
     */
    public Boolean parse(JSONObject jsonObject, String expression) {

        // Checks for Brackets and recursively solves one bracket at a time

        while (expression.contains(LogicalOperator.OPENING_BRACKET.getValue())) {
            expression = reduce(jsonObject, expression);
        }
        return logicalEvaluator.evaluate(jsonObject, expression);
    }

    /**
     * Replacing the smallest bracket expression with the evaluated boolean String value
     * 
     * @param jsonObject
     * @param expression
     * @return String
     */
    private String reduce(JSONObject jsonObject, String expression) {
        return expression.replace(
                LogicalOperator.OPENING_BRACKET.getValue() + subStringInBraces(expression) + LogicalOperator.CLOSING_BRACKET.getValue(),
                logicalEvaluator.evaluate(jsonObject, subStringInBraces(expression)).toString());
    }

    /**
     * Parsing the smallest block of expression inside brackets Starting with the last block of brackets first
     * 
     * @param expression
     * @return
     */
    private String subStringInBraces(String expression) {
        String subString = expression.substring(expression.lastIndexOf(LogicalOperator.OPENING_BRACKET.getValue()) + 1, //
                expression.substring(expression.lastIndexOf(LogicalOperator.OPENING_BRACKET.getValue())).indexOf(
                        LogicalOperator.CLOSING_BRACKET.getValue()) + expression.lastIndexOf(LogicalOperator.OPENING_BRACKET.getValue()));
        return subString;
    }
}
