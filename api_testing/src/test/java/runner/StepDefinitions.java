package runner;

import clientapi.FactoryRequest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.Assert;
import utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class StepDefinitions {
    public final String HOST="https://petstore.swagger.io";
    public Response response;
    public Map<String, String> globalVariables = new HashMap<>();

    @Given("^I have access to https://petstore\\.swagger\\.io/ API$")
    public void iHaveAccessToHttpsPetstoreSwaggerIoAPI() {
    }
    @When("^I send (POST|PUT|DELETE|GET) request to '(.*)' with json$")
    public void iSendARequestWithJson(String httpVerb, String url, String jsonBody) {
        response = FactoryRequest.make(httpVerb).send(HOST+this.replaceStoredVariables(url),this.replaceStoredVariables(jsonBody));
        System.out.println("[INFO] REQUEST WAS EXECUTED " + url + " - " + response.timeIn(TimeUnit.MILLISECONDS) + " milliseconds");
        response.prettyPrint();
    }

    @Then("^I expect the response code (\\d+)$")
    public void iExpectTheResponseCode(int expectedResponseCode) {
        Assert.assertEquals("Error! Wrong response data.",expectedResponseCode, response.getStatusCode());
    }

    @And("^I expect the response body is equal$")
    public void iExpectTheResponseBodyIsEqual(String expectedJsonBody) throws JSONException {
        Assert.assertTrue("Error!, wrong response data",
                JsonUtils.areEqualJSON(expectedJsonBody,response.getBody().asString()));
    }


    // Util
    private String replaceStoredVariables(String value){
        for (String key: this.globalVariables.keySet()){
            value= value.replace(key,this.globalVariables.get(key));
        }
        return value;
    }


}
