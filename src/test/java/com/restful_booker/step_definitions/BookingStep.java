package com.restful_booker.step_definitions;

import com.github.javafaker.Faker;
import com.restful_booker.pages.newBookingPOJO.Bookingdates;
import com.restful_booker.pages.newBookingPOJO.CreateBooking;
import com.restful_booker.utilities.APIUtils;
import com.restful_booker.utilities.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class BookingStep {

    String token;
    Response response;
    int bookingId;
    String url = ConfigurationReader.get("url");

    @When("user uses token")
    public void user_uses_token() {
      token = APIUtils.getAccessToken();
    }

    @When("user send get request to GetBookingIds")
    public void user_send_get_request_to_GetBookingIds() {

        // send GET request to GetBookingIds with token
        response = given().accept(ContentType.JSON)
                .auth().oauth2(token)
                .get(url);

        // retrieve the last bookingid
        bookingId = response.path("bookingid[-1]");
    }

    @When("user send get request to GetBooking with ID from GetBookingIds")
    public void user_send_get_request_to_GetBooking_with_ID_from_GetBookingIds() {
        response = given().accept(ContentType.JSON)
                .auth().oauth2(token)
                .get(url + "/" + bookingId);

        Assert.assertEquals(418,response.statusCode());
    }

    @When("user send post request to CreateBooking")
    public void user_send_post_request_to_CreateBooking() {

        Faker faker = new Faker(Locale.UK);
        String firstN = faker.name().firstName();
        String lastN = faker.name().lastName();
        int totalPrice = faker.number().numberBetween(001,999);
        boolean deposit = faker.random().nextBoolean();
        String checkInDay = APIUtils.randomDateGenerator(2022,4,10,2022,4,15);
        String checkOutDay = APIUtils.randomDateGenerator(2022,4,16,2022,4,19);
        String additionalNeeds = faker.food().dish();

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin(checkInDay);
        bookingdates.setCheckout(checkOutDay);

        CreateBooking createBooking = new CreateBooking();
        createBooking.setBookingdates(bookingdates);
        createBooking.setAdditionalneeds(additionalNeeds);
        createBooking.setFirstname(firstN);
        createBooking.setLastname(lastN);
        createBooking.setDepositpaid(deposit);
        createBooking.setTotalprice(totalPrice);

        response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .body(createBooking)
                .post(url);

        // status code is : 418  with "I'm a teapot"  ----> This code is returned by teapots that receive requests to brew coffee
        Assert.assertEquals(418,response.statusCode());
    }

    @Then("status code should be {int}")
    public void status_code_should_be(int expectedStatusCode) {

        // verify that post request is sent successfully
        Assert.assertEquals(expectedStatusCode, response.statusCode());
    }

}
