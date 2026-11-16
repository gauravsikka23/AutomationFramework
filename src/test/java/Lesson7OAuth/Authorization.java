package Lesson7OAuth;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import  static  io.restassured.RestAssured.*;
public class Authorization {
    @Test
    public void Auth() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParams("grant_type", "client_credentials")
                .formParams("scope", "trust").when().log().all()
                .post("/oauthapi/oauth2/resourceOwner/token").then().log().all().extract().response().asString();

        JsonPath js = new JsonPath(response);
        String accesstoken=js.getString("access_token");
        System.out.println("Access Token is "+accesstoken);
        System.out.print("****************************");

        String resp2=given().log().all().queryParam("access_token",accesstoken)
                .when().get("/oauthapi/getCourseDetails")
                .then().log().all().extract().response().asString();

        System.out.println("Final Response "+resp2);
    }
}
