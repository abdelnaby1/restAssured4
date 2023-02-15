package com.restassured.module5;

import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class _0BasicResponseBody {
    public static final String BASE_URL = "https://api.github.com";

    @Test
    public void jsonPathReturnMap() {
        Response response = RestAssured.get(BASE_URL + "/rate_limit");
        ResponseBody<?> body = response.body();
        JsonPath jPath = body.jsonPath();
        Map<String, String> fullJson = jPath.get();
        Map<String, String> subMap = jPath.get("resources");
        Map<String, String> subMap2 = jPath.get("resources.core");

        int value = jPath.get("resources.core.limit");
        int value2 = jPath.get("resources.graphql.limit");
        // System.out.println(value);
        // System.out.println(subMap);
    }

    // get body by fluent interface
    @Test
    public void matcherExample() {
        RestAssured.get(BASE_URL)
                .then()
                .body("current_user_url", Matchers.equalTo(BASE_URL + "/user"))
                .body(Matchers.containsString("feeds_url"))
                .body(Matchers.containsString("feeds_url"), Matchers.containsString("current_user_url"));

    }

    @DataProvider
    public Object[][] names() {
        return new Object[][] {
                { "abdelnaby1" },
                { "rest-assured" }
        };
    }

    @Test(dataProvider = "names")
    public void complexBodyExample(String name) {
        RestAssured.get(BASE_URL + "/users/" + name)
                .then()
                .body("url", response -> Matchers.containsString(response.body().jsonPath().get("login")));

    }

}
