package stepDefinitions;

import net.serenitybdd.rest.SerenityRest;

import static org.hamcrest.Matchers.equalTo;

public class HttpValidation {
    public void verifyGeoResponseMessage(String name) {
        SerenityRest.restAssuredThat(response -> response.body("name", equalTo(name)));
    }
}
