package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

public class C06_GetRequestResponseBodyTesti {

    @Test
    public void test01(){

        /*
        https://jsonplaceholder.typicode.com/posts/44 url'ine
        bir GET request yolladigimizda donen Response’in
        	status code'unun 200,
        	ve content type'inin Aplication.JSON,
        	ve response body'sinde bulunan userId'nin 5,
        	ve response body'sinde bulunan title'in "optio dolor molestias sit"
        	oldugunu test edin.

         */

        // 1- Request yollayabilmek icin endpoint ve gerekiyorsa request body hazirlayin

        String endpoint = "https://jsonplaceholder.typicode.com/posts/44";

        // 2- Soruda verilmisse expected response'i kodlarimizda olusturalim
        // 3- Request'i gonderip, donen response'i kaydedelim
        Response actualResponse = RestAssured
                                        .given()
                                        .when()
                                        .get(endpoint);

        // 4- Istenen testleri yap
        /*
        {
                "userId": 5,
                "id": 44,
                "title": "optio dolor molestias sit",
                "body": "temporibus est consectetur dolore\net libero debitis vel velit laboriosam quia\nipsum quibusdam qui itaque fuga rem aut\nea et iure quam sed maxime ut distinctio quae"
            }
         */


        //          status code'unun 200,
        //        	ve content type'inin Aplication.JSON,
        //        	ve response body'sinde bulunan userId'nin 5,
        //        	ve response body'sinde bulunan title'in "optio dolor molestias sit"

        actualResponse
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("userId", Matchers.equalTo(5))
                .body("title",Matchers.equalTo("optio dolor molestias sit"));


    }
}
