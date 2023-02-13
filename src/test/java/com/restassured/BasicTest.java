package com.restassured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class BasicTest {

    @Test
    public void basic() {
        RestAssured.get("http://api.github.com") // return response object
                .then() // return validateableResponse
                .assertThat() // syntacit suger
                .statusCode(200);
    }
}
