package tests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class C08_JsonPathKullanimi {

    @Test
    public void test01(){

        JSONObject kisiBilgileriJsonObj= new JSONObject();

        JSONObject adresJsonObj= new JSONObject();
        adresJsonObj.put("streetAddress","naist street");
        adresJsonObj.put("city","Nara");
        adresJsonObj.put("postalCode","630-0192");

        JSONArray telefonBilgileriArr= new JSONArray();

        JSONObject cepTelJsonObj= new JSONObject();
        cepTelJsonObj.put("type","iPhone");
        cepTelJsonObj.put("number","0123-4567-8888");

        JSONObject evTelJsonObj= new JSONObject();
        evTelJsonObj.put("type","home");
        evTelJsonObj.put("number","0123-4567-8910");

        telefonBilgileriArr.put(cepTelJsonObj);
        telefonBilgileriArr.put(evTelJsonObj);

        kisiBilgileriJsonObj.put("firstName","John");
        kisiBilgileriJsonObj.put("lastName","Doe");
        kisiBilgileriJsonObj.put("age",26);
        kisiBilgileriJsonObj.put("address",adresJsonObj);
        kisiBilgileriJsonObj.put("phoneNumbers",telefonBilgileriArr);


        System.out.println(kisiBilgileriJsonObj);

        /*
                {
                    "firstName":"John",
                    "lastName":"Doe",
                    "address":{
                        "streetAddress":"naist street",
                        "city":"Nara",
                        "postalCode":"630-0192"
                        },
                     "age":26,
                     "phoneNumbers":
                        [
                          {
                           "number":"0123-4567-8888",
                           "type":"iPhone"
                           },
                          {
                            "number":"0123-4567-8910",
                            "type":"home"
                            }
                          ]
                     }
         */
        // kisisel bilgilerde olan cep telefonu numarasini yazdirin
        System.out.println(kisiBilgileriJsonObj.getJSONArray("phoneNumbers").getJSONObject(0).getString("number"));

        // kisisel bilgilerde olan city'i yazdirin
        System.out.println(kisiBilgileriJsonObj.getJSONObject("address").getString("city")); // Nara


        // kisisel bilgilerde olan age'i yazdirin
        System.out.println(kisiBilgileriJsonObj.getInt("age")); // 26

        // kisisel bilgilerde olan firstname'i yazdirin
        System.out.println(kisiBilgileriJsonObj.getString("firstName")); // John

    }
}
