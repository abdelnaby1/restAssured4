package com.restassured.module7;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class OthersMethods {
    public static final String BASE_URL = "https://api.github.com/user/repos";
    public static final String TOKEN = "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";

    @Test(description = "create a repo")
    public void postTest() {
        RestAssured
                .given()
                .header("Authorization", "token " + TOKEN)
                .body("{\"name\": \"deleteme\"}")
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(201);
    }

    @Test(description = "update a repo")
    public void patchTest() {
        RestAssured
                .given()
                .header("Authorization", "token " + TOKEN)
                .body("{\"name\": \"deleteme-patched\"}")
                .when()
                .patch("https://api.github.com/repos/abdelnaby1/deleteme")
                .then()
                .statusCode(200);
    }

    @Test(description = "delete a repo", dependsOnMethods = "patchTest")
    public void deleteTest() {
        RestAssured
                .given()
                .header("Authorization", "token " + TOKEN)
                .body("{\"name\": \"deleteme-patched\"}")
                .when()
                .delete("https://api.github.com/repos/abdelnaby1/deleteme-patched")
                .then()
                .statusCode(204);
    }
}
