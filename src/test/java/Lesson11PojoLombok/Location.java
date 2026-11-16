package Lesson11PojoLombok;

import lombok.Data;

@Data
public class Location {

    private Street street;
    private Coordinate coordinates;
}
