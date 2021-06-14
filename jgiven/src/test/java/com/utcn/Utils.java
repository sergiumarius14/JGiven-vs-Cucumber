package com.utcn;

import io.restassured.response.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class Utils {

  public static HttpEntity<Object> prepareRequest(Object request) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new HttpEntity<>(request, headers);
  }

  public static String getResponsePath(Response response, String path) {
    return response.then().extract().body().path(path);
  }
}
