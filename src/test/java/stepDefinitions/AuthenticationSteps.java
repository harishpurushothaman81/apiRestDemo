package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.cucumber.java.en.Then;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;

public class AuthenticationSteps {
    private String username;
    private String password;
    private String authToken;
    RequestSpecification request;
    private  static  Response response;
    
    // setting user name and password from config file 
    @Given("the user has valid credentials")
    public void setValidCredentials() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        prop.load(fis);
        RestAssured.baseURI = prop.getProperty("base_url");
        username = prop.getProperty("user_name");
        password = prop.getProperty("pass");
    }
    // Code to make API call and get the authentication token
    @When("the user requests an authentication token")
    public void requestAuthToken() {
        // Code to make API call and get the authentication token
        this.authToken = makeAPICallAndGetAuthToken(username, password);
    }
    // Code for validate authToken
    @Then("a valid token is returned")
    public void verifyAuthToken() {
        Assert.assertNotNull(this.authToken);
        Assert.assertTrue(this.authToken.length() > 0);
    }
    

    private String makeAPICallAndGetAuthToken(String username, String password) {
        // Code to make API call and get the authentication token
		request  =  RestAssured.given();
		request.header("Content-Type",  "application/json");
		response  =  request.body("{ \"user\":{ \"login\":\""  +  username  +  "\", \"password\":\""  +  password  +  "\"}}") .post("api/v3/sandboxcost/tokens"); 
		String authToken = response.jsonPath().getString("access_token");
		return authToken;
	
    }
}
