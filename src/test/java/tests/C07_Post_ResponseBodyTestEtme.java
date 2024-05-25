package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

public class C07_Post_ResponseBodyTestEtme {

    @Test
    public void test01(){

        /*
                    https://jsonplaceholder.typicode.com/posts  url’ine
                    asagidaki body ile bir POST request gonderdigimizde
                        {
                        "title":"API",
                        "body":"API ogrenmek ne guzel",
                        "userId":10
                        }
                     donen Response’un,
                        status code’unun 201,
                        ve content type’inin application/json
                        ve Response Body'sindeki,"title"'in "API" oldugunu
                        "userId" degerinin 100'den kucuk oldugunu,
                        "body" nin "API" kelimesi icerdigini
                        test edin.
         */

        // 1- Request yollayabilmek icin endpoint ve gerekiyorsa request body hazirlayin

        String endpoint = "https://jsonplaceholder.typicode.com/posts";

        JSONObject requestBody = new JSONObject();
        requestBody.put("title","API");
        requestBody.put("body","API ogrenmek ne guzel");
        requestBody.put("userId",10);

        // 2- Soruda verilmisse expected response'i kodlarimizda olusturalim
        // 3- Request'i gonderip, donen response'i kaydedelim

        Response actualResponse = RestAssured
                                        .given().contentType(ContentType.JSON)
                                        .when().body(requestBody.toString())
                                        .post(endpoint);

        /*
                {
                    "title": "API",
                    "body": "API ogrenmek ne guzel",
                    "userId": 10,
                    "id": 101
                }
         */
        // 4- Istenen testleri yap

        //                        status code’unun 201,
        //                        ve content type’inin application/json
        //                        ve Response Body'sindeki,"title"'in "API" oldugunu
        //                        "userId" degerinin 100'den kucuk oldugunu,
        //                        "body" nin "API" kelimesi icerdigini


        actualResponse
                .then()
                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("title", Matchers.equalTo("API"))
                .body("userId",Matchers.lessThan(100))
                .body("body",Matchers.containsString("API"));
    }
}
