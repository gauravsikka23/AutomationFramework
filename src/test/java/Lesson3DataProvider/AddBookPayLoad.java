package Lesson3DataProvider;

public class AddBookPayLoad {

    public static String addBook(String isbn, String aisle)
    {
        String payload="{\r\n" +
                "\"name\":\"Learn Appium Automation with Java\",\r\n" +
                "\"isbn\":\""+isbn+"\",\r\n" +
                "\"aisle\":\""+aisle+"\",\r\n" +
                "\"author\":\"John foe\"\r\n" +
                "}";
        return payload;
    }
}
