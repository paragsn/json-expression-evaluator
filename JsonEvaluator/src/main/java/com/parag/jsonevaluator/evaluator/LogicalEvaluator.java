package com.parag.jsonevaluator.evaluator;

import org.json.JSONObject;

import com.parag.jsonevaluator.enums.LogicalOperator;

/**
 * Solves Logical Operations
 * 
 * @author parag
 *
 */
public class LogicalEvaluator {
    
    private JsonEvaluator jsonEvaluator;
    
    /**
     * Constructor
     * 
     * @param jsonEvaluator
     */
    public LogicalEvaluator(JsonEvaluator jsonEvaluator) {
        this.jsonEvaluator = jsonEvaluator;
    }

    /**
     * Evalutes the expression containing Logical Operators
     * 
     * @param jsonObject
     * @param expression
     * @return Boolean
     */
    public Boolean evaluate(JSONObject jsonObject, String expression) {

        Boolean result = false;

        if (expression.contains(LogicalOperator.AND.getValue())) {
            String[] exps = expression.split(LogicalOperator.AND.getValue());
            result = jsonEvaluator.evaluate(jsonObject, exps[0].trim()) && jsonEvaluator.evaluate(jsonObject, exps[1].trim());

        } else if (expression.contains(LogicalOperator.OR.getValue())) {
            String[] exps = expression.split(LogicalOperator.OR.getValue());
            result = jsonEvaluator.evaluate(jsonObject, exps[0].trim()) || jsonEvaluator.evaluate(jsonObject, exps[1].trim());

        } else if (expression.contains(LogicalOperator.NOT.getValue())) {
            String[] exps = expression.split(LogicalOperator.NOT.getValue());
            result = !jsonEvaluator.evaluate(jsonObject, exps[1].trim());

        } else {
            result = jsonEvaluator.evaluate(jsonObject, expression.trim());
        }
        return result;
    }

}
