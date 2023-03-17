package stepDefinitions;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.cucumber.java.en.Then;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;


public class InactiveCampaignListSteps {
    private String username;
    private String password;
    private String authToken;
    private String checkYear;
    RequestSpecification request;
    private  static  Response response;
    
    @Before
    public void setUp() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        prop.load(fis);
        RestAssured.baseURI = prop.getProperty("base_url");
        username = prop.getProperty("user_name");
        password = prop.getProperty("pass");
        checkYear = prop.getProperty("year");
    }
    // code for generating auth token
    @Given("the API endpoint authentication token")
    public void theAPIEndpointIs() {
    	this.authToken = makeAPICallAndGetAuthToken(username, password);
		request  =  RestAssured.given();
		request.header("Content-Type",  "application/json");
		authToken = "Bearer "+authToken;
		request.header("Authorization",authToken);
    }
    // code for send campaigns get list
    @When("^I send a GET request to the API endpoint$")
    public void iSendAGETRequestToTheAPIEndpoint() {
    	String endpoint = "api/v3/sandboxcost/campaigns";
    	response  =  request.get(endpoint);
    }
    // code for validate response status code
    @Then("^the response status code should be (\\d+)$")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }
    // code for validate inactive campaigns in response 
    @Then("^the response body should contain a list of inactive campaigns$")
    public void theResponseBodyShouldContainAListOfInactiveCampaigns() throws JsonProcessingException {
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(false, jsonPath.getBoolean("data[0].attributes.active"));
    }
 // code for validate inactive campaigns start date
    @Then("^inactive campaigns starting date is 2015$")
    public void theResponseBodyShouldContainCampaignsStartDateis2022() throws JsonProcessingException {
        JsonPath jsonPath = response.jsonPath();
        String active1 = jsonPath.getString("data[0].attributes.start_date");
        System.out.println(active1);
        String year = jsonPath.getString("data[0].attributes.start_date");
        year = year.substring(year.length() - 4);
        Assert.assertEquals(year, checkYear);
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
