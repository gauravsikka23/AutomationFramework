package Lesson9PojoDeSerialization;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import  static io.restassured.RestAssured.*;
public class Main {

    public static void main(String[] args) {

        String response = 	given()
                .formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParams("grant_type","client_credentials")
                .formParams("scope","trust")
                .when().log().all()
                .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

        System.out.println(response);
        JsonPath jsonPath = new JsonPath(response);
        String accessToken = jsonPath.getString("access_token");



               Root gc =given()
                .queryParams("access_token",accessToken)
                .when().log().all()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").then().log().all().extract().response().as(Root.class);
        //Root gc=rs.as(Root.class);
        System.out.println(gc.getLinkedIn());
        System.out.println(gc.getInstructor());
        System.out.println(gc.getExpertise());
        System.out.println(gc.getServices());
        System.out.println(gc.getUrl());
        System.out.println(gc.getCourses().getApi().get(0).getCourseTitle());
        System.out.println(gc.getCourses().getWebAutomation().get(2).getPrice());
        //Print the price of "SoapUI Webservices testing" course
        for(int i=0;i<gc.getCourses().getApi().size();i++)
        {

            if(gc.getCourses().getApi().get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
            {
                System.out.println("Price of Soap Ui webservices testing course is " +gc.getCourses().getApi().get(i).getPrice());
                break;
            }
        }
        //Print all the course titles of Web Automation courses
        for(int i=0;i<gc.getCourses().getWebAutomation().size();i++)
        {
            System.out.println("Title of the course " +(i+1)+" is "+gc.getCourses().getWebAutomation().get(i).getCourseTitle());
        }

    }
}