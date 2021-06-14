package com.utcn.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Login {
  private String email;
  private String password;

  public static Login getLoginDetails() {
    return getLoginDetails("eve.holt@reqres.in", "cityslicka@fife");
  }

  public static Login getLoginDetails(String email, String password) {
    return Login.builder().email(email).password(password).build();
  }
}
