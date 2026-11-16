package Lesson5FileAsAttachment;
import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import java.io.File;

public class AttachmentData {

    @Test
public void attachedFile()
    {

        File file = new File("C:\\Users\\sikka\\Pictures\\dr.jpg");

        Response response =
                given()
                .baseUri("https://httpbin.org").contentType("multipart/form-data")
                .multiPart("file", file)
                .when()
                .post("/post")
                .then()
                .extract()
                .response();

        System.out.println(response.getStatusCode());
        System.out.println(response.asPrettyString());
    }
}
