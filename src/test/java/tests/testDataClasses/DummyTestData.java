package tests.testDataClasses;

import org.json.JSONObject;

public class DummyTestData {

    public static int basariliSorguStatusKod = 200;
    public static String basariliSorguContentType = "application/json";

    /*
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

    public static JSONObject expectedResponseOlustur(String status,int id, String employee_name,
                                                    int employee_salary, int employee_age,
                                                     String profile_image, String message){

        JSONObject expectedResponseBody = new JSONObject();

        JSONObject dataJsonObjesi = new JSONObject();
        dataJsonObjesi.put("id",id);
        dataJsonObjesi.put("employee_name",employee_name);
        dataJsonObjesi.put("employee_salary",employee_salary);
        dataJsonObjesi.put("employee_age",employee_age);
        dataJsonObjesi.put("profile_image",profile_image);

        expectedResponseBody.put("status",status);
        expectedResponseBody.put("data",dataJsonObjesi);
        expectedResponseBody.put("message", message);

        return expectedResponseBody;
    }







    public static JSONObject expectedResponseOlustur(){

        JSONObject expectedResponseBody = new JSONObject();

        JSONObject dataJsonObjesi = new JSONObject();
        dataJsonObjesi.put("id",3);
        dataJsonObjesi.put("employee_name","Ashton Cox");
        dataJsonObjesi.put("employee_salary",86000);
        dataJsonObjesi.put("employee_age",66);
        dataJsonObjesi.put("profile_image","");

        expectedResponseBody.put("status","success");
        expectedResponseBody.put("data",dataJsonObjesi);
        expectedResponseBody.put("message","Successfully! Record has been fetched.");

       return expectedResponseBody;
    }



}
