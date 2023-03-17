$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/functionalTests/Platform161InactiveCampain_Test.feature");
formatter.feature({
  "name": "Check Inactive Campaign List API",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Verify Inactive Campaign List",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@mytag"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "the API endpoint authentication token",
  "keyword": "Given "
});
formatter.match({
  "location": "InactiveCampaignListSteps.theAPIEndpointIs()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I send a GET request to the API endpoint",
  "keyword": "When "
});
formatter.match({
  "location": "InactiveCampaignListSteps.iSendAGETRequestToTheAPIEndpoint()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the response status code should be 200",
  "keyword": "Then "
});
formatter.match({
  "location": "InactiveCampaignListSteps.theResponseStatusCodeShouldBe(int)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the response body should contain a list of inactive campaigns",
  "keyword": "And "
});
formatter.match({
  "location": "InactiveCampaignListSteps.theResponseBodyShouldContainAListOfInactiveCampaigns()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "inactive campaigns starting date is 2015",
  "keyword": "And "
});
formatter.match({
  "location": "InactiveCampaignListSteps.theResponseBodyShouldContainCampaignsStartDateis2022()"
});
formatter.result({
  "status": "passed"
});
formatter.uri("file:src/test/resources/functionalTests/Platform161_Test.feature");
formatter.feature({
  "name": "Authentication",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "User gets an authentication token",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@mytag"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "the user has valid credentials",
  "keyword": "Given "
});
formatter.match({
  "location": "AuthenticationSteps.setValidCredentials()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user requests an authentication token",
  "keyword": "When "
});
formatter.match({
  "location": "AuthenticationSteps.requestAuthToken()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a valid token is returned",
  "keyword": "Then "
});
formatter.match({
  "location": "AuthenticationSteps.verifyAuthToken()"
});
formatter.result({
  "status": "passed"
});
});