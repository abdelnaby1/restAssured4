package com.restassured.module8;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;

public class _1ConfigDemo {
    public static final String BASE_URL = "https://api.github.com";

    @Test
    public void maxRedirectesTest() {
        RestAssured.config = RestAssured.config()
                // max limit of redirects
                .redirect(RedirectConfig.redirectConfig().followRedirects(true).maxRedirects(1));
        RestAssured.get(BASE_URL + "/repos/twitter/bootstrap")
                .then()
                .statusCode(200);
    }

}
