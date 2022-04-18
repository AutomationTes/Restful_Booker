package com.restful_booker.utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import static io.restassured.RestAssured.given;

public class APIUtils {

    public static String randomDateGenerator(int start_yyyy, int start_mm, int start_dd,int end_yyyy,int end_mm, int end_dd){
        LocalDate from = LocalDate.of(start_yyyy, start_mm, start_dd);
        LocalDate to = LocalDate.of(end_yyyy, end_mm, end_dd);
        long days = from.until(to, ChronoUnit.DAYS);
        long randomDays = ThreadLocalRandom.current().nextLong(days + 1);
        LocalDate randomDate = from.plusDays(randomDays);

           return randomDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String getAccessToken(){

        String url = ConfigurationReader.get("urlToken");
        Map<String, String> tokenBody = new HashMap<>();
        tokenBody.put("username", ConfigurationReader.get("username"));
        tokenBody.put("password", ConfigurationReader.get("password"));

        Response response = given().accept(ContentType.JSON)
                .contentType("application/json")
                .body(tokenBody)
                .post(url);

        String token = response.path("token");

            return token;
    }

}
