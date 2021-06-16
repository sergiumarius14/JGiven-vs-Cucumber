package com.utcn.test;

import static com.utcn.constants.Constants.LOGIN_ENDPOINT;
import static com.utcn.models.Login.getLoginDetails;

import com.tngtech.jgiven.annotation.ScenarioStage;
import com.tngtech.jgiven.junit5.JGivenExtension;
import com.utcn.StageApi;
import com.utcn.Utils;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpEntity;

@ExtendWith(JGivenExtension.class)
class LoginTest {
  @ScenarioStage StageApi stageApi;

  @RepeatedTest(1)
  void repeated_successfully_login() {
    HttpEntity<Object> entity = Utils.prepareRequest(getLoginDetails());
    stageApi
        .given()
        .the_request_is_prepared(entity)
        .when()
        .i_submit_a_post_request(LOGIN_ENDPOINT)
        .then()
        .status_code_is_$(200)
        .and()
        .response_field_$_is_$("token", "QpwL5tke4Pnpja7X4");
  }

  @ParameterizedTest
  @CsvSource({
    "eve.holt@reqres.in, cityslicka@fife, 200",
    "sergiu@reqres.in, admin, 400",
    "george.bluth@reqres.in, cityslicka, 200",
    "janet.weaver@reqres.in, cityslicka, 200",
    "emma.wong@reqres.in, cityslicka, 200",
    "charles.morris@reqres.in, cityslicka, 200",
    "tracey.ramos@reqres.in, cityslicka, 200"
  })
  void login(String email, String password, int statusCode) {
    HttpEntity<Object> entity = Utils.prepareRequest(getLoginDetails(email, password));
    stageApi
        .given()
        .the_request_is_prepared(entity)
        .when()
        .i_submit_a_post_request(LOGIN_ENDPOINT)
        .then()
        .status_code_is_$(statusCode);
  }
}
