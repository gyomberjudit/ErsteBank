package org.example.sandbox.tests;

import org.example.sandbox.Common;
import org.example.sandbox.util.Assertions;
import org.example.sandbox.util.body.pet.PostCreatePet;
import org.example.sandbox.util.body.pet.PostCreateStore;
import org.example.sandbox.util.body.pet.PostCreateUser;
import org.example.sandbox.util.swagger.definitions.Item;
import org.example.sandbox.util.swagger.definitions.PetBody;
import org.example.sandbox.util.swagger.definitions.StoreOrderBody;
import org.example.sandbox.util.swagger.definitions.UserBody;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.*;

import static org.example.sandbox.util.Tools.generateRandomNumber;
import static org.example.sandbox.util.body.pet.JsonBody.*;
import static org.example.sandbox.util.constans.Tags.SMOKE;
import static org.example.sandbox.util.constans.TestData.HAMSTERIMAGE;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
public class EndpointsTest extends Common {

    Assertions assertions = new Assertions();

    @Test(enabled = true,groups = {SMOKE},description ="description")
    public void testPet() {
        PostCreatePet body = PostCreatePet.builder()
                .PetBody(PetBody.builder()
                        .id(2)
                        .category(Item.builder()
                                .id(2)
                                .name("Hamster")
                                .build())
                        .name("Lara")
                        .photoUrl(HAMSTERIMAGE)
                        .tag(Item.builder()
                                .id(2)
                                .name("cute")
                                .build())
                        .status("available")
                        .build()
                ).build();

        Response  response = postUrl(newPet, createJsonBody(body));
        assertions.assertReturnCode(response, 200);
    }

    @Test(enabled = true,groups = {SMOKE},description ="description")
    public void testPetById() {
        Response response = RestAssured
                .given()
                .pathParam("petId", "2")
                .when()
                .get("https://petstore.swagger.io/v2/pet/{petId}")
                .then()
                .contentType("application/json")
                .extract().response();

        assertions.assertReturnCode(response, 200);
    }
    @Test(enabled = true,groups = {SMOKE},description ="description")
    public void testPetById2() {
        when()
                .get("https://petstore.swagger.io/v2/pet/{petId}", 2)
                .then()
                .statusCode(200)
                .body("id", equalTo(2));
    }
    @Test(enabled = true,groups = {SMOKE},description ="description")
    public void testFindByStatus() {
        Map<String, String> queryParams = new TreeMap<>();
        queryParams.put("status","available");

        Response  response = getUrl(findByStatus, queryParams);
        assertions.assertReturnCode(response, 200);
    }
    @Test(enabled = true,groups = {SMOKE},description ="description")
    public void testFindByStatus2() {
        when()
                .get("https://petstore.swagger.io/v2/pet/findByStatus")
                .then()
                .statusCode(200);

    }
    @Test(enabled = true,groups = {SMOKE},description ="description")
    public void testUploadImage() {

        Response response = RestAssured
                .given()
                .pathParam("petId", "2")
                .formParams("file", HAMSTERIMAGE)
                .when()
                .get("https://petstore.swagger.io/v2/pet/{petId}/uploadImage")
                .then()
                .contentType("application/xml")
                .extract().response();

        assertions.assertReturnCode(response, 200);
    }
    @Test(enabled = true,groups = {SMOKE},description ="description")
    public void testOrder() {
        PostCreateStore body = PostCreateStore.builder()
                .StoreOrderBody(StoreOrderBody.builder()
                        .id(generateRandomNumber())
                        .petId(generateRandomNumber())
                        .quantity(generateRandomNumber())
                        .shipDate("2023-05-22T19:14:52.056Z")
                        .status("placed")
                        .complete(true)
                        .build()
                ).build();

        Response  response = postUrl(order, createJsonBody2(body));
        assertions.assertReturnCode(response, 200);
    }
    @Test(enabled = true,groups = {SMOKE},description ="description")
    public void testInventory() {
        Response  response = getUrl(inventory);
        assertions.assertReturnCode(response, 200);
    }
    @Test(enabled = true,groups = {SMOKE},description ="description")
    public void testInventory2() {
        when()
                .get("https://petstore.swagger.io/v2/store/inventory")
                .then()
                .statusCode(200);
    }
    @Test(enabled = true,groups = {SMOKE},description ="description")
    public void testCreateWithList() {
        Map<String, Object> user1 = new HashMap<>();
        user1.put("id", 6);
        user1.put("username", "she");
        user1.put("firstName", "Eni");
        user1.put("lastName", "Test");
        user1.put("email", "eni@test.com");
        user1.put("password", "something");
        user1.put("phone", "123456");
        user1.put("userStatus", 1);

        Map<String, Object> user2 = new HashMap<>();
        user1.put("id", 10);
        user1.put("username", "he");
        user1.put("firstName", "Elek");
        user1.put("lastName", "Test");
        user1.put("email", "elek@test.com");
        user1.put("password", "pwd");
        user1.put("phone", "654321");
        user1.put("userStatus", 2);

        List<Map<String, Object>> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        Response response = RestAssured
                .given()
                .body(users)
                .when()
                .post("https://petstore.swagger.io/v2/user/createWithList")
                .then()
                .contentType("application/json")
                .extract().response();

        assertions.assertReturnCode(response, 200);
    }
    @Test(enabled = true,groups = {SMOKE},description ="description")
    public void testCreateWithList2() {
        when()
                .get("https://petstore.swagger.io/v2/store/inventory")
                .then()
                .statusCode(200);
    }
    @Test(enabled = true,groups = {SMOKE},description ="description")
    public void testLogin() {
        Map<String, String> queryParams = new TreeMap<>();
        queryParams.put("username","somebody");
        queryParams.put("password","password");

        Response  response = getUrl(login, queryParams);
        assertions.assertReturnCode(response, 200);
    }
    @Test(enabled = true,groups = {SMOKE},description ="description")
    public void testLogin2() {
        when()
                .get("https://petstore.swagger.io/v2/user/login")
                .then()
                .statusCode(200);
    }
    @Test(enabled = true,groups = {SMOKE},description ="description")
    public void testUser() {
        PostCreateUser body = PostCreateUser.builder()
                .UserBody(UserBody.builder()
                        .id(generateRandomNumber())
                        .username("somebody")
                        .firstName("first")
                        .lastName("last")
                        .email("somebody@gmail.com")
                        .password("password")
                        .phone("0123456")
                        .userStatus(1)
                        .build()
                ).build();

        Response  response = putUrl2(user, createJsonBody3(body));
        assertions.assertReturnCode(response, 200);
    }
    @Test(enabled = true,groups = {SMOKE},description ="description")
    public void testLogout() {
        Response  response = getUrl(logout);
        assertions.assertReturnCode(response, 200);
    }
    @Test(enabled = true,groups = {SMOKE},description ="description")
    public void testLogout2() {
        when()
                .get("https://petstore.swagger.io/v2/user/logout")
                .then()
                .statusCode(200);
    }

}
