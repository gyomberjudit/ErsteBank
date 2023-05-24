package org.example.sandbox.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.sandbox.Common;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.example.sandbox.util.constans.Tags.SMOKE;

public class SuccessTest extends Common {

    @Test(enabled = true,groups = {SMOKE},description ="description")
    public void responseCodeTest() {

        Response  response = getUrl(newPet);
        Assert.assertEquals(response.getStatusCode(),200,"Invalid response code");
    }

    @Test(enabled = true,groups = {SMOKE},description ="description")
    public void postResponseCodeTest() {
        Response response = RestAssured
                .given()
                .when()
                .get(baseUrl+newPet)
                .then()
                .contentType("application/json")
                .extract().response();

        int actual = response.statusCode();

        Assertions.assertEquals(200, actual);
    }
}
