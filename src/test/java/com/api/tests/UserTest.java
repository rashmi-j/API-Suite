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
        String username = "User"+date.getTime();
        String firstName = username;
        String lastName = "Joseph";
        String email = username+"@gmail.com";
        String phoneNo = "1"+date.getTime();
        String password = username;
        requestBody.put("username",username);
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

    @Test(description = "Verify get user api")
    public void getUserByUserName(){
        date = new Date();

        JSONObject requestBody = JsonUtils.getJsonFileObj("src/main/resources/CreateSingleUser.json");
        String username = "User"+date.getTime();
        String firstName = username;
        String lastName = "Joseph";
        String email = username+"@gmail.com";
        String phoneNo = "1"+date.getTime();
        String password = username;
        requestBody.put("username",username);
        requestBody.put("firstName",firstName);
        requestBody.put("lastName",lastName);
        requestBody.put("email",email);
        requestBody.put("phoneNo",phoneNo);
        requestBody.put("password",password);

        Response createUserResponse = Users.createUser(requestBody.toJSONString(), 200);
        /** data preparation **/
//        System.out.println(createUserResponse.body().asString());
        Response getResponse = Users.getUser(username,200);
        System.out.println(getResponse.body().asString());

        /***^API call**/

        String actualUserName = getResponse.jsonPath().get("username").toString();
        String id = getResponse.jsonPath().get("id").toString();
        String actualEmail = getResponse.jsonPath().get("email").toString();
        /***^Assertions**/

        Assert.assertEquals(username,actualUserName);
        Assert.assertEquals(email,actualEmail);
        Assert.assertFalse(id.isEmpty());
    }
}
