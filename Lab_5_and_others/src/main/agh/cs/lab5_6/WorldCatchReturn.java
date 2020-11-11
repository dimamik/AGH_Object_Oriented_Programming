package agh.cs.lab5_6;

import agh.cs.lab5_6.directions.MoveDirection;
import agh.cs.lab5_6.maps.GrassField;
import agh.cs.lab5_6.movement.OptionsParser;
import agh.cs.lab5_6.movement.Vector2d;
import agh.cs.lab5_6.objects.Animal;

import java.util.List;

/**
 * This main class is taking in-stream and parsing it for MoveDirection list
 * than placing two Animals on GrassField map,
 * And one by one moving animals corresponding to list of directions
 * Then printing out the result map
 * ATTENTION: THIS ONE IS EXITING AFTER EXCEPTION
 */
public class WorldCatchReturn {
    public static GrassField map;

    public static void main(String[] args) {
        List<MoveDirection> directions = OptionsParser.parse(args);
        map = new GrassField(10);
        try {
            map.place(new Animal(map, new Vector2d(1, 1)));
            map.place(new Animal(map, new Vector2d(3, 4)));
            map.place(new Animal(map, new Vector2d(3, 4)));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.toString());
            return;
        }
        try {
            map.place(new Animal(map, new Vector2d(31, 40)));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.toString());
        }
        map.run(directions);
        System.out.println(map);
    }
}
