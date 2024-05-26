package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import tests.testDataClasses.BookerTestData;

public class C12_TestDatasiKullanarakAssertionYapma {

    @Test
    public void test01(){
        /*
        https://restful-booker.herokuapp.com/booking url’ine
        asagidaki body'ye sahip bir POST request gonderdigimizde
        donen response’un id haric asagidaki gibi oldugunu test edin.

        Request body
        {
        "firstname" : "Hasan",
        "lastname" : "Yagmur",
        "totalprice" : 500,
        "depositpaid" : false,
        "bookingdates" : {
            "checkin" : "2021-06-01",
            "checkout" : "2021-06-10"
            },
        "additionalneeds" : "wi-fi"
        }

        Expected Response Body
        {
        "bookingid":24,
        "booking":{
            "firstname":"Hasan",
            "lastname":"Yagmur",
            "totalprice":500,
            "depositpaid":false,
            "bookingdates":{
                "checkin":"2021-06-01",
                "checkout":"2021-06-10"
                },
        "additionalneeds":"wi-fi"
        }

         */

        // 1- Request yollayabilmek icin endpoint ve gerekiyorsa request body hazirlayin

        String endpoint = "https://restful-booker.herokuapp.com/booking";

        JSONObject requestBody = BookerTestData.requestBodyOlustur();

        // 2- Soruda verilmisse expected response'i kodlarimizda olusturalim

        JSONObject expectedResponse = BookerTestData.responseBodyOlustur();

        // 3- Request'i gonderip, donen response'i kaydedelim

        Response actualResponse = RestAssured
                                    .given().contentType(ContentType.JSON)
                                    .when().body(requestBody.toString())
                                    .post(endpoint);


        // 4- Istenen testleri yap

        // testleri yapmak icin
        // expectedResponseBody   <===> actualResponseBody
        // JSONObject   <==>  Response (Jsonpath'e cevirmeliyiz)
        JsonPath actualResponseJPath= actualResponse.jsonPath();

        // firstname'lerin esit oldugunu test edelim
        Assert.assertEquals(
                expectedResponse.getJSONObject("booking").getString("firstname"),
                actualResponseJPath.getString("booking.firstname")
        );

        // lastname'lerin esit oldugunu test edelim
        Assert.assertEquals(
                expectedResponse.getJSONObject("booking").getString("lastname"),
                actualResponseJPath.getString("booking.lastname")
        );


        // totalprice'larin esit oldugunu test edelim
        Assert.assertEquals(
                expectedResponse.getJSONObject("booking").getInt("totalprice"),
                actualResponseJPath.getInt("booking.totalprice")
                );

        // depositpaid'larin esit oldugunu test edelim
        Assert.assertEquals(
                expectedResponse.getJSONObject("booking").getBoolean("depositpaid"),
                actualResponseJPath.getBoolean("booking.depositpaid")
        );

        // checkin'lerin esit oldugunu test edelim
        Assert.assertEquals(
                expectedResponse
                        .getJSONObject("booking")
                        .getJSONObject("bookingdates")
                        .getString("checkin"),
                actualResponseJPath.getString("booking.bookingdates.checkin")

        );


        // checkout'larin esit oldugunu test edelim
        Assert.assertEquals(
                expectedResponse
                        .getJSONObject("booking")
                        .getJSONObject("bookingdates")
                        .getString("checkout"),
                actualResponseJPath.getString("booking.bookingdates.checkout")

        );

    }
}
