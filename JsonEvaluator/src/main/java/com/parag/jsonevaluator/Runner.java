package com.parag.jsonevaluator;

import org.json.JSONObject;

import com.parag.jsonevaluator.evaluator.JsonEvaluator;
import com.parag.jsonevaluator.evaluator.LogicalEvaluator;
import com.parag.jsonevaluator.parser.ExpressionParser;
import com.parag.jsonevaluator.parser.JsonParser;

public class Runner {
    public static void main(String[] args) {
        
        String json = "{\"parag\" : {\"pranu\" : \"100\"}}";
        String expression = "(((parag.pranu == '100') OR false) AND EXISTS parag) OR false";
        
        JsonParser jsonParser = new JsonParser();
        JSONObject jsonObject = jsonParser.convertJsonToObject(json);
        
        JsonEvaluator jsonEvaluator = new JsonEvaluator(jsonParser);
        
        LogicalEvaluator logicalEvaluator = new LogicalEvaluator(jsonEvaluator);
        
        ExpressionParser expressionParser = new ExpressionParser(logicalEvaluator);
        
        System.out.println(expressionParser.parse(jsonObject, expression));
    }
}
