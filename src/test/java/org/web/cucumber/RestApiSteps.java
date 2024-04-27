package org.web.cucumber;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.web.dto.PersonDto;
import org.web.dto.ResultsDto;
import org.web.util.DataHolder;

import java.util.List;

@Slf4j
public class RestApiSteps {

    @Autowired
    private DataHolder dataHolder;

    @Given("I request {int} random persons from API as {string}")
    public void requestPersonFromApi(int amount, String alias) {
        RestAssured.baseURI = "https://randomuser.me/";
        List<PersonDto> persons = RestAssured.given()
                .basePath("/api")
                .queryParam("inc", "gender,name,nat")
                .queryParam("noinfo")
                .queryParam("results", amount)
                .get()
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .extract()
                .as(ResultsDto.class)
                .getResults();
        log.info("Storing persons to container: {}", persons);
        dataHolder.put(alias, persons);
    }
}
