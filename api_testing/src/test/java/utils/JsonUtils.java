package utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class JsonUtils {
    //Convert STRING -> JSON
    public static JSONObject convertJSON(String json) throws JSONException {
        return new JSONObject(json);
    }

    //Get a "property" value
    public static String getValueFromJSON(String json, String propertyName) throws JSONException {
        String value = convertJSON(JsonUtils.convertJSON(json).get("data").toString()).getString(propertyName);
        return  value;
    }

    //Compare expectedResult vs actualResult
    public static boolean areEqualJSON(String expectedResult, String actualResult) throws JSONException {
        boolean areEqual = true;
        //String -> JSON
        JSONObject jsonExpectedResult = JsonUtils.convertJSON(expectedResult);
        JSONObject jsonActualResult = JsonUtils.convertJSON(actualResult);
        //Iterate jsonExpectedResult Data
        Iterator<?> keys = jsonExpectedResult.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String actualValue = jsonActualResult.get(key).toString();
            String expectedValue = jsonExpectedResult.get(key).toString();
            if (actualValue.startsWith("{")) {
                areEqual = areEqualJSON(expectedValue,actualValue);
            } else {
                //DO NOT compare fields with value "EXCLUDE"
                if (expectedValue.equals("EXCLUDE")) {
                    System.out.println("INFO: EXCLUDE, the attribute [" + key + "]");
                } else if (!expectedValue.equals(actualValue)) {
                    areEqual = false;
                }
                System.out.println("INFO: COMPARING, the attribute [" + key + "]:  actual value [" + actualValue + "] vs expected [" + expectedValue + "]");
            }
        }
        return areEqual;
    }
}
