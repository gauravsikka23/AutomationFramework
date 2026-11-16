package SpecBuilderExample;
import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EcommerceApiTest {
    public static  void main(String[] args) {
        RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
        LoginRequest lr = new LoginRequest();
        lr.setUserEmail("sikkagaurav@hotmail.com");
        lr.setUserPassword("Atharv@123");

        RequestSpecification reqlogin=given().log().all().spec(reqSpec).body(lr);

        LoginResponse loginrp = reqlogin.when().post("/api/ecom/auth/login").then().log().all().extract().response().as(LoginResponse.class);

        System.out.println(loginrp.getToken());
        System.out.println(loginrp.getUserId());

        String token=loginrp.getToken();
        String userId=loginrp.getUserId();
        //Add Product API

        RequestSpecification requestSpec= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization",token).build();

        RequestSpecification reqaddProd= given().log().all().spec(requestSpec).param("productAddedBy",userId).param("productCategory","fashion")
                .param("productSubCategory","shirts").param("productPrice","18500").
                param("productDescription","Lenovo").param("productFor","men").
                multiPart("productImage", new File("C:\\Users\\sikka\\Pictures\\Lenovo.png")).param("productName","Laptop");
        String response= reqaddProd.when().post("/api/ecom/product/add-product").then().log().all().extract().response().asString();

        JsonPath js = new JsonPath(response);
        String prodId= js.get("productId");

        //Create Order API
        RequestSpecification reqSpecCreateOrder= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization",token).build();
        OrderDetails od = new OrderDetails();
        od.setCountry("india");
        od.setProductOrderedId(prodId);

        List<OrderDetails> orderlist = new ArrayList<OrderDetails>();
        orderlist.add(od);

        Orders order = new Orders();
        order .setOrders(orderlist);;

        RequestSpecification createorder =given().log().all().spec(reqSpecCreateOrder).body(order);

        String resp=createorder.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();

        System.out.println(resp);



    }
}
