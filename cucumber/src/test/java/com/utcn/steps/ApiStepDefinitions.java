package com.utcn.steps;

import static com.utcn.Utils.getResponsePath;
import static com.utcn.constants.Constants.REQRES_URL;
import static com.utcn.constants.Constants.USER_ENDPOINT;
import static com.utcn.models.Login.getLoginDetails;
import static com.utcn.models.Users.getUserDetails;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.utcn.Utils;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.http.HttpEntity;

public class ApiStepDefinitions {
  private Response response;
  private HttpEntity<Object> entity;
  private String userId;

  @BeforeStep
  public void set_base_url() {
    RestAssured.baseURI = REQRES_URL;
  }

  @Given("I prepare the {} request")
  public void givenIPrepareTheRequest(String requestType) {
    if (requestType.equals("login") || requestType.equals("register")) {
      entity = Utils.prepareRequest(getLoginDetails());
    } else if (requestType.equals("userDetails")) {
      entity = Utils.prepareRequest(getUserDetails());
    } else {
      entity = Utils.prepareRequest(null);
    }
  }

  @Given("I prepare a request with {} {}")
  public void givenIPrepareARequestWith(String email, String password) {
    entity = Utils.prepareRequest(getLoginDetails(email, password));
  }

  @Given("I prepare a user request with {} {} {} {} {}")
  public void givenIPrepareAUserRequestWith(
      String name, String job, String phone, String title, String email) {
    entity = Utils.prepareRequest(getUserDetails(name, job, phone, title, email));
  }

  @Given("I get the created user id")
  public void givenIGetTheCreatedUserId() {
    userId = getResponsePath(response, "id");
  }

  @When("I submit a post request to {}")
  public void whenISubmitAPostRequest(String path) {
    response = RestAssured.given().contentType(JSON).body(entity.getBody()).post(path).andReturn();
  }

  @When("I submit a put request to {}")
  public void whenISubmitAPutRequest(String path) {
    response = RestAssured.given().contentType(JSON).body(entity.getBody()).put(path).andReturn();
  }

  @When("I submit a request to update the created user")
  public void whenISubmitARequestToUpdateTheCreatedUser() {
    response =
        RestAssured.given()
            .contentType(JSON)
            .body(entity.getBody())
            .put(String.format(USER_ENDPOINT, userId))
            .andReturn();
  }

  @When("I submit a get request to {}")
  public void whenISubmitAGetRequest(String path) {
    response = RestAssured.given().contentType(JSON).get(path).andReturn();
  }

  @When("I submit a delete request to {}")
  public void whenISubmitADeleteRequest(String path) {
    response = RestAssured.given().contentType(JSON).delete(path).andReturn();
  }

  @Then("I submit a request to delete the created user")
  public void thenISubmitARequestToDeleteTheCreatedUser() {
    response =
        RestAssured.given()
            .contentType(JSON)
            .delete(String.format(USER_ENDPOINT, userId))
            .andReturn();
  }

  @Then("status code is {}")
  public void thenStatusCodeIs(int statusCode) {
    assertEquals(statusCode, response.statusCode(), "Not the expected status code!");
  }

  @Then("response field {} is {}")
  public void thenResponseFieldIs(String path, String value) {
    assertEquals(
        value, getResponsePath(response, path), "Not the expected value for field " + path + "!");
  }

  @Then("I validate response by schema {}")
  public void thenIValidateResponseBySchema(String schemaName) {
    response.then().assertThat().body(matchesJsonSchemaInClasspath(schemaName + ".json"));
  }
}
