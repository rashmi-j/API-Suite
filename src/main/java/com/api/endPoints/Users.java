package com.api.endPoints;

import com.api.utils.Loadconfig;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

public class Users {

    public static Response createUserWithArray(String reqBody, int statusCode){
        Loadconfig lf = new Loadconfig();

        String endPoint = lf.HOST+"/user/createWithArray";
        Response response = given().log().ifValidationFails().header("Content-Type","application/json")
                .accept("application/json").body(reqBody)
                .when().post(endPoint)
                .then().assertThat().statusCode(statusCode).extract().response();
        return response;
    }

    public static Response createUserWithList(String reqBody, int statusCode){
        Loadconfig lf = new Loadconfig();

        String endPoint = lf.HOST+"/user/createWithList";
        Response response = given().log().ifValidationFails().header("Content-Type","application/json")
                .accept("application/json").body(reqBody)
                .when().post(endPoint)
                .then().assertThat().statusCode(statusCode).extract().response();
        return response;
    }

    public static Response getUser(String userName,int statusCode){
        Loadconfig lf = new Loadconfig();

        String endPoint = lf.HOST+"/user"+userName;
        Response response = given().log().ifValidationFails().header("Content-Type","application/json")
                .accept("application/json")
                .when().get(endPoint)
                .then().assertThat().statusCode(statusCode).extract().response();
        return response;
    }

    public static Response updateUser(String userName, String reqBody, int statusCode){
        Loadconfig lf = new Loadconfig();

        String endPoint = lf.HOST+"/user"+userName;
        Response response = given().log().ifValidationFails().header("Content-Type","application/json")
                .accept("application/json").body(reqBody)
                .when().put(endPoint)
                .then().assertThat()
                .statusCode(statusCode).extract()
                .response();
        return response;
    }

    public static Response deleteUser(String userName, int statusCode){
        Loadconfig lf = new Loadconfig();

        String endPoint = lf.HOST+"/user"+userName;
        Response response = given().log().ifValidationFails()
                .header("Content-Type","application/json")
                .accept("application/json")
                .when().delete(endPoint)
                .then().assertThat()
                .statusCode(statusCode).extract()
                .response();
        return response;
    }

    public static Response userLogin(String userName,String password, int statusCode){
        Loadconfig lf = new Loadconfig();

        //String endPoint = lf.HOST+"/user/login";
        String endPoint = lf.HOST+"/user/login?username="+userName+"&password="+password;
        /**Doubt on above line on how to pass username and pwd **/

        Response response = given().log().ifValidationFails()
                .header("Content-Type","application/json")
                .accept("application/json")
                .when().get(endPoint)
                .then().assertThat().statusCode(statusCode).extract().response();
        return response;
    }

    public static Response createUser(String reqBody, int statusCode){
        Loadconfig lf = new Loadconfig();

        String endPoint = lf.HOST+"/user";

        Response response = given().log().ifValidationFails()
                .header("Content-Type","application/json")
                .accept("application/json").body(reqBody)
                .when().post(endPoint)
                .then()
                .assertThat().statusCode(statusCode).extract().response();
        return response;
    }

}
