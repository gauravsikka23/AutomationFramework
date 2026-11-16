package Lesson4PayloadFromExternalFile;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import  static io.restassured.RestAssured.*;
public class ExternalFile  {

    @Test
    public void payLoadFromExternalFile() throws  Exception
    {
       RestAssured.baseURI="https://rahulshettyacademy.com";
        String resp=given().log().all().contentType("application/json").queryParam("key","qaclick123").body(new String(Files.readAllBytes(Paths.get("C:\\gaurav\\AddPlace.json")))).
                when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = new JsonPath(resp);
        String place_id=js.get("place_id");
        System.out.println(" Place id of new added place is "+place_id);
    }
}
