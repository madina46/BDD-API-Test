package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.FootballPage;
import utility.LeagueTableRow;
import utility.PropertiesReader;

import java.util.List;


import com.app.test.response.HttpValidation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

public class NewRequest {
    public static final String URL = "https://petstore.swagger.io/#/";
    public Response response;
    HttpValidation httpValidation = new HttpValidation();
    @Given("GET petid\" api pet request endpoint is set as \"pet/petid")
    public void aRequestIsMade() {
// to practice Request body - response split and validate
        //jackson lib -
        response = SerenityRest.given().contentType("application/json").header("Content-Type", "application/json")
                .when().get(URL);
    }

    @Then("the API should return status {int}")
    public void verifyResponse(int status) {
        response.statusCode();
        SerenityRest.restAssuredThat(response -> response.statusCode(status));
    }

    @Then("the response will return {string} ")
    public void verifyGeoInformation(String name) {
        httpValidation.verifyGeoResponseMessage(name);
    }
}
