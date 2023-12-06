package apiTesting;

import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java8.En;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

public class RestSteps implements En {

    private static final Logger LOG = LoggerFactory.getLogger(RestSteps.class);

    RequestSpecification request = RestAssured.given();
    Response subBreedResponse = request.get("https://dog.ceo/api/breed/retriever/list");
    Response response = request.get("https://dog.ceo/api/breeds/list/all");
    Response retrieverRandomImageResponse = request.get("https://dog.ceo/api/breed/retriever/golden/images/random");

    @Then("verify response code")
    public void expectedResponseCode() {
        int statusCode = Integer.parseInt(String.valueOf(response.getStatusCode() | subBreedResponse.getStatusCode() | retrieverRandomImageResponse.getStatusCode()));
        if (statusCode == 200) {
            System.out.println("Response status is success");
        } else {
            Assert.fail("Response status code is not successful");
        }
    }

    @And("Verify a list of all dog breeds is displayed {string}")
    public void listOfAllDogBreeds(String breed) {
        String responseBodyAsString = response.getBody().asString();
        response.prettyPrint();
        Assert.assertTrue(responseBodyAsString.contains(breed));
    }

    @And("confirm a list of retriever sub breeds")
    public void confirmAListOfRetrieverSubBreeds() {
        String[] retrieverSubBreeds = new String[]{"chesapeake", "curly", "flatcoated", "golden"};
        String responseBodyAsString = subBreedResponse.getBody().asString();
        for (String retrieverSubBreedList : retrieverSubBreeds) {
            if (subBreedResponse.getStatusCode() == 200 && responseBodyAsString != null) {
                String retrieverSubBreed = retrieverSubBreedList.toLowerCase();
                Assert.assertTrue(responseBodyAsString.contains(retrieverSubBreed));
            System.out.println(retrieverSubBreed);}
            else{
                Assert.fail("Failed to return retriever sub breed");
            }
        }
    }
    @And("verify a random image is returned from golden breed")
    public void getARandomImageFromGoldenBreed() {
            String responseBodyAsString = retrieverRandomImageResponse.getBody().asString();
            if(responseBodyAsString.contains("jpg")){
                retrieverRandomImageResponse.prettyPrint();
            } else {
                Assert.fail("Failed to return breed image");
        }
    }
}


