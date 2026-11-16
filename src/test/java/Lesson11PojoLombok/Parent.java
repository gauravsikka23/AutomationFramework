package Lesson11PojoLombok;

import lombok.Data;
import org.testng.annotations.DataProvider;

import java.util.List;

@Data
public class Parent {

    private List<Result> results;



}
