package Lesson12SpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
public class EcommerceTest {

    public static void main(String[] args)
    {
        AddPlace p=new AddPlace();
        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen 09");
        p.setLanguage("French-IN");
        p.setName("Frontline house");
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("http://google.com");
        List<String> myList=new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");
        p.setTypes(myList);
        Location l=new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);

        RequestSpecification reqspec= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123").setContentType(ContentType.JSON).build();

        RequestSpecification resp=given().log().all().spec(reqspec).body(p);
        ResponseSpecification responspec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        String response = resp.when().post("/maps/api/place/add/json").then().log().all().spec(responspec).extract().response().asString();
        System.out.println(response);

        JsonPath js = new JsonPath(response);
        String placeid= js.getString("place_id");
        System.out.println("Place id is "+placeid);
    }
}
