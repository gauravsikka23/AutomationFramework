package PojoExample3;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import  static io.restassured.RestAssured.*;
public class Root {

    public static void main(String[] args)
    {
       RestAssured.baseURI="https://dummyjson.com";

        Response resp= given().log().all().pathParams("id",1).
                when().get("/carts/{id}").then().log().all().extract().response();

        System.out.println(resp.asPrettyString());

        ParentJson pj=resp.as(ParentJson.class);

        System.out.println("Product id is: "+pj.getId());

        System.out.println("discountPercentage of the first product is: "+pj.getProducts().get(0).getDiscountedTotal());
    }
}
