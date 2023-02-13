package com.restassured.module4;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class _1BasicResponse {
    public static final String BASE_URL = "http://api.github.com";

    @Test
    public void conveienceMethods() {
        Response response = RestAssured.get(BASE_URL);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
    }

    @Test
    public void genericHeader() {
        Response response = RestAssured.get(BASE_URL);
        Assert.assertEquals(response.getHeader("server"), "GitHub.com");
        Assert.assertEquals(response.getHeader("x-ratelimit-limit"), "60");

    }

    @Test
    public void getHeaders() {
        Response response = RestAssured.get(BASE_URL);

        Headers headers = response.getHeaders();
        String val = headers.getValue("header1");
        int size = headers.size();
        List<Header> list = headers.asList();
    }

    @Test
    public void getResTime() {
        Response response = RestAssured.get(BASE_URL);
        response.getTime();
        // or
        response.time();
        // or
        response.getTimeIn(TimeUnit.MINUTES);
    }
}
