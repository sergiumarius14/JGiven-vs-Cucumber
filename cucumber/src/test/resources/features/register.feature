Feature: Register

  Scenario: Register
    Given I prepare the register request
    When I submit a post request to /api/register
    Then status code is 200
    And response field token is QpwL5tke4Pnpja7X4
    And response field id is 4

  Scenario Outline: Register with certain credentials
    Given I prepare a request with <email> <password>
    When I submit a post request to /api/register
    Then status code is <statusCode>
    And response field <field> is <value>
    Examples:
      | email                    | password        | statusCode | field | value                     |
      | eve.holt@reqres.in       | cityslicka@fife | 200        | id    | 4                         |
      | george.bluth@reqres.in   | cityslicka      | 200        | id    | 1                         |
      | janet.weaver@reqres.in   | cityslicka      | 200        | id    | 2                         |
      | emma.wong@reqres.in      | cityslicka      | 200        | id    | 3                         |
      | charles.morris@reqres.in | cityslicka      | 200        | id    | 5                         |
      | tracey.ramos@reqres.in   | cityslicka      | 200        | id    | 6                         |
      | sergiu@reqres.in         |                 | 400        | error | Missing password          |
      |                          | admin           | 400        | error | Missing email or username |