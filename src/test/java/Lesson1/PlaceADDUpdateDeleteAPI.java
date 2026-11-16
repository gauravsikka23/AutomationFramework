package Lesson1;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PlaceADDUpdateDeleteAPI {

    public  static  void  main(String[] args)
    {
      RestAssured.baseURI="https://rahulshettyacademy.com";

      String resp=given().log().all().queryParam("key","qaclick123").contentType("application/json")
              .body(Payload.addPayload()).when().post("maps/api/place/add/json")
              .then().log().all().assertThat().statusCode(200).body("status",equalTo("OK")).extract().response().asString();
        JsonPath js = new JsonPath(resp);
        String place_id=js.get("place_id");
        System.out.println(place_id);

        //update Address

        String address= "10/103 Ved Homes";
        given().queryParam("key","qaclick123").log().all().contentType("application/json")
                .body(Payload.updatePlace(place_id,address)).when().put("maps/api/place/update/json")
                .then().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
        //get place details  post update

       String getplaceresp= given().log().all().contentType("application/json").queryParam("key","qaclick123").queryParam("place_id",place_id)
                .get("maps/api/place/get/json").then().log().all().statusCode(200).extract().response().asString();
       JsonPath js1= new JsonPath(getplaceresp);
       String actualAddress= js1.get("address");
        Assert.assertEquals(address,actualAddress);

    }
}
