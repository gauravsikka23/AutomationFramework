package Lesson8PathParam;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
public class PathParameter {
    @Test
    public void path() {
        RestAssured.baseURI="https://jsonplaceholder.typicode.com";
        String resp = given().when().get("/users").then().assertThat().statusCode(200).extract().response().asString();
        JsonPath js = new JsonPath(resp);

        int id=js.getInt("[0].id");
        double lat=js.getDouble("[0].address.geo.lat");
        System.out.println(lat);
        System.out.println(id);

        String res2=given().log().all().pathParams("Id",id).when().get("/users/{Id}").then()
                .assertThat().statusCode(200).extract().response().asString();

        JsonPath js2= new JsonPath(res2);
        System.out.print("Response of Path Parameter API is "+res2);



    }
}
