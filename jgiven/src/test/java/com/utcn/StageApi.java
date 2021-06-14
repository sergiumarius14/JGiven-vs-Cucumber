package com.utcn;

import static com.utcn.Utils.getResponsePath;
import static com.utcn.constants.Constants.REQRES_URL;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.BeforeStage;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.http.HttpEntity;

public class StageApi extends Stage<StageApi> {

  private Response response;
  private HttpEntity<Object> entity;

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

  public StageApi i_submit_a_get_request(String path) {
    response = RestAssured.given().contentType(JSON).get(path).andReturn();
    return self();
  }

  public StageApi status_code_is_$(int statusCode) {
    assertEquals(statusCode, response.statusCode());
    return self();
  }

  public StageApi login_token_is_$(String token) {
    assertEquals(token, getResponsePath(response, "token"));
    return self();
  }
}
