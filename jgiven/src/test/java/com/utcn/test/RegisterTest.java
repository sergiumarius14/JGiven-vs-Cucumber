package com.utcn.test;

import static com.utcn.constants.Constants.REGISTER_ENDPOINT;
import static com.utcn.models.Login.getLoginDetails;

import com.tngtech.jgiven.annotation.ScenarioStage;
import com.tngtech.jgiven.junit5.JGivenExtension;
import com.utcn.Utils;
import com.utcn.stage.StageApi;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpEntity;

@ExtendWith(JGivenExtension.class)
public class RegisterTest {
  @ScenarioStage StageApi stageApi;

  @RepeatedTest(1)
  void register() {
    HttpEntity<Object> entity = Utils.prepareRequest(getLoginDetails());
    stageApi
        .given()
        .the_request_is_prepared(entity)
        .when()
        .i_submit_a_post_request(REGISTER_ENDPOINT)
        .then()
        .status_code_is_$(200)
        .and()
        .response_field_$_is_$("token", "QpwL5tke4Pnpja7X4")
        .and()
        .response_field_$_is_$("id", "4");
  }

  @ParameterizedTest
  @CsvSource({
    "eve.holt@reqres.in, cityslicka@fife, 200, id, 4",
    "george.bluth@reqres.in, cityslicka, 200, id, 1",
    "janet.weaver@reqres.in, cityslicka, 200, id, 2",
    "emma.wong@reqres.in, cityslicka, 200, id, 3",
    "charles.morris@reqres.in, cityslicka, 200, id, 5",
    "tracey.ramos@reqres.in, cityslicka, 200, id, 6",
    "sergiu@reqres.in, , 400, error, Missing password",
    ", admin, 400, error, Missing email or username",
    "eve.holt@reqres.in, cityslicka@fife, 200, id, 4",
    "george.bluth@reqres.in, cityslicka, 200, id, 1",
    "janet.weaver@reqres.in, cityslicka, 200, id, 2",
    "emma.wong@reqres.in, cityslicka, 200, id, 3",
    "charles.morris@reqres.in, cityslicka, 200, id, 5",
    "tracey.ramos@reqres.in, cityslicka, 200, id, 6",
    "sergiu@reqres.in, , 400, error, Missing password",
    ", admin, 400, error, Missing email or username",
    "eve.holt@reqres.in, cityslicka@fife, 200, id, 4",
    "george.bluth@reqres.in, cityslicka, 200, id, 1",
    "janet.weaver@reqres.in, cityslicka, 200, id, 2",
    "emma.wong@reqres.in, cityslicka, 200, id, 3",
    "charles.morris@reqres.in, cityslicka, 200, id, 5",
    "tracey.ramos@reqres.in, cityslicka, 200, id, 6",
    "sergiu@reqres.in, , 400, error, Missing password",
    ", admin, 400, error, Missing email or username",
    "eve.holt@reqres.in, cityslicka@fife, 200, id, 4",
    "george.bluth@reqres.in, cityslicka, 200, id, 1",
    "janet.weaver@reqres.in, cityslicka, 200, id, 2",
    "emma.wong@reqres.in, cityslicka, 200, id, 3",
    "charles.morris@reqres.in, cityslicka, 200, id, 5",
    "tracey.ramos@reqres.in, cityslicka, 200, id, 6",
    "sergiu@reqres.in, , 400, error, Missing password",
    ", admin, 400, error, Missing email or username",
    "eve.holt@reqres.in, cityslicka@fife, 200, id, 4",
    "george.bluth@reqres.in, cityslicka, 200, id, 1",
    "janet.weaver@reqres.in, cityslicka, 200, id, 2",
    "emma.wong@reqres.in, cityslicka, 200, id, 3",
    "charles.morris@reqres.in, cityslicka, 200, id, 5",
    "tracey.ramos@reqres.in, cityslicka, 200, id, 6",
    "sergiu@reqres.in, , 400, error, Missing password",
    ", admin, 400, error, Missing email or username",
    "eve.holt@reqres.in, cityslicka@fife, 200, id, 4",
    "george.bluth@reqres.in, cityslicka, 200, id, 1",
    "janet.weaver@reqres.in, cityslicka, 200, id, 2",
    "emma.wong@reqres.in, cityslicka, 200, id, 3",
    "charles.morris@reqres.in, cityslicka, 200, id, 5",
    "tracey.ramos@reqres.in, cityslicka, 200, id, 6",
    "sergiu@reqres.in, , 400, error, Missing password",
    ", admin, 400, error, Missing email or username"
  })
  void register_with_certain_credentials(
      String email, String password, int statusCode, String path, String value) {
    HttpEntity<Object> entity = Utils.prepareRequest(getLoginDetails(email, password));
    stageApi
        .given()
        .the_request_is_prepared(entity)
        .when()
        .i_submit_a_post_request(REGISTER_ENDPOINT)
        .then()
        .status_code_is_$(statusCode)
        .and()
        .response_field_$_is_$(path, value);
  }
}
