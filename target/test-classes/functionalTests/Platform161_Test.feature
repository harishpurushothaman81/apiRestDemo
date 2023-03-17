Feature: Authentication
@mytag
  Scenario: User gets an authentication token
    Given the user has valid credentials
    When the user requests an authentication token
    Then a valid token is returned