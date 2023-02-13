package com.restassured.module4;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class _0Module4 {
    public static final String BASE_URL = "http://api.github.com";

    @Test
    public void peek() {
        RestAssured.get(BASE_URL)
                .peek(); // print the body as header
    }

    @Test
    public void prettyPeek() {
        System.out.println("=========================");
        RestAssured.get(BASE_URL)
                .prettyPeek(); // print the body and headers nicely as json
    }

    @Test
    public void print() {
        System.out.println("=========================");
        RestAssured.get(BASE_URL)
                .print(); // print the body
    }

    @Test
    public void prettyPrint() {
        System.out.println("=========================");
        RestAssured.get(BASE_URL)
                .prettyPrint(); // print the body nicely
    }

}
