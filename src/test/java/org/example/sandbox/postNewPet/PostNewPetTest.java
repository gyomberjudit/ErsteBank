package org.example.sandbox.postNewPet;

import org.example.sandbox.Common;
import org.example.sandbox.util.body.pet.PostCreatePet;
import org.example.sandbox.util.swagger.definitions.Item;
import org.example.sandbox.util.swagger.definitions.PetBody;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.example.sandbox.util.Tools.generateRandomNumber;
import static org.example.sandbox.util.body.pet.JsonBody.createJsonBody;
import static org.example.sandbox.util.constans.Tags.SMOKE;
import static org.example.sandbox.util.constans.TestData.HYDRAIMAGE;

public class PostNewPetTest extends Common{

    @Test(enabled = true,groups = {SMOKE},description ="description")
    public void Test1(){

        PostCreatePet body = PostCreatePet.builder()
                .PetBody(PetBody.builder()
                        .id(generateRandomNumber())
                        .category(Item.builder()
                                .id(1)
                                .name("Hydra")
                                .build())
                        .name("Princess")
                        .photoUrl(HYDRAIMAGE)
                        .tag(Item.builder()
                                .id(2)
                                .name("cute")
                                .build())
                        .status("available")
                        .build()
                ).build();


        Response  response = postUrl(newPet,createJsonBody(body));
        Assert.assertEquals(response.getStatusCode(),200,"Invalid response code");
    }
}
