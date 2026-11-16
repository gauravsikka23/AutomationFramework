package PojoExample2;

import java.util.ArrayList;
import java.util.Arrays;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class Main {

    public static void main(String[] args)
    {

        Address ad = new Address();
        ad.setStreet("MG Road");
        ad.setCity("Bangalore");
        ad.setState("Karnataka");
        ad.setPincode(560001);
        Project p1= new Project();
        p1.setProjectId(1);
        p1.setProjectName("Banking");
        Project p2= new Project();
        p2.setProjectId(2);
        p2.setProjectName("HealthCare");
        Employee emp = new Employee();
        emp.setEmployeeId(101);
        emp.setName("Rahul Sharma");
        emp.setEmail("rahul@gmail.com");
        emp.setSalary(85000);
        emp.setPermanent(true);
        emp.setAddress(ad);
        emp.setSkills(Arrays.asList("Java","Selenium","Rest Assured"));
        emp.setProjects(Arrays.asList(p1,p2));
        RestAssured.baseURI = "https://66b123456789.mockapi.io/api/v1";
       Response resp= given()
                .contentType(ContentType.JSON)
                .body(emp)          // Serialization happens here
                .when()
                .post("/employees")
                .then()
                .statusCode(201).extract().response();

       System.out.println(resp.asPrettyString());


    }
}
