package com.api.tests;

import com.api.endPoints.Pets;
import com.api.endPoints.Users;
import com.api.utils.JsonUtils;
import com.jayway.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

public class UserTest {

    Date date;

    @Test(description = "verify create user api")
    public void createUser(){
        date = new Date();

        JSONObject requestBody = JsonUtils.getJsonFileObj("src/main/resources/CreateSingleUser.json");
        String userName = "User"+date.getTime();
        String firstName = userName;
        String lastName = "Joseph";
        String email = userName+"@gmail.com";
        String phoneNo = "1"+date.getTime();
        String password = userName;
        requestBody.put("userName",userName);
        requestBody.put("firstName",firstName);
        requestBody.put("lastName",lastName);
        requestBody.put("email",email);
        requestBody.put("phoneNo",phoneNo);
        requestBody.put("password",password);
        /** data preparation **/

        Response createUserResponse = Users.createUser(requestBody.toString(),200);
        System.out.println(createUserResponse.body().asString());
        /***^Api call**/

        Assert.assertFalse(createUserResponse.jsonPath().get("message").toString().isEmpty());
        /*** Assertions ***/
    }
}
