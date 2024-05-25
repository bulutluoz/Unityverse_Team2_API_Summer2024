package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

public class C05_PutRequestResponseBilgileriTestEtme {


    @Test
    public void test01(){
        //https://jsonplaceholder.typicode.com/posts/70 url’ine
        // asagidaki Json formatindaki body ile bir PUT request gonderdigimizde
        //{
        //  "title":"Ahmet",
        //  "body":"Merhaba",
        //  "userId":10,
        //  "id":70
        //}
        //donen Response’un,
        // 	status code’unun 200,
        //	ve content type’inin application/json; charset=utf-8,
        //	ve Server isimli Header’in degerinin cloudflare,
        //	ve status Line’in HTTP/1.1 200 OK


        // 1- Request yollayabilmek icin endpoint ve gerekiyorsa request body hazirlayin

        String endpoint = "https://jsonplaceholder.typicode.com/posts/70";

        JSONObject requestBody = new JSONObject();
        requestBody.put("title","Ahmet");
        requestBody.put("body","Merhaba");
        requestBody.put("userId",10);
        requestBody.put("id",70);
        // {"id":70,"title":"Ahmet","body":"Merhaba","userId":10}

        // 2- Soruda verilmisse expected response'i kodlarimizda olusturalim

        // 3- Request'i gonderip, donen response'i kaydedelim

        Response actualResponse = RestAssured
                                        .given().contentType(ContentType.JSON)
                                        .when().body(requestBody.toString())
                                        .put(endpoint);


        // 4- Istenen testleri yap

        // 	status code’unun 200,
        //	ve content type’inin application/json; charset=utf-8,
        //	ve Server isimli Header’in degerinin cloudflare,
        //	ve status Line’in HTTP/1.1 200 OK

        actualResponse
                .then()
                .assertThat()
                .statusCode(200).contentType("application/json; charset=utf-8")
                .header("Server","cloudflare")
                .statusLine("HTTP/1.1 200 OK");


    }
}
