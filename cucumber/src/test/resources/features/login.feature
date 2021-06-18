Feature: Login

  Scenario: Login
    Given I prepare the login request
    When I submit a post request to /api/login
    Then status code is 200
    And response field token is QpwL5tke4Pnpja7X4

  Scenario Outline: Login with certain credentials
    Given I prepare a request with <email> <password>
    When I submit a post request to /api/login
    Then status code is <statusCode>
    Examples:
      | email                    | password        | statusCode |
      | eve.holt@reqres.in       | cityslicka@fife | 200        |
      | sergiu@reqres.in         | admin           | 400        |
      | george.bluth@reqres.in   | cityslicka      | 200        |
      | janet.weaver@reqres.in   | cityslicka      | 200        |
      | emma.wong@reqres.in      | cityslicka      | 200        |
      | charles.morris@reqres.in | cityslicka      | 200        |
      | tracey.ramos@reqres.in   | cityslicka      | 200        |
      | eve.holt@reqres.in       | cityslicka@fife | 200        |
      | sergiu@reqres.in         | admin           | 400        |
      | george.bluth@reqres.in   | cityslicka      | 200        |
      | janet.weaver@reqres.in   | cityslicka      | 200        |
      | emma.wong@reqres.in      | cityslicka      | 200        |
      | charles.morris@reqres.in | cityslicka      | 200        |
      | tracey.ramos@reqres.in   | cityslicka      | 200        |
      | eve.holt@reqres.in       | cityslicka@fife | 200        |
      | sergiu@reqres.in         | admin           | 400        |
      | george.bluth@reqres.in   | cityslicka      | 200        |
      | janet.weaver@reqres.in   | cityslicka      | 200        |
      | emma.wong@reqres.in      | cityslicka      | 200        |
      | charles.morris@reqres.in | cityslicka      | 200        |
      | tracey.ramos@reqres.in   | cityslicka      | 200        |
      | eve.holt@reqres.in       | cityslicka@fife | 200        |
      | sergiu@reqres.in         | admin           | 400        |
      | george.bluth@reqres.in   | cityslicka      | 200        |
      | janet.weaver@reqres.in   | cityslicka      | 200        |
      | emma.wong@reqres.in      | cityslicka      | 200        |
      | charles.morris@reqres.in | cityslicka      | 200        |
      | tracey.ramos@reqres.in   | cityslicka      | 200        |
      | eve.holt@reqres.in       | cityslicka@fife | 200        |
      | sergiu@reqres.in         | admin           | 400        |
      | george.bluth@reqres.in   | cityslicka      | 200        |
      | janet.weaver@reqres.in   | cityslicka      | 200        |
      | emma.wong@reqres.in      | cityslicka      | 200        |
      | charles.morris@reqres.in | cityslicka      | 200        |
      | tracey.ramos@reqres.in   | cityslicka      | 200        |
      | eve.holt@reqres.in       | cityslicka@fife | 200        |
      | sergiu@reqres.in         | admin           | 400        |
      | george.bluth@reqres.in   | cityslicka      | 200        |
      | janet.weaver@reqres.in   | cityslicka      | 200        |
      | emma.wong@reqres.in      | cityslicka      | 200        |
      | charles.morris@reqres.in | cityslicka      | 200        |
      | tracey.ramos@reqres.in   | cityslicka      | 200        |