package com.parag.jsonevaluator.parser;

import org.json.JSONObject;

import com.parag.jsonevaluator.evaluator.JsonEvaluator;
import com.parag.jsonevaluator.evaluator.LogicalEvaluator;

import junit.framework.TestCase;

public class ExpressionParserTest extends TestCase {
    
    public void testExpressionParser() {
        
        String json = "{\"hp\" : {\"laptop\" : \"100\"}}";
        
        JsonParser jsonParser = new JsonParser();
        JSONObject jsonObject = jsonParser.convertJsonToObject(json);
        
        JsonEvaluator jsonEvaluator = new JsonEvaluator(jsonParser);
        
        LogicalEvaluator logicalEvaluator = new LogicalEvaluator(jsonEvaluator);
        
        ExpressionParser expressionParser = new ExpressionParser(logicalEvaluator);
        
        String expression1 = "(((hp.laptop == '100') OR false) AND EXISTS hp) OR false";
        String expression2 = "EXISTS laptop";
        String expression3 = "EXISTS hp OR (((hp.laptop == '100') AND (true)) OR (NOT true))";
        
        assertEquals(Boolean.TRUE, expressionParser.parse(jsonObject, expression1));
        assertEquals(Boolean.FALSE, expressionParser.parse(jsonObject, expression2));
        assertEquals(Boolean.TRUE, expressionParser.parse(jsonObject, expression3));
    }
}
