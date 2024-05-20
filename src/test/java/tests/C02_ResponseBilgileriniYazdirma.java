package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

public class C02_ResponseBilgileriniYazdirma {

    @Test
    public void test01(){

        // https://restful-booker.herokuapp.com/booking/10 url’ine
        // bir GET request gonderdigimizde donen Response’un,
        // 	status code’unun 200,
        //	ve content type’inin application/json; charset=utf-8,
        //	ve Server isimli Header’in degerinin Cowboy,
        //	ve status Line’in HTTP/1.1 200 OK
        //	ve response suresinin 5 sn’den kisa oldugunu manuel olarak test ediniz


        // 1- Request icin endpoint ve gerekiyorsa request body hazirla
        // 2- soruda verilmisse expected response olustur
        // 3- Request'i gonder ve donen response'i kaydet
        // 4- Istenen testleri yap


        // 1- Request icin endpoint ve gerekiyorsa request body hazirla

        String endpoint = "https://restful-booker.herokuapp.com/booking/10";

        // 2- soruda verilmisse expected response olustur
        // 3- Request'i gonder ve donen response'i kaydet

        Response response = RestAssured
                                    .given()
                                    .when()
                                    .get(endpoint);

        // 4- Istenen testleri yap

        // 	status code’unun 200,
        System.out.println("Status kodu " + response.statusCode());
        //	ve content type’inin application/json; charset=utf-8,
        System.out.println("Content type : "+response.contentType());
        //	ve Server isimli Header’in degerinin Cowboy,
        System.out.println("Server isimli Header degeri : "+response.getHeader("Server"));
        //	ve status Line’in HTTP/1.1 200 OK
        System.out.println("Status Line : " + response.statusLine());
        //	ve response suresinin 5 sn’den kisa oldugunu manuel olarak test ediniz
        System.out.println("response suresi : "+response.getTime());


    }
}
