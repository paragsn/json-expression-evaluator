package com.parag.jsonevaluator.evaluator;

import org.json.JSONObject;

import com.parag.jsonevaluator.enums.ComparisonOperator;
import com.parag.jsonevaluator.parser.JsonParser;

/**
 * Json Evaluator
 * 
 * @author parag
 *
 */
public class JsonEvaluator {

    private JsonParser jsonParser;

    public JsonEvaluator(JsonParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    /**
     * Evaluates expression against JSON
     * 
     * @param jsonObject
     * @param expression
     * @return Boolean
     */
    public Boolean evaluate(JSONObject jsonObject, String expression) {
        Boolean result = false;
        if (expression.contains(ComparisonOperator.EQUALS.getValue())) {
            String[] keyValue = expression.split(ComparisonOperator.EQUALS.getValue());
            result = jsonParser.isValuePresent(jsonObject, keyValue[0].trim(), keyValue[1].trim());
        } else if (expression.contains(ComparisonOperator.EXISTS.getValue())) {
            String[] keyValue = expression.split(ComparisonOperator.EXISTS.getValue());
            result = jsonParser.isKeyPresent(jsonObject, keyValue[1].trim());
        } else {
            result = Boolean.parseBoolean(expression);
        }
        return result;
    }

}
