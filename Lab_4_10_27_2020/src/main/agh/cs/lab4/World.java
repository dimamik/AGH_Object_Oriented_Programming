package agh.cs.lab4;

import java.util.List;

/**
 * Project structure could be changed,
 * but for convenience it remains the same as in previous labs
 */
public class World {
    public static void main(String[] args) {
        List<MoveDirection> directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        map.place(new Animal(map));
        map.place(new Animal(map, new Vector2d(3, 4)));
        map.run(directions);
        System.out.println(map);
    }
}
