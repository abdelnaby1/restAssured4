package com.restassured.module5;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class _1ValidatableNestedBody {
    public static final String BASE_URL = "https://api.github.com";

    // enhance path of body
    @Test
    public void nestedBodyValidation() {
        RestAssured.get(BASE_URL + "/rate_limit")
                .then()
                .rootPath("resources.core")
                .body("limit", Matchers.equalTo(60))
                .body("remaining", Matchers.lessThanOrEqualTo(60))
                .body("reset", Matchers.notNullValue())
                .rootPath("resources.search")
                .body("limit", Matchers.equalTo(10))
                .body("remaining", Matchers.lessThanOrEqualTo(10))
                .noRootPath()
                .body("resources.graphql.limit", Matchers.equalTo(0));

    }
}
