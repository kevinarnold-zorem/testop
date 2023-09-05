Feature: User CRUD feature
  As a user
  I would like to create/update/delete/get an user

  @Regression
  Scenario Outline: create new user
    Given I have access to https://petstore.swagger.io/ API
    When I send POST request to '/v2/user' with json
    """
    {"username":"<username>","firstname":"<firstname>","lastname":"<lastname>","email":"<email>","password":"<password>","phone":"<phone>"}
    """
    Then I expect the response code 200
    And I expect the response body is equal
    """
    {
      "code": "200",
      "type": "unknown",
      "message": "EXCLUDE"
    }
    """
    Examples:
      | username | firstname | lastname | email | password  | phone |
      | kevin234  | kevin    |  Arnold  | keviarnold@zorem.pe | Arnold123!  | 986352588 |

  @Regression
  Scenario: get an user data by username
    Given I have access to https://petstore.swagger.io/ API
    When I send GET request to '/v2/user/kevin234' with json
    """
    """
    Then I expect the response code 200


  Scenario Outline: update user firstname, email
    Given I have access to https://petstore.swagger.io/ API
    When I send PUT request to '/v2/user/kevin234' with json
    """
    {"username":"<username>","firstname":"<firstname>","lastname":"<lastname>","email":"<email>","password":"<password>","phone":"<phone>"}
    """
    Then I expect the response code 200
    Examples:
      | username | firstname | lastname | email | password  | phone |
      | kevin234  | Lorem412    |  Arnold  | keviarnold.zorem@zorem.pe | Arnold123!  | 986352588 |

  @Regression
  Scenario: get an user data by username
    Given I have access to https://petstore.swagger.io/ API
    When I send GET request to '/v2/user/kevin234' with json
    """
    """
    Then I expect the response code 200

  @Regression
  Scenario: delete user by username
    Given I have access to https://petstore.swagger.io/ API
    When I send DELETE request to '/v2/user/kevin234' with json
    """
    """
    Then I expect the response code 200


