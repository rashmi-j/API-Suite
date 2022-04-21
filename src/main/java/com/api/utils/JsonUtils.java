package com.api.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonUtils {

    public static JSONObject getJsonFileObj(String jsonFilePath){

        JSONParser jsonParser =  new JSONParser();
        /*** Create JSONParser object to convert json file to readable type ***/
        Object object = null;
        JSONObject jsonObject = null;
        //above 18 and 19 we are creating variables fir object and jsonObject

        try {
            object = jsonParser.parse(new FileReader(jsonFilePath));
            /*** jsonParser.parse requires file reader type(file reader converts given json file to input stream/ readable type)
             * line jsonParser.parse returns object type. We want return in JSONObject type***/

            jsonObject = (JSONObject) object;
            /*** type cast to  JSONObject type***/
        } catch ( Exception e) {
            e.printStackTrace();
        }
        return jsonObject;

    }
//   public static void main(String[] args) {
//       JSONObject jsobj= getJsonFileObj("src/main/resources/NewPet.json");
//    }

}