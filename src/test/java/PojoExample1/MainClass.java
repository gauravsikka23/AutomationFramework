package PojoExample1;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class MainClass {
    public static  void  main(String[] args) {
        Rating rt = new Rating();
        rt.setCount(120);
        rt.setRate(3.9);
        Root rc = new Root();
        rc.setId(1);
        rc.setTitle("Fjallraven Backpack");
        rc.setPrice(109.95);
        rc.setDescription("Your's perfect pack");
        rc.setCategory("men's clothing");
        rc.setImage("https://...");
        rc.setRating(rt);

        RestAssured.baseURI="https://fakestoreapi.com/";
        Response resp=given().log().all().pathParams("id",1)
                .when().get("/products/{id}").then().log().all().extract().response();

        System.out.println("Response is "+resp.asPrettyString());

        Root rt1=resp.as(Root.class);
        System.out.println("ID from the response is "+rt1.getId());
        System.out.println("Title from the response is "+rt1.getTitle());
        System.out.println("Price from the response is "+rt1.getPrice());
        System.out.println("Description from the response is "+rt1.getDescription());
        System.out.println("Category from the response is "+rt1.getCategory());
        System.out.println("Image from the response is "+rt1.getImage());
        System.out.println("Rate from the response is "+rt1.getRating().getRate());
        System.out.println("Count from the response is "+rt1.getRating().getCount());




    }
}
