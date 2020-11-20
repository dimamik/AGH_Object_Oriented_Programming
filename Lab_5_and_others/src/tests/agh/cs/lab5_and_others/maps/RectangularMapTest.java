package agh.cs.lab5_and_others.maps;

import agh.cs.lab5_and_others.directions.MapDirection;
import agh.cs.lab5_and_others.directions.MoveDirection;
import agh.cs.lab5_and_others.movement.Vector2d;
import agh.cs.lab5_and_others.objects.Animal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests needed to be run all together which produce main integration test
 * Dependencies are from top to bottom
 */

class RectangularMapTest {
    public static Animal animal1;
    public static Animal animal2;
    public static Animal animal3;
    public static Animal animal4;
    public static Animal animal5;

    public static RectangularMap rectangularMap;

    @BeforeEach
    public void init() {
        rectangularMap = new RectangularMap(10, 10);
        animal1 = new Animal(rectangularMap, new Vector2d(1, 1));
        animal2 = new Animal(rectangularMap, new Vector2d(2, 2));
        animal3 = new Animal(rectangularMap, new Vector2d(3, 3));
        animal4 = new Animal(rectangularMap, new Vector2d(1, 1));
        animal5 = new Animal(rectangularMap, new Vector2d(-5, -5));
        rectangularMap.place(animal3);
        rectangularMap.place(animal1);
    }

    @Test
    void place_PutTheAnimalOnTheMap_ReturnsTrueIfCanElseThrowsIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> rectangularMap.place(animal4)
        );
    }

    @Test
    void canMoveTo_IndicateIfMovableObjectCanMoveToThisPosition_ReturnsTrueIfCan() {
        try {
            assertFalse(rectangularMap.canMoveTo(new Vector2d(1, -1)));

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.toString());
        }
        try {
            assertFalse(rectangularMap.canMoveTo(new Vector2d(1, 1)));
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.toString());
        }
    }


    @Test
    void run_IteratesListAndMovesAnimal() {
        List<MoveDirection> directions = new LinkedList<>();
        directions.add(MoveDirection.BACKWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.BACKWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.FORWARD);
        directions.add(MoveDirection.RIGHT);
        directions.add(MoveDirection.LEFT);
        directions.add(MoveDirection.LEFT);
        try {
            rectangularMap.run(directions);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.toString());
        }
        HashMap<Vector2d, Animal> should_return = new HashMap<>();
        Animal animal_tmp = new Animal(rectangularMap, new Vector2d(1, 1), MapDirection.NORTH);
        Animal animal_tmp2 = new Animal(rectangularMap, new Vector2d(3, 6), MapDirection.WEST);
        should_return.put(new Vector2d(3, 2), animal_tmp);
        should_return.put(new Vector2d(1, 4), animal_tmp2);

    }

    @Test
    void isOccupied_IfAnimalIsThere() {
        assertFalse(rectangularMap.isOccupied(new Vector2d(10, 10)));

        assertTrue(rectangularMap.isOccupied(new Vector2d(1, 1)));
    }

    @Test
    void objectAt_ReturnsAnObjectOnGivenPositionOrOptional() {
        // given
        RectangularMap rectangularMap = new RectangularMap(4, 4);
        Animal animal6 = new Animal(rectangularMap);
        // then
        rectangularMap.place(animal6);
        // when
        Optional<Object> opt = rectangularMap.objectAt(new Vector2d(2, 3));
        assertFalse(opt.isPresent());
    }
}