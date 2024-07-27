import com.thoughtworks.gauge.Step;
import infrastructure.singleton.BaseConfigService;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static io.restassured.http.ContentType.JSON;

public class StepImplementation extends BaseConfigService {
    public StepImplementation() {
        super();
    }

    @Step("Create a new auth")
    public void CreateANewAuth() {

        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setContentType(JSON)
                .setBody("{\"username\": \"" + configServiceReader.getUserName()
                        + "\", \"password\": \"" + configServiceReader.getPassword()
                        + "\"}")
                .setBaseUri(configServiceReader.postBookerAuth())
                .build();

        Response response = RestAssured.given().spec(requestSpec).post();
        String authToken = response.jsonPath().getString("token");
        System.out.println("Token: " + authToken);
    }

    @Step("Get all booking ids")
    public void GetBookingIds() {

        Response response = RestAssured.get(configServiceReader.getBookingIds());
        response.then().statusCode(200);

        List<Integer> bookingIds = response.jsonPath().getList("bookingid");
        List<Integer> firstThreeBookingIds = bookingIds.subList(0, Math.min(3, bookingIds.size()));
        String jsonOutput = firstThreeBookingIds.toString();
        System.out.println("First Three BookingIds: " + jsonOutput);
    }

    @Step("Get booking id")
    public void GetBookingId() {
        Response response = RestAssured
                .get(configServiceReader.getBookingId());
        response.then().statusCode(200);

        String firstName = response.jsonPath().getString("firstname");
        String lastName = response.jsonPath().getString("lastname");
        System.out.println("Guest Name: " + firstName + " " + lastName);
    }

    @Step("Create new booking")
    public String CreateNewBooking() {
        String requestBody = "{\n" +
                "  \"firstname\" : \"Jim\",\n" +
                "  \"lastname\" : \"Brown\",\n" +
                "  \"totalprice\" : 111,\n" +
                "  \"depositpaid\" : true,\n" +
                "  \"bookingdates\" : {\n" +
                "      \"checkin\" : \"2018-01-01\",\n" +
                "      \"checkout\" : \"2019-01-01\"\n" +
                "  },\n" +
                "  \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(configServiceReader.getBookerBaseUrl());

        response.then().statusCode(200);

        String newBookingId = response.jsonPath().getString("bookingid");
        System.out.println("New Booking Id" + newBookingId);

        return newBookingId;
    }

    @Step("Check new booking id exist")
    public void CheckNewBookingIdExist(){
        Response response = RestAssured
                .get(configServiceReader.getBookingIdNewBooking()+ CreateNewBooking());
        response.then().statusCode(200);

        String firstName = response.jsonPath().getString("firstname");
        String lastName = response.jsonPath().getString("lastname");
        System.out.println("Guest Name: " + firstName + " " + lastName);
    }
}
