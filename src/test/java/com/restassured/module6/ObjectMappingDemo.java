package com.restassured.module6;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restassured.User;

import io.restassured.RestAssured;
import io.restassured.internal.mapping.Jackson2Mapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;

public class ObjectMappingDemo {
    public static final String BASE_URL = "https://api.github.com/users/rest-assured";

    @Test
    public void objectMappingTestOne() {
        User user = RestAssured.get(BASE_URL)
                .as(User.class);
        Assert.assertEquals(user.getLogin(), "rest-assured");
        Assert.assertEquals(user.getId(), 19369327);
        Assert.assertEquals(user.getPublicRepos(), 2);

    }

    @Test // recomended for me
    public void objectMappingRelyingOnMapperType() {
        User user = RestAssured.get(BASE_URL)
                .as(User.class, ObjectMapperType.JACKSON_2);
        Assert.assertEquals(user.login, "rest-assured");

    }

    @Test
    public void objectMappingUsingSpecifiedMapper() {
        io.restassured.mapper.ObjectMapper mapper = new Jackson2Mapper(new Jackson2ObjectMapperFactory() {
            @Override
            public ObjectMapper create(java.lang.reflect.Type type, String s) {
                ObjectMapper om = new ObjectMapper();
                om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                return om;
            }

        });
        User user = RestAssured.get(BASE_URL)
                .as(User.class, mapper);

        Assert.assertEquals(user.login, "rest-assured");

    }
}
