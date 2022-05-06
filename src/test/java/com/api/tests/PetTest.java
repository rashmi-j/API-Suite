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
        /*** Line 20 to 22 is for data preparation ***/

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
        /*** ^^^Data preparation***/

        System.out.println(requestBody.toString());

        Response response = Pets.createNewPet(requestBody.toString(), 200);
        String idOfPet = response.jsonPath().get("id").toString();
        /*** Api call ***/

        Response resp = Pets.getPetByID(idOfPet, 200);
        Assert.assertEquals(name, resp.jsonPath().get("name"));
        /*** Assertions ***/
    }

    @Test (description = "Verify pet status")
    public void verifyPetStatus(){

       Response response = Pets.getPetStatus(200,"available");
       Assert.assertEquals(response.jsonPath().get("status[0]"),"available");
    }

    @Test (description = "Verify update pet tests")
    public void verifyUpdatePetTests(){

        date = new Date();
        String name = "Kitto"+date.getTime();
        String expectedName = "Pet 1";
        String expectedTagName = "updated_pet";
        String expectedFamily = "Big cats";

        JSONObject requestBody = JsonUtils.getJsonFileObj("src/main/resources/createNewPet.json");
        requestBody.put("name",name);

        /*** ^^^Data preparation***/

        Response response =  Pets.createNewPet(requestBody.toString(),200);
        /*** ^^Create Pet for updating***/

        JSONObject requestBodyUpdate = JsonUtils.getJsonFileObj("src/main/resources/UpdatePet.json");
        requestBodyUpdate.put("id", response.jsonPath().get("id").toString());
        /*** ^^data preparation for updating***/

        Response updateResponse = Pets.updatePet(200,requestBodyUpdate.toString());
        /*** update api call***/

        System.out.println(updateResponse.body().asString());

        Assert.assertEquals(expectedName,updateResponse.jsonPath().get("name").toString());
        Assert.assertEquals(expectedTagName,updateResponse.jsonPath().get("tags[0].name").toString());
        Assert.assertEquals(expectedFamily, updateResponse.jsonPath().get("category.name").toString());
        /*** Update api test assertions***/

    }

    @Test (description = "verify updating pet image")
    public void updatePetImage(){

        Date d = new Date();
        String name = "Kitto"+d.getTime();
        JSONObject requestBody = JsonUtils.getJsonFileObj("src/main/resources/CreateNewpetWithoutImage.json");
        requestBody.put("name",name);

        Response createResponse = Pets.createNewPet(requestBody.toString(),200);
        String petId = createResponse.jsonPath().get("id").toString();

        Response updateResponse = Pets.uploadPetImage(petId,"src/test/resources/Kitty.jpg",200 );
        /** checkout how to add image under resources , if its img url or actual img**/

        Response getPet = Pets.getPetByID(petId, 200);
        Assert.assertFalse(getPet.jsonPath().get("photoUrls").toString().isEmpty());

    }
}


