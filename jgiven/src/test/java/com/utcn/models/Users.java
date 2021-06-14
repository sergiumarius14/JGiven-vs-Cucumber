package com.utcn.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Users {
  private String name;
  private String job;
  private String phone;
  private String title;
  private String email;

  public static Users getUserDetails() {
    return getUserDetails("Sergiu", "Tester", "0777", "engineer", "sergiu@yahoo.com");
  }

  public static Users getUserDetails(
      String name, String job, String phone, String title, String email) {
    return Users.builder().name(name).job(job).phone(phone).title(title).email(email).build();
  }
}
