package agh.cs.lab5.maps;

import agh.cs.lab5.directions.MapDirection;
import agh.cs.lab5.directions.MoveDirection;
import agh.cs.lab5.movement.Vector2d;
import agh.cs.lab5.objects.Animal;
import org.junit.jupiter.api.BeforeAll;
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

    @BeforeAll
    public static void init(){
        rectangularMap =  new RectangularMap(10,10);
        animal1 = new Animal(rectangularMap,new Vector2d(1,1));
        animal2 = new Animal(rectangularMap,new Vector2d(2,2));
        animal3 = new Animal(rectangularMap,new Vector2d(3,3));
        animal4 = new Animal(rectangularMap,new Vector2d(1,1));
        animal5 = new Animal(rectangularMap,new Vector2d(-5,-5));
    }

    @Test
    public void place_PutTheAnimalOnTheMap_ReturnsTrueIfCan() {
        assertTrue(
                rectangularMap.place(animal1)
        );
        assertFalse(
                rectangularMap.place(animal4)
        );
        assertFalse(
                rectangularMap.place(animal5)
        );
        assertTrue(
                rectangularMap.place(animal3)
        );
    }
    @Test
    void canMoveTo_IndicateIfMovableObjectCanMoveToThisPosition_ReturnsTrueIfCan() {
        assertFalse(rectangularMap.canMoveTo(new Vector2d(1,-1)));
        assertFalse(rectangularMap.canMoveTo(new Vector2d(1,1)));
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
        rectangularMap.run(directions);
        HashMap<Vector2d,Animal> should_return = new HashMap<>();
        Animal animal_tmp = new Animal(rectangularMap,new Vector2d(1,1), MapDirection.NORTH);
        Animal animal_tmp2 = new Animal(rectangularMap,new Vector2d(3,6), MapDirection.WEST);
        should_return.put(new Vector2d(1,1),animal_tmp);
        should_return.put(new Vector2d(3,6),animal_tmp2);
        assertEquals(rectangularMap.getAnimalHashMap().toString(),should_return.toString());
    }

    @Test
    void isOccupied_IfAnimalIsThere() {
        assertFalse(rectangularMap.isOccupied(new Vector2d(10,10)));
        assertTrue(rectangularMap.isOccupied(new Vector2d(1,1)));
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