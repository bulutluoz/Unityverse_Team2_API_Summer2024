package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import tests.testDataClasses.DummyTestData;

public class C13_TestDatasiIleAssertions {

    @Test
    public void test01(){

         /*
        http://dummy.restapiexample.com/api/v1/employee/3 url’ine
        bir GET request gonderdigimizde
        donen response’un status code’unun 200,
        content Type’inin application/json
        ve body’sinin asagidaki gibi oldugunu test edin.

          Expected Response Body
            {
                "status":"success",
                "data":{
                        "id":3,
                        "employee_name":"Ashton Cox",
                        "employee_salary":86000,
                        "employee_age":66,
                        "profile_image":""
                        },
                "message":"Successfully! Record has been fetched."
            }
         */

        // 1- Request yollayabilmek icin endpoint ve gerekiyorsa request body hazirlayin

        String endpoint = "http://dummy.restapiexample.com/api/v1/employee/3";

        // 2- Soruda verilmisse expected response'i kodlarimizda olusturalim
        JSONObject expectedResponse = DummyTestData.expectedResponseOlustur();

        // 3- Request'i gonderip, donen response'i kaydedelim

        Response actualResponse = RestAssured
                                            .given()
                                            .when()
                                            .get(endpoint);

        // 4- Istenen testleri yap


        actualResponse
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON);

        // Eger response bilgilerini de test datasi class'indan almak istersek
        // bu degerleri class level'da static olarak olusturup
        // oradan alabiliriz

        Assert.assertEquals(DummyTestData.basariliSorguStatusKod,actualResponse.statusCode());
        Assert.assertEquals(DummyTestData.basariliSorguContentType,actualResponse.contentType());



        JsonPath actualResponseJPath = actualResponse.jsonPath();

        // employee_name'lerin ayni oldugunu test edelim

        Assert.assertEquals(
               expectedResponse.getJSONObject("data").getString("employee_name") ,
               actualResponseJPath.getString("data.employee_name")
        );


        // employee_age'lerin ayni oldugunu test edelim
        Assert.assertEquals(
                expectedResponse.getJSONObject("data").getInt("employee_age"),
                actualResponseJPath.getInt("data.employee_age")
        );


        // employee_salary'lerin ayni oldugunu test edelim
        Assert.assertEquals(
                expectedResponse.getJSONObject("data").getInt("employee_salary"),
                actualResponseJPath.getInt("data.employee_salary")
        );

        // message degerlerinin ayni oldugunu test edelim

        Assert.assertEquals(
                expectedResponse.getString("message"),
                actualResponseJPath.getString("message")
        );


        // status degerlerinin ayni oldugunu test edelim

        Assert.assertEquals(
                expectedResponse.getString("status"),
                actualResponseJPath.getString("status")
        );








    }
}
