package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

public class C03_ResponseBilgileriniTestEtme {

    @Test
    public void test01(){
        // https://restful-booker.herokuapp.com/booking/10 url’ine
        // bir GET request gonderdigimizde donen Response’un,
        // 	status code’unun 200,
        //	ve content type’inin application/json; charset=utf-8,
        //	ve Server isimli Header’in degerinin Cowboy,
        //	ve status Line’in HTTP/1.1 200 OK oldugunu test edin

        // 1- Request icin endpoint ve gerekiyorsa request body hazirla
        String endpoint = "https://restful-booker.herokuapp.com/booking/10";

        // 2- soruda verilmisse expected response olustur
        // 3- Request'i gonder ve donen response'i kaydet

        Response response = RestAssured.
                                given().
                                when().
                                get(endpoint);

        response.prettyPrint();

        // 4- Istenen testleri yap

        response
                .then()
                .assertThat()
                .statusCode(200)// 	status code’unun 200,
                .contentType("application/json; charset=utf-8")//	ve content type’inin application/json; charset=utf-8,
                .header("Server","Cowboy")//	ve Server isimli Header’in degerinin Cowboy,
                .statusLine("HTTP/1.1 200 OK");//	ve status Line’in HTTP/1.1 200 OK oldugunu test edin








    }
}
