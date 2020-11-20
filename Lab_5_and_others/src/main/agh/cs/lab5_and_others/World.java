package agh.cs.lab5_and_others;

import agh.cs.lab5_and_others.directions.MoveDirection;
import agh.cs.lab5_and_others.maps.GrassField;
import agh.cs.lab5_and_others.movement.OptionsParser;
import agh.cs.lab5_and_others.movement.Vector2d;
import agh.cs.lab5_and_others.objects.Animal;

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
        map.initial_place(new Animal(map, new Vector2d(3, 4)));
        map.initial_place(new Animal(map, new Vector2d(1, 4)));
        map.initial_place(new Animal(map, new Vector2d(2, 3)));
        map.initial_place(new Animal(map, new Vector2d(4, 1)));
        Animal animal = new Animal(map, new Vector2d(1, 1));
        map.initial_place(animal);
        System.out.println(map);
        animal.move(MoveDirection.BACKWARD);
        map.run(directions);
        System.out.println(map);
    }
}
