package com.api.tests;

import com.api.endPoints.Pets;
import com.api.utils.JsonUtils;
import com.jayway.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Date;

public class PetTest {

Date date;

    @Test(description = "Create new pet and verify the creation")
    public void createNewPetAndVerify(){

        date = new Date();
        String name = "kitto"+date.getTime();
        JSONObject requestBody = JsonUtils.getJsonFileObj("src/main/resources/createNewPet.json");
        requestBody.put("name",name);
        /*** data preparation ***/

        /*System.out.println(requestBody.toString());*/

        Response response = Pets.createNewPet(requestBody.toString(), 200);
        /*** Api call ***/

        String actualName = response.jsonPath().get("name").toString();
        String idOfPet = response.jsonPath().get("id").toString();

        Assert.assertEquals(name,actualName);
        //Assert.assertEquals(idOfPet.isEmpty());
        /*** Assertions ***/

    }

    @Test (description = "Verify pet by id")
    public void verifyPetByID(){
        Date d = new Date();
        String name = "Kitto"+d.getTime();
        JSONObject requestBody=  JsonUtils.getJsonFileObj("src/main/resources/createNewPet.json");
        requestBody.put("name", name);


        System.out.println(requestBody.toString());

        Response response = Pets.createNewPet(requestBody.toString(), 200);
        String idOfPet = response.jsonPath().get("id").toString();

        Response resp = Pets.getPetByID(idOfPet, 200);
        Assert.assertEquals(name, resp.jsonPath().get("name"));
    }


}


