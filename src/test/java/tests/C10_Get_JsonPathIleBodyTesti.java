package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

public class C10_Get_JsonPathIleBodyTesti {

    @Test
    public void test01(){

        /*
             http://dummy.restapiexample.com/api/v1/employees url'ine
            bir GET request yolladigimizda
                donen Response'in
                    status code'unun 200,
                    ve content type'inin Aplication.JSON,
                    ve response body'sindeki
                    employees sayisinin 24
                    ve employee'lerden birinin "Ashton Cox"
                    ve girilen yaslar icinde 61,21 ve 35 degerinin oldugunu test edin.
         */

        // 1- Request yollayabilmek icin endpoint ve gerekiyorsa request body hazirlayin
        String endpoint = "http://dummy.restapiexample.com/api/v1/employees";

        // 2- Soruda verilmisse expected response'i kodlarimizda olusturalim
        // 3- Request'i gonderip, donen response'i kaydedelim
        Response actualResponse = RestAssured
                                .given()
                                .when()
                                .get(endpoint);

        // 4- Istenen testleri yap

        //                    status code'unun 200,
        //                    ve content type'inin Aplication.JSON,
        //                    ve response body'sindeki
        //                    employees sayisinin 24
        //                    ve employee'lerden birinin "Ashton Cox"
        //                    ve girilen yaslar icinde 61,21 ve 35 degerinin oldugunu test edin.

        actualResponse
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.employee_name", Matchers.hasSize(24),
                       "data.employee_name", Matchers.hasItem("Ashton Cox"),
                        "data.employee_age",Matchers.hasItems(61,21,35)
                );
    }
}
