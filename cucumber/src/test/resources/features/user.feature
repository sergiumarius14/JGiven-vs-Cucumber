Feature: User operations

  Scenario Outline: Get user with id
    Given I prepare the user request
    When I submit a get request to /api/users/<id>
    Then status code is 200
    And response field data.email is <email>
    Examples:
      | id | email                    |
      | 1  | george.bluth@reqres.in   |
      | 2  | janet.weaver@reqres.in   |
      | 3  | emma.wong@reqres.in      |
      | 4  | eve.holt@reqres.in       |
      | 5  | charles.morris@reqres.in |
      | 6  | tracey.ramos@reqres.in   |

  Scenario Outline: Create user
    Given I prepare a user request with <name> <job> <phone> <title> <email>
    When I submit a post request to /api/users
    Then status code is 201
    Examples:
      | name   | job       | phone  | title | email            |
      | Sergiu | tester    | 075555 | eng   | sergiu@reqres.in |
      | Marius | tester    | 4847   | eng   | marius@reqres.in |
      | Maria  | profesor  | 544    | prof  | maria@reqres.in  |
      | Ion    | seller    | 03669  |       | ion@reqres.in    |
      | Dana   | tester    | 064    |       | dana@reqres.in   |
      | Flavia | architect | 0569   | eng   | flavia@reqres.in |
      | Larisa | doctor    | 0259   | dr    | larisa@reqres.in |

  Scenario: Create user and verify response by schema
    Given I prepare the userDetails request
    When I submit a get request to /api/users
    Then status code is 201
    And I validate response by schema createUserSchema

  Scenario Outline: Update user
    Given I prepare a user request with <name> <job> <phone> <title> <email>
    When I submit a put request to /api/users/2
    Then status code is 200
    And response field name is <name>
    And response field email is <email>
    And response field job is <job>
    Examples:
      | name   | job    | phone | title | email            |
      | Sergiu | tester |       |       | sergiu@reqres.in |


  Scenario Outline: Delete user
    Given I prepare the delete request
    When I submit a delete request to /api/users/<id>
    Then status code is 204
    Examples:
      | id |
      | 1  |
      | 2  |
      | 3  |
      | 4  |
      | 5  |
      | 6  |
      | 7  |

  Scenario Outline: Create, Update and Delete user
    Given I prepare the userDetails request
    And I submit a post request to /api/users
    And status code is 201
    And I get the created user id
    When I prepare a user request with <name> <job> <phone> <title> <email>
    And I submit a request to update the created user
    And status code is 200
    And response field job is <job>
    Then I submit a request to delete the created user
    And status code is 204
    Examples:
      | name   | job       | phone | title | email            |
      | Sergiu | tester    | 07455 | eng   | sergiu@reqres.in |
      | Marius | tester    | 4847  | eng   | marius@reqres.in |
      | Maria  | profesor  | 544   | prof  | maria@reqres.in  |
      | Ion    | seller    | 03669 |       | ion@reqres.in    |
      | Dana   | tester    | 064   |       | dana@reqres.in   |
      | Flavia | architect | 0569  | eng   | flavia@reqres.in |
      | Larisa | doctor    | 0259  | dr    | larisa@reqres.in |