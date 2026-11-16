package Lesson11PojoLombok;

import lombok.Data;

@Data
public class Result {

    private Name name;
    private String gender;
    private Location location;
    private Login login;

}
