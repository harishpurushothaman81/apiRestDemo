Feature: Check Inactive Campaign List API
@mytag
  Scenario: Verify Inactive Campaign List
    Given the API endpoint authentication token
    When I send a GET request to the API endpoint
    Then the response status code should be 200
    And the response body should contain a list of inactive campaigns
    And inactive campaigns starting date is 2015