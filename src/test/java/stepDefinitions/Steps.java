package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.junit.Assert;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;

public class Steps {
	private  static  final  String  BASE_URL  =  "https://api.m3o.com";

	RequestSpecification request;
	private  static  Response response;
	
	// Global variables for login, create and delete user details 
	public String M3O_API_TOKEN;
	public String userEmail;
	public String userName;
	public String passWord;
	public String userID;
	public String newEmail;
	public String email;
	public String password;

	@Given("Post Login API")
	public void post_login_api() {
		RestAssured.baseURI  =  BASE_URL;
		
		//Authentication token for accessing M30 api's
		//Please Provide a valid token before start executing this script
		//for generating new token please visit https://m3o.com/account/tokens
		
		M3O_API_TOKEN = "NzEwN2YzZTctMjIzMy00NmNmLTg2MDgtMmRiMDE0Mzk5MWIz";
		
		request  =  RestAssured.given();
		request.header("Content-Type",  "application/json");
		String bToken = "Bearer "+M3O_API_TOKEN;
		request.header("Authorization",bToken);

	}

	@When("Provide Valid Credential")
	public void provide_valid_credential() {
		
		// Assign valid user and password for login test
		email = "joe@example.com";
		password = "Password1";
		
		//login API request
		response  =  request.body("{ \"email\":\""  +  email  +  "\", \"password\":\""  +  password  +  "\"}")
				.post("/v1/user/Login");	
	}
    //Verify that user is getting success status after login
	@Then("Status_code equals {int}")
	public void statuscode_equals_(int agr) {
		Assert.assertEquals(agr, response.getStatusCode());
	}

	@And("response contains IsLogin equals {string}")
	public void response_contains_IsLogin_equals_(String message) {
		Assert.assertEquals(message, getJsonPath(response, "true"));
	}

	@And("response contains IsPosted equals {string}")
	public void response_contains_IsPosted_equals_(String message) {
		Assert.assertEquals(message, getJsonPath(response, "IsPosted"));
	}

	@And("^response contains message equals (.*)$")
	public void response_contains_equals (String message) {
		String responseStatus = response.getStatusLine().toString();
		String expResponse = message.substring(1, message.length() - 1);
		Assert.assertEquals(expResponse, responseStatus);
	}
    // Invalid login validations 
	@When("^Provide different combinations to (.*) and (.*)$")
	public void provide_different_combinations_to_something(String email, String password){
		response = request.body("{ \"email\":\"" + email + "\", \"password\":\""  + password +  "\"}") .post("/v1/user/Login");
	}
    // User creation check with Dynamic data using Faker
	@Given("I have a valid data for user creation")
	public void i_have_a_valid_data_for_user_creation()
	{
		Faker faker = new Faker();
		userEmail = faker.internet().emailAddress();
		userName = faker.address().firstName();
		passWord = faker.internet().password();
		userID = faker.name().username();

	}
	@When("I send a POST request email, id, password, Username")
	public void i_send_a_post_request_email_id_passowrd_username()
	{
		response = request.body("{ \"email\":\"" + userEmail + "\", \"id\":\""  + userID +  "\", \"password\":\"" + passWord + "\", \"username\":\""  + userName +  "\"}") .post("/v1/user/Create");
	}
	@Then("the response status code should be {int} for created user")
	public void the_response_status_code_should_be_(int agr) {
		Assert.assertEquals(agr, response.getStatusCode());
	}
	@Then("the response should contain the username")
	public void the_response_should_contain_the_username() throws Throwable {
		String responseBody = response.getBody().asString();
		Assert.assertTrue(responseBody.contains(userName));
	}

	@Then("the response should contain the email")
	public void the_response_should_contain_the_email() throws Throwable {
		String responseBody = response.getBody().asString();
		Assert.assertTrue(responseBody.contains(userEmail));
	}
	// Update api check - Verifying user can able to update email address
	@Given("I have to select last created user for update emailid")
	public void i_have_to_select_last_created_user_for_update_emailid()
	{
		newEmail = "new" + userEmail;
		System.out.println(newEmail);
	}
	@When("I update last created user emailid to new emailid")
	public void  i_update_last_created_user_emailid_to_new_emailid()
	{
		response = request.body("{ \"email\":\"" + newEmail + "\", \"id\":\"" + userID + "\"}") .post("/v1/user/Update");
		Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(newEmail, response.jsonPath().getString("email"));

	}
	// validating the updated user info is listing correctly 
	@Then("I have to verity the emailid is updated correctily")
	public void i_have_to_verity_the_emailid_is_updated_correctily() {
		response = request.body("{ \"id\":\"" + userID + "\"}") .post("/v1/user/Update");
		Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(newEmail, response.jsonPath().getString("email"));
	}

    //Delete last created user from the DB
	@Given("I have to delete last created user")
	public void i_have_to_delete_last_created_user()
	{
		response = request.body("{ \"id\":\"" + userID + "\"}") .delete("/v1/user/Delete");
	}
	@When("I delete last created account")
	public void i_delete_last_created_account()
	{
        // Verify response
        Assert.assertEquals(204, response.getStatusCode());
	}
	@Then("last created user account should be deleted")
	public void last_created_user_account_should_be_deleted()
	{
		response = request.body("{ \"id\":\"" + userID + "\"}") .get("/v1/user/List");
		 Assert.assertEquals(404, response.getStatusCode());
	}
	
	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}


}