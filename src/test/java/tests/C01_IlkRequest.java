package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

public class C01_IlkRequest {

    @Test
    public void test01(){

        // 1- Request yollayabilmek icin endpoint ve gerekiyorsa request body hazirlayin
        // 2- Soruda verilmisse expected response'i kodlarimizda olusturalim
        // 3- Request'i gonderip, donen response'i kaydedelim
        // 4- Istenen testleri yap

        //  https://restful-booker.herokuapp.com/booking/10 url’ine
        //  bir GET request gonderdigimizde donen Response’u yazdirin.

        /*
            Bir API sorgusu yapmadan once
            mutlaka server'in dokumantasyonu incelenmelidir

            Eger gorev verilen endpoint'i ilk defa kullaniyorsak
            postman uzerinden manuel sorgu yaparak
            bize verilen bilgilerle sorgu yapilabildigini gozlemleyebiliriz

         */

        // 1- Request yollayabilmek icin endpoint ve gerekiyorsa request body hazirlayin

        String endPoint = "https://restful-booker.herokuapp.com/booking/10";

        // 2- Soruda verilmisse expected response'i kodlarimizda olusturalim

        // 3- Request'i gonderip, donen response'i kaydedelim

        Response response = RestAssured
                                    .given()
                                    .when()
                                    .get(endPoint);


        response.prettyPrint();

        /*
                        {
                    "firstname": "Mark",
                    "lastname": "Wilson",
                    "totalprice": 439,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2021-06-22",
                        "checkout": "2022-07-16"
                    },
                    "additionalneeds": "Breakfast"
                }
         */


    }
}
