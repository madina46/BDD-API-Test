package stepDefinitions;

import net.serenitybdd.rest.SerenityRest;

import static org.hamcrest.Matchers.equalTo;

public class HttpValidation {
    public void verifyGeoResponseMessage(String name) {
        SerenityRest.restAssuredThat(response -> response.body("country_code", equalTo(country_code))
                .and().body("name", equalTo(name)));
    }
}
