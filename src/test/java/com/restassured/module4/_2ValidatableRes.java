package com.restassured.module4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.hamcrest.number.OrderingComparison;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class _2ValidatableRes {
    public static final String BASE_URL = "http://api.github.com";

    // instead of returning response object
    // we can return validatableresponse using then()
    @Test
    public void conveienceMethods() {
        RestAssured.get(BASE_URL)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("x-ratelimit-limit", "60");
    }

    @Test
    public void simpleHamcrestMatchers() {
        RestAssured.get(BASE_URL)
                .then()
                .assertThat()
                .statusCode(Matchers.lessThan(300))
                .and()
                .header("cache-control", Matchers.containsStringIgnoringCase("max-age=60"))
                .and()
                .time(Matchers.lessThan(3L), TimeUnit.SECONDS)
                .and()
                .header("etag", Matchers.notNullValue())
                .and()
                .header("etag", Matchers.not(Matchers.emptyString()))
                // using matcher with mapping function using lambda
                .and()
                .header("x-ratelimit-limit", Integer::parseInt, Matchers.lessThan(100));

    }

    @Test
    public void complexHamcrestMatchers() {
        RestAssured.get(BASE_URL)
                .then()
                .assertThat()
                .header("x-ratelimit-limit", Integer::parseInt, Matchers.lessThan(100))
                .and()
                // .header("date", date -> LocalDate.parse(date,
                // DateTimeFormatter.RFC_1123_DATE_TIME),
                // OrderingComparison.comparesEqualTo(LocalDate.now()));
                .and()
                .header("x-ratelimit-limit",
                        response -> Matchers.greaterThan(response.header("x-ratelimit-remaining")));
    }
}
