package Lesson3DataProvider;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.nio.charset.StandardCharsets;

public class MultipleData {
    @Test(dataProvider = "testdata")
    public void addBook(String isbn,String aisle)
    {
        String resp=given().log().all().contentType("application/json").body(AddBookPayLoad.addBook(isbn,aisle)).
                when().post("http://216.10.245.166/Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js = new JsonPath(resp);
        String id= js.get("ID");
        System.out.println("ID of the added book is "+id);
    }
    @DataProvider(name="testdata")
    public Object[][] data()
    {
        return new Object[][]{{"abc","1234"},{"defg","5678"},{"hijk","15678"}};
    }
}
