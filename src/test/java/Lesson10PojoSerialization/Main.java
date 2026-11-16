package Lesson10PojoSerialization;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
public class Main {

    public static  void main(String[] args)
    {
        User user =new User();
        user.setUserId(1001);
        user.setBody("Hello");
        user.setTitle("MR");
        user.setId(121);
      Response resp=  given().baseUri("https://jsonplaceholder.typicode.com")
                .contentType("application/json").body(user) // Java object converted to JSON automatically
                .when().post("/posts")
                .then().statusCode(201).extract().response();
      System.out.println(resp.asPrettyString());

      User userresponse= resp.as(User.class);
      System.out.println(userresponse.getBody());
      System.out.println(userresponse.getUserId());

    }
}
