package com.parag.jsonevaluator.parser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Json parsing operations
 * 
 * @author parag
 *
 */
public class JsonParser {

    /**
     * converts json string to JSONObject
     * 
     * @param jsonString
     * @return JSONObject
     */
    public JSONObject convertJsonToObject(String jsonString) {
        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject;
    }

    /**
     * Checks if key is present in json
     * Also takes into consideration nested keys
     * 
     * @param jsonObject
     * @param key
     * @return Boolean
     */
    public Boolean isKeyPresent(JSONObject jsonObject, String key) {
        Boolean result = false;
        if (key.contains(".")) {
            JSONObject object = jsonObject;
            String[] parsedNested = key.split("\\.");

            for (int i = 0; i < parsedNested.length; i++) {

                if (i == parsedNested.length - 1) {
                    result = object.has(parsedNested[i]);
                } else {
                    try {
                        object = object.getJSONObject(parsedNested[i]);
                    } catch (JSONException j) {
                        result = false;
                        break;
                    }
                }
            }
        } else {
            result = jsonObject.has(key);
        }
        return result;
    }

    /**
     * Checks for key value pair
     * 
     * @param jsonObject
     * @param key
     * @param value
     * @return Boolean
     */
    public Boolean isValuePresent(JSONObject jsonObject, String key, String value) {
        Boolean result = false;
        if (key.contains(".")) {
            JSONObject object = jsonObject;
            String[] parsedNested = key.split("\\.");

            for (int i = 0; i < parsedNested.length; i++) {

                if (i == parsedNested.length - 1) {
                    result = objectType(value).equals(object.get(parsedNested[i]));
                } else {
                    try {
                        object = object.getJSONObject(parsedNested[i]);
                    } catch (JSONException j) {
                        result = false;
                        break;
                    }
                }
            }
        } else {
            try {
            result = objectType(value).equals(jsonObject.get(key));
            } catch (JSONException j) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Checking for object type
     * 
     * @param x
     * @return Object
     */
    private Object objectType(String x) {
        if (x.contains("'")) {
            return x.replaceAll("'", "").toString();
        } else if (x.matches("[0-9.]*")) {
            if (x.contains(".")) {
                return Double.parseDouble(x);
            } else {
                return Integer.parseInt(x);
            }
        } else {
            return Boolean.parseBoolean(x);
        }
    }

}
