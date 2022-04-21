package com.api.endPoints;

import com.api.utils.Loadconfig;
import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.given;

public class Pets {

    static Loadconfig lf;

    public static Response getPetById(String petId, int statusCode){

        lf = new Loadconfig();

        String endPoint = lf.HOST+"/pet"+petId;

        Response response = given().log().ifValidationFails()
                .header("Content-Type","application.json")
                .when().get(endPoint)
                .then().assertThat().statusCode(statusCode)
                .extract().response();

        return response;
    }

    public static Response getPetStatus(String status, int statusCode){

        lf = new Loadconfig();

        String endPoint = lf.HOST+"/pet/findByStatus?status=available";

        Response response = given().log().ifValidationFails()
                .header("Content-Type","application.json")
                .when().get(endPoint)
                .then().assertThat().statusCode(statusCode)
                .extract().response();

        return response;
    }

    public static Response createNewPet(String body, int statusCode){
        lf = new Loadconfig();

        String endPoint = lf.HOST+"/pet";

        Response response=given().log().ifValidationFails()
                .header("Content-Type", "application/json").body(body)
                .when().post(endPoint)
                .then().assertThat().statusCode(statusCode).extract().response();
        return response;
    }


    public static Response updatePet(int statusCode, String reqBody){

        lf = new Loadconfig();

        String endPoint = lf.HOST+"/pet";

        Response response = given().log().ifValidationFails()
                .header("Content-Type","application/json")
                .accept("application/json")
                .body(reqBody)
                .when().put(endPoint)
                .then()
                .assertThat().statusCode(statusCode).extract().response();
        return response;
    }
}