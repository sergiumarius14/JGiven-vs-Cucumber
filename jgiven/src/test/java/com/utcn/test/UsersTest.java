package com.utcn.test;

import static com.utcn.constants.Constants.USERS_ENDPOINT;
import static com.utcn.models.Users.getUserDetails;

import com.tngtech.jgiven.annotation.ScenarioStage;
import com.tngtech.jgiven.junit5.JGivenExtension;
import com.utcn.StageApi;
import com.utcn.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpEntity;

@ExtendWith(JGivenExtension.class)
public class UsersTest {
  @ScenarioStage StageApi stageApi;

  @Test
  public void JSon_Schema_validation() {
    HttpEntity<Object> entity = Utils.prepareRequest(getUserDetails());
    stageApi
        .given()
        .the_request_is_prepared(entity)
        .when()
        .i_submit_a_post_request(USERS_ENDPOINT)
        .then()
        .status_code_is_$(201)
        .and()
        .validate_response_by_schema_$("createUserSchema");
  }
}
