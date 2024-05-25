package tests.testDataClasses;

import org.json.JSONObject;

public class BookerTestData {

    // Bu class'daki temel amacimiz
    // request body, response body gibi icice JSONObject'leri tek sefer olusturup
    // ihtiyac duydukca buradan kullanabilmek
    // Eger isterseniz basariliSorguStatusCode... gibi degerleri de buradan test datasi olarak olusturup
    // dinamik olarak kullanabiliriz

    public static JSONObject requestBodyOlustur(){

        /*
                    {
                    "firstname" : "Hasan",
                    "lastname" : "Yagmur",
                    "totalprice" : 500,
                    "depositpaid" : false,
                    "bookingdates" : {
                        "checkin" : "2021-06-01",
                        "checkout" : "2021-06-10"
                        },
                    "additionalneeds" : "wi-fi"
                    }
         */

        JSONObject requestBody = new JSONObject();

        JSONObject bookingdatesJsonObject = new JSONObject();
        bookingdatesJsonObject.put("checkin","2021-06-01");
        bookingdatesJsonObject.put("checkout","2021-06-10");

        requestBody.put("firstname","Hasan");
        requestBody.put("additionalneeds","wi-fi");
        requestBody.put("bookingdates",bookingdatesJsonObject);
        requestBody.put("totalprice",500);
        requestBody.put("depositpaid",false);
        requestBody.put("lastname","Yagmur");

        return requestBody;
    }

    public static JSONObject responseBodyOlustur(){
        /*
                {
                "bookingid":24,
                "booking":{
                    "firstname":"Hasan",
                    "lastname":"Yagmur",
                    "totalprice":500,
                    "depositpaid":false,
                    "bookingdates":{
                        "checkin":"2021-06-01",
                        "checkout":"2021-06-10"
                        },
                "additionalneeds":"wi-fi"
                }
         */

        JSONObject responseBody= new JSONObject();
        JSONObject bookingBody = new JSONObject();

        JSONObject bookingdatesBody = new JSONObject();
        bookingdatesBody.put("checkin","2021-06-01");
        bookingdatesBody.put("checkout","2021-06-10");

        bookingBody.put("firstname","Hasan");
        bookingBody.put("additionalneeds","wi-fi");
        bookingBody.put("bookingdates",bookingdatesBody);
        bookingBody.put("totalprice",500);
        bookingBody.put("depositpaid",false);
        bookingBody.put("lastname","Yagmur");

        responseBody.put("bookingid",24);
        responseBody.put("booking",bookingBody);

        return responseBody;
    }

}
