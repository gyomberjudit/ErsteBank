package org.example.sandbox.tests;

import org.example.sandbox.Common;
import org.example.sandbox.util.body.pet.PostCreatePet;
import org.example.sandbox.util.swagger.definitions.Item;
import org.example.sandbox.util.swagger.definitions.PetBody;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.example.sandbox.util.body.pet.JsonBody.createJsonBody;
import static org.example.sandbox.util.constans.Tags.SMOKE;
import static org.example.sandbox.util.constans.TestData.SPHINXIMAGE;

public class CreateUpdateDeleteTest extends Common {

    @Test(enabled = true,groups = {SMOKE},description ="description")
    public void Test1(){

//create pet
        PostCreatePet body = PostCreatePet.builder()
                .PetBody(PetBody.builder()
                        .id(2)
                        .category(Item.builder()
                                .id(2)
                                .name("Sphinx")
                                .build())
                        .name("Leon")
                        .photoUrl(SPHINXIMAGE)
                        .tag(Item.builder()
                                .id(2)
                                .name("strong")
                                .build())
                        .status("available")
                        .build()
                ).build();


        Response response = postUrl(newPet,createJsonBody(body));
        Assert.assertEquals(response.getStatusCode(),200,"Invalid response code");

//update pet
        PostCreatePet body2 = PostCreatePet.builder()
                .PetBody(PetBody.builder()
                        .id(2)
                        .category(Item.builder()
                                .id(2)
                                .name("Sphinx")
                                .build())
                        .name("Marvel")
                        .photoUrl(SPHINXIMAGE)
                        .tag(Item.builder()
                                .id(2)
                                .name("brave")
                                .build())
                        .status("pending")
                        .build()
                ).build();

        Response response2 = putUrl(newPet,createJsonBody(body2));
        Assert.assertEquals(response2.getStatusCode(),200,"Invalid response code");

//check update
        Map<String, Integer> pathParams = new TreeMap<>();
        pathParams.put("petId", 2);

        Response response3 = getUrlById(petById, pathParams);
        Assert.assertEquals(response3.getStatusCode(),200,"Invalid response code");

//delete pet
        Response response4 = deleteUrl(petById);
        Assert.assertEquals(response4.getStatusCode(),200,"Invalid response code");

//check deletion
        Response response5 = RestAssured
                .given()
                .relaxedHTTPSValidation()
                .contentType("application/json; charset=UTF-8")
                .pathParams("petId", 2)
                .and()
                .log().all()
                .when()
                .get("https://petstore.swagger.io/v2/pet/{petId}")
                .then()
                .log()
                .all()
                .extract().response();

        Assert.assertEquals(response5.getStatusCode(),404,"Invalid response code");
    }
}
