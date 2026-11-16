package Lesson6DataFromExcel;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import  static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataRetrival {

    @Test
    public void addBook() throws Exception
    {
        DataDriven dd= new DataDriven();
        ArrayList<String> data=dd.getdata("AddBook","Sheet1");
        HashMap<String,Object> hm= new HashMap<>();
        hm.put("name",data.get(1));
        hm.put("isbn",data.get(2));
        hm.put("aisle",data.get(3));
        hm.put("author",data.get(4));

        RestAssured.baseURI="http://216.10.245.166";
        String res= given().log().all().contentType("application/json").body(hm)
                .when().post("/Library/Addbook.php").then()
                .assertThat().statusCode(200).extract().response().asString();
        JsonPath js = new JsonPath(res);
        String id=js.get("ID");
        System.out.println(id);


    }
}
