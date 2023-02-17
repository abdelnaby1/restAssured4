package com.restassured.module8;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class _0GlobalConstantsDemo {

    @BeforeSuite
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/";
        RestAssured.basePath = "api/users";
        RestAssured.rootPath = "data";
    }

    @Test
    public void testOneUsingGlobalConstants() {
        RestAssured.get()
                .then()
                .body("id[0]", Matchers.is(1));
    }

    @Test
    public void testTwoUsingGlobalConstants() {
        RestAssured.get()
                .then()
                .body("id[1]", Matchers.is(2));
    }

}
