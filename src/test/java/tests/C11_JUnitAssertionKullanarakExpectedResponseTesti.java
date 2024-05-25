package tests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class C11_JUnitAssertionKullanarakExpectedResponseTesti {

    @Test
    public void test01(){

         /*
        https://jsonplaceholder.typicode.com/posts/22 url'ine
        bir GET request yolladigimizda
        donen response body’sinin asagida verilen ile ayni oldugunu test ediniz

        Response body :

        {
        "userId":3,
        "id":22,
        "title":"dolor sint quo a velit explicabo quia nam",
        "body":"eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse"
        }
     */

        // 1- Request yollayabilmek icin endpoint ve gerekiyorsa request body hazirlayin

        String endpoint = "https://jsonplaceholder.typicode.com/posts/22";

        // 2- Soruda verilmisse expected response'i kodlarimizda olusturalim
        //    expected response'in body'sini JSONObject olarak olusturabiliriz

        JSONObject expectedResponseBody = new JSONObject();
        expectedResponseBody.put("userId",3);
        expectedResponseBody.put("id",22);
        expectedResponseBody.put("title","dolor sint quo a velit explicabo quia nam");
        expectedResponseBody.put("body","eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");

        // 3- Request'i gonderip, donen response'i kaydedelim

        Response actualResponse = RestAssured
                                    .given()
                                    .when()
                                    .get(endpoint);

        // 4- Istenen testleri yap

        // Bize soruda response'in asagidaki gibi oldugunu test edin diyerek
        // expected response body'si verilmisse
        // tum attribute'leri test etmeliyiz
        // actual response'daki id == expectedResponseBody 'deki id

        actualResponse
                .then()
                .assertThat()
                .body("userId", equalTo(3) ,
                        "id", equalTo(22),
                        "title",equalTo("dolor sint quo a velit explicabo quia nam")
                        );


        // madem ki expectedResonseBody olusturduk, olmasi gereken degerleri oradan almaliyiz

        actualResponse
                .then()
                .assertThat()
                .body("userId", equalTo(expectedResponseBody.getInt("userId")) ,
                        //actualResponse'daki userId'nin degeri esit olmalidir
                        //expectedResponseBody'deki userId'nin degerine
                        "id", equalTo(expectedResponseBody.getInt("id")),
                        "title",equalTo(expectedResponseBody.getString("title"))
                );

        // JUnit'deki assert method'larini kullanarak da assertion yapabiliriz
        // ANCAK response'dan direk olarak degerleri almamiz mumkun olmadigindan
        // once response'i JSonPath objesine cevirmeliyiz

        JsonPath actualResponseJPath = actualResponse.jsonPath();

        assertEquals(   expectedResponseBody.getInt("userId"),
                        actualResponseJPath.getInt("userId"));


        assertEquals(   expectedResponseBody.getInt("id"),
                        actualResponseJPath.getInt("id"));

        assertEquals(   expectedResponseBody.getString("title"),
                        actualResponseJPath.getString("title"));
    }
}
