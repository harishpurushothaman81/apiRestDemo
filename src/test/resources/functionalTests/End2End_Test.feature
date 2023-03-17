Feature: Login Functionality
@validLogin
Scenario: User Should Login With Valid Credentials 
   Given Post Login API
   When Provide Valid Credential
   Then Status_code equals 200   
Scenario: Create a new user
    Given I have a valid data for user creation
    When I send a POST request email, id, password, Username
    Then the response status code should be 200 for created user
    And the response should contain the username
    And the response should contain the email
Scenario: Update user information
    Given I have to select last created user for update emailid
    When I update last created user emailid to new emailid 
    Then I have to verity the emailid is updated correctily 	
Scenario: Delete user
    Given I have to delete last created user
    When I delete last created account
    Then last created user account should be deleted
Scenario Outline: Invalid Login validation API checks
   Given Post Login API
   When Provide different combinations to "<email>" and "<password>"
   Then response contains message equals "<message>"    
 Examples:
   |email     		   |password      | statuscode |  message                                 |
   |          	     |              |   500      | HTTP/1.1 500 Internal Server Error       |   
   | abc	  		     |              |   500      | HTTP/1.1 500 Internal Server Error       |
   | abc@mail7.io    | Pass	   	    |   500      | HTTP/1.1 500 Internal Server Error       |