package agh.cs.lab5;

import agh.cs.lab5.directions.MoveDirection;
import agh.cs.lab5.maps.GrassField;
import agh.cs.lab5.maps.IWorldMap;
import agh.cs.lab5.movement.OptionsParser;
import agh.cs.lab5.movement.Vector2d;
import agh.cs.lab5.objects.Animal;

import java.util.List;

/**
 * This main class is taking in-stream and parsing it for MoveDirection list
 * than placing two Animals on GrassField map,
 * And one by one moving animals corresponding to list of directions
 * Then printing out the result map
 */
public class World {
    public static GrassField map;
    public static void main(String[] args) {
        List<MoveDirection> directions = OptionsParser.parse(args);
        map = new GrassField(10);
        map.place(new Animal(map, new Vector2d(1, 1)));
        map.place(new Animal(map, new Vector2d(3, 4)));
        map.place(new Animal(map, new Vector2d(31, 40)));
        map.run(directions);
        System.out.println(map);
    }
}
