package com.utcn;

import static com.utcn.Utils.getResponsePath;
import static com.utcn.constants.Constants.REQRES_URL;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.http.HttpEntity;

public class StageApi extends Stage<StageApi> {

  private Response response;
  private HttpEntity<Object> entity;
  private String userId;

  @BeforeStage
  public StageApi set_base_url() {
    RestAssured.baseURI = REQRES_URL;
    return self();
  }

  public StageApi the_request_is_prepared(HttpEntity<Object> entity) {
    this.entity = entity;
    return self();
  }

  public StageApi i_submit_a_post_request(String path) {
    response = RestAssured.given().contentType(JSON).body(entity.getBody()).post(path).andReturn();
    return self();
  }

  public StageApi i_submit_a_put_request(String path) {
    response = RestAssured.given().contentType(JSON).body(entity.getBody()).put(path).andReturn();
    return self();
  }

  public StageApi i_submit_a_get_request(String path) {
    response = RestAssured.given().contentType(JSON).get(path).andReturn();
    return self();
  }

  public StageApi i_submit_a_delete_request(String path) {
    response = RestAssured.given().contentType(JSON).delete(path).andReturn();
    return self();
  }

  public StageApi status_code_is_$(int statusCode) {
    assertEquals(statusCode, response.statusCode(), "Not the expected status code!");
    return self();
  }

  public StageApi response_field_$_is_$(String path, String value) {
    assertEquals(
        value, getResponsePath(response, path), "Not the expected value for field " + path + "!");
    return self();
  }

  public StageApi validate_response_by_schema_$(String schemaName) {
    response.then().assertThat().body(matchesJsonSchemaInClasspath(schemaName + ".json"));
    return self();
  }

  public StageApi i_set_the_user_id() {
    userId = getResponsePath(response, "id");
    return self();
  }

  public String i_get_the_user_id() {
    return userId;
  }
}
