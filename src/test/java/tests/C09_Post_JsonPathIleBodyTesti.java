package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class C09_Post_JsonPathIleBodyTesti {

    @Test
    public void test01(){

        /*
         https://restful-booker.herokuapp.com/booking url’ine
        asagidaki body'ye sahip bir POST request gonderdigimizde
            {
            "firstname" : "Ahmet",
            "lastname" : “Bulut",
            "totalprice" : 500,
            "depositpaid" : false,
            "bookingdates" : {
                "checkin" : "2023-01-10",
                "checkout" : "2023-01-20"},
            "additionalneeds" : "wi-fi"
            }

         donen Response’un,
            status code’unun 200,
            ve content type’inin application-json,
            ve response body’sindeki
                "firstname“in,"Ahmet",
                ve "lastname“in, "Bulut",
                ve "totalprice“in,500,
                ve "depositpaid“in,false,
                ve "checkin" tarihinin 2023-01-10
                ve "checkout" tarihinin 2023-01-20
                ve "additionalneeds“in,"wi-fi"
             oldugunu test edin

         */

        // 1- Request yollayabilmek icin endpoint ve gerekiyorsa request body hazirlayin

        String endpoint = "https://restful-booker.herokuapp.com/booking";

        JSONObject requestBody = new JSONObject();

        JSONObject bookingdatesBody = new JSONObject();
        bookingdatesBody.put("checkin","2023-01-10");
        bookingdatesBody.put("checkout","2023-01-20");


        requestBody.put("firstname","Ahmet");
        requestBody.put("lastname","Bulut");
        requestBody.put("totalprice",500);
        requestBody.put("depositpaid",false);
        requestBody.put("bookingdates",bookingdatesBody);
        requestBody.put("additionalneeds" , "wi-fi");

        // {"firstname":"Ahmet","additionalneeds":"wi-fi","bookingdates":{"checkin":"2023-01-10","checkout":"2023-01-20"},"totalprice":500,"depositpaid":false,"lastname":"Bulut"}

        // 2- Soruda verilmisse expected response'i kodlarimizda olusturalim
        // 3- Request'i gonderip, donen response'i kaydedelim

        Response actualResponse = RestAssured
                                    .given().contentType(ContentType.JSON)
                                    .when().body(requestBody.toString())
                                    .post(endpoint);

        // 4- Istenen testleri yap

        //            status code’unun 200,
        //            ve content type’inin application-json,
        //            ve response body’sindeki
        //                "firstname“in,"Ahmet",
        //                ve "lastname“in, "Bulut",
        //                ve "totalprice“in,500,
        //                ve "depositpaid“in,false,
        //                ve "checkin" tarihinin 2023-01-10
        //                ve "checkout" tarihinin 2023-01-20
        //                ve "additionalneeds“in,"wi-fi"

        actualResponse
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("booking.firstname", equalTo("Ahmet"),
                        "booking.lastname",equalTo("Bulut"),
                        "booking.totalprice",equalTo(500),
                        "booking.depositpaid",equalTo(false),
                        "booking.bookingdates.checkin",equalTo("2023-01-10"),
                        "booking.bookingdates.checkout",equalTo("2023-01-20"),
                        "booking.additionalneeds",equalTo("wi-fi")
                );


    }
}
