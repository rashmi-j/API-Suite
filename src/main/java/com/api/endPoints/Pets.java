package com.api.endPoints;

import com.api.utils.Loadconfig;
import com.jayway.restassured.response.Response;

import java.io.File;

import static com.jayway.restassured.RestAssured.given;

public class Pets {

    static Loadconfig lf;

    public static Response getPetByID(String petID, int statusCode) {
        lf = new Loadconfig();

        String endPoint = lf.HOST + "/pet/" + petID;

        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .when().get(endPoint)
                .then().assertThat().statusCode(statusCode).extract().response();
        return response;

    }

    public static Response getPetStatus(int statusCode, String status) {
        lf = new Loadconfig();

        String endPoint = lf.HOST + "/pet/findByStatus?status=available";

        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
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

    public static Response uploadPetImage(String petId, String filePath, int statusCode){

        String endPoint = lf.HOST +"/pet/"+petId+"/uploadImage";

        Response response = given().log().ifValidationFails().header("ContentType","multipart/form-data")
                .accept("application/json")
                .multiPart("file",new File(filePath))
                .when().post(endPoint)
                .then().assertThat().statusCode(statusCode).extract().response();
        return response;
    }

}
