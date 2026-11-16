package Lesson2;
//import java.lang;
import io.restassured.path.json.JsonPath;

public class ComplexDataParsing {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(ComplexJson.complexdata());
        //Print no of courses
        int count = js.getInt("courses.size()");
         System.out.println(count);
         //Print purchase amount
        int purchAmt=js.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase Amount is "+purchAmt);
        //Print title of the first course
        String title=js.get("courses[0].title");
        System.out.println("Title of the first course is "+title);
        //Print title of all course and their price
        for(int i=0;i<count;i++)
        {
            String courseTitle=js.get("courses.title["+i+"]");
            int price=js.get("courses.price["+i+"]");
            System.out.println("Title of the "+(i+1)+" course is "+courseTitle+ " and price is "+ price);

        }

        //Print no of copies sold by RPA
        for(int i=0;i<count;i++)
        {
            String courseTitle=js.get("courses.title["+i+"]");
            if(courseTitle.equalsIgnoreCase("RPA")) {
                int noofcopies = js.get("courses.copies[" + i + "]");
                System.out.println("No of Copies Sold by RPA is " + noofcopies);
            break;
            }
        }
        //verify if the sum of all the courses matches with purchase amount
        int sum=0;
        for(int i=0;i<count;i++)
        {
            int noofcopies = js.get("courses.copies[" + i + "]");
            int price=js.get("courses.price["+i+"]");
            sum=sum+(noofcopies*price);
        }
        System.out.println("Sum of all the books price is "+sum);
        if(sum==purchAmt)
            System.out.println("Sum and purchase amount is matching");
        else
            System.out.println("Sum and Purchase amount is not matching");
    }
}