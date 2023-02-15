package com.restassured.module5;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class _2ValidatableRsponseRepeatingItems {
    public static final String BASE_URL = "https://reqres.in/api/users?page=1";

    @Test
    public void repeatingItems() {
        RestAssured.get(BASE_URL)
                .then()
                .body("data.id[0]", Matchers.equalTo(1))
                .body("data.id[1]", Matchers.equalTo(2))
                // or verify all elements
                .body("data.first_name", Matchers.hasItems("Emma", "Eve"));
        // .body("data.first_name",Matchers.containsInAnyOrder("Emma","Eve")) // verify
        // all elements so you need to specify all elements

    }
    // co change the default parser
    // RestAssured.get(BASE_URL)
    // .then()
    // .using().defaultParser(Parser.XML)

}
