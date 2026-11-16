package Lesson11PojoLombok;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;
import static io.restassured.RestAssured.*;

public class MainClass {

    public static void main(String[] args) {
        Street st = new Street();
        st.setNumber(123);
        st.setName("Main Street");
        Coordinate cod= new Coordinate();
        cod.setLatitude(12.34);
        cod.setLongitude(67.89);
        Location lc = new Location();
        lc.setCoordinates(cod);
        lc.setStreet(st);
        Login lg = new Login();
        lg.setUuid("xxxx");
        Name nm = new Name();
        nm.setTitle("Ms");
        nm.setFirst("Sara");
        nm.setLast("Smith");
        Result rs = new Result();
        rs.setName(nm);
        rs.setLocation(lc);
        rs.setLogin(lg);
        rs.setGender("female");
        Parent pr = new Parent();
        pr.setResults(List.of(rs));

        RestAssured.baseURI = "https://randomuser.me";

        Response response = given()
                .log().all()
                .when()
                .get("/api")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println(response.asPrettyString());
        /*


        System.out.println("Gender : " +
                response.getResults().get(0).getGender());

        System.out.println("Name : " +
                response.getResults().get(0).getName().getFirst() + " "
                + response.getResults().get(0).getName().getLast());

        System.out.println("Street : " +
                response.getResults().get(0).getLocation().getStreet().getName());

        System.out.println("Latitude : " +
                response.getResults().get(0).getLocation().getCoordinates().getLatitude());

        System.out.println("UUID : " +
                response.getResults().get(0).getLogin().getUuid()); */

    }
}
