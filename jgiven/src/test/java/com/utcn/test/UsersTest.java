package com.utcn.test;

import static com.utcn.constants.Constants.*;
import static com.utcn.models.Users.getUserDetails;

import com.tngtech.jgiven.annotation.ScenarioStage;
import com.tngtech.jgiven.junit5.JGivenExtension;
import com.utcn.StageApi;
import com.utcn.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpEntity;

@ExtendWith(JGivenExtension.class)
public class UsersTest {
  @ScenarioStage StageApi stageApi;

  @ParameterizedTest
  @CsvSource({
    "1, george.bluth@reqres.in",
    "2, janet.weaver@reqres.in",
    "3, emma.wong@reqres.in",
    "4, eve.holt@reqres.in",
    "5, charles.morris@reqres.in",
    "6, tracey.ramos@reqres.in"
  })
  void get_user_by_id(String id, String email) {
    HttpEntity<Object> entity = Utils.prepareRequest(null);
    stageApi
        .given()
        .the_request_is_prepared(entity)
        .when()
        .i_submit_a_get_request(String.format(USER_ENDPOINT, id))
        .then()
        .status_code_is_$(200)
        .and()
        .response_field_$_is_$("data.email", email);
  }

  @ParameterizedTest
  @CsvSource({
    "Sergiu, tester, 075555, eng, sergiu@reqres.in",
    "Marius, tester, 4847, eng, marius@reqres.in",
    "Maria, profesor, 544, prof, maria@reqres.in",
    "Ion, seller, 03669, , ion@reqres.in",
    "Dana, tester, 064, , dana@reqres.in",
    "Flavia, architect, 0569, eng, flavia@reqres.in",
    "Larisa, doctor, 0259, dr, larisa@reqres.in"
  })
  public void create_user(String name, String job, String phone, String title, String email) {
    HttpEntity<Object> entity =
        Utils.prepareRequest(getUserDetails(name, job, phone, title, email));
    stageApi
        .given()
        .the_request_is_prepared(entity)
        .when()
        .i_submit_a_post_request(USERS_ENDPOINT)
        .then()
        .status_code_is_$(201);
  }

  @Test
  public void create_user_and_verify_response_by_schema() {
    HttpEntity<Object> entity = Utils.prepareRequest(getUserDetails());
    stageApi
        .given()
        .the_request_is_prepared(entity)
        .when()
        .i_submit_a_post_request(USERS_ENDPOINT)
        .then()
        .status_code_is_$(201)
        .and()
        .i_validate_response_by_schema_$("createUserSchema");
  }

  @Test
  public void update_user() {
    HttpEntity<Object> entity =
        Utils.prepareRequest(getUserDetails("Ioana", "", null, null, "ioana@yahoo.com"));
    stageApi
        .given()
        .the_request_is_prepared(entity)
        .when()
        .i_submit_a_put_request(String.format(USER_ENDPOINT, 2))
        .then()
        .status_code_is_$(200)
        .and()
        .response_field_$_is_$("name", "Ioana")
        .and()
        .response_field_$_is_$("email", "ioana@yahoo.com")
        .and()
        .response_field_$_is_$("job", "");
  }

  @ParameterizedTest
  @CsvSource({"1", "2", "3", "4", "5", "6", "7"})
  public void delete_user(String id) {
    HttpEntity<Object> entity = Utils.prepareRequest(null);
    stageApi
        .given()
        .the_request_is_prepared(entity)
        .when()
        .i_submit_a_delete_request(String.format(USER_ENDPOINT, id))
        .then()
        .status_code_is_$(204);
  }

  @ParameterizedTest
  @CsvSource({
    "Sergiu, tester, 07455, eng, sergiu@reqres.in",
    "Marius, tester, 4847, eng, marius@reqres.in",
    "Maria, profesor, 544, prof, maria@reqres.in",
    "Ion, seller, 03669, , ion@reqres.in",
    "Dana, tester, 064, , dana@reqres.in",
    "Flavia, architect, 0569, eng, flavia@reqres.in",
    "Larisa, doctor, 0259, dr, larisa@reqres.in"
  })
  public void create_update_and_delete_same_user(
      String name, String job, String phone, String title, String email) {
    HttpEntity<Object> entity = Utils.prepareRequest(getUserDetails());
    stageApi
        .given() // create user
        .the_request_is_prepared(entity)
        .and()
        .i_submit_a_post_request(USERS_ENDPOINT)
        .and()
        .status_code_is_$(201)
        .i_get_the_created_user_id()
        .when() // update user
        .the_request_is_prepared(
            Utils.prepareRequest(getUserDetails(name, job, phone, title, email)))
        .and()
        .i_submit_a_put_request(String.format(USER_ENDPOINT, stageApi.i_get_the_user_id()))
        .and()
        .status_code_is_$(200)
        .and()
        .response_field_$_is_$("job", job)
        .then() // delete user
        .i_submit_a_delete_request(String.format(USER_ENDPOINT, stageApi.i_get_the_user_id()))
        .and()
        .status_code_is_$(204);
  }
}
