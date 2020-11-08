package agh.cs.lab5.maps;

import agh.cs.lab5.directions.MoveDirection;
import agh.cs.lab5.movement.Vector2d;
import agh.cs.lab5.objects.Animal;
import agh.cs.lab5.objects.Grass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    public static GrassField grassField;
    @BeforeEach
    public void setUp() {
        grassField = new GrassField(15);
    }

    @Test
    void place_PutTheAnimalOnTheMap_ReturnsTrueIfCan() {
        assertTrue(grassField.place(new Animal(grassField, new Vector2d(2,2))));
        assertTrue(grassField.place(new Animal(grassField, new Vector2d(3,3))));
        assertFalse(grassField.place(new Animal(grassField, new Vector2d(2,2))));
    }

    @Test
    void canMoveTo_IndicateIfMovableObjectCanMoveToThisPosition_ReturnsTrueIfCan() {
        grassField.place(new Animal(grassField, new Vector2d(2,2)));
        assertTrue(grassField.canMoveTo(new Vector2d(1,-1)));
        assertFalse(grassField.canMoveTo(new Vector2d(2,2)));
    }

    @Test
    void canMoveTo_IfWeCanMoveToFieldWithGrass(){
        HashMap<Vector2d, Grass> grassHashMap = grassField.getGrassHashMap();
        Map.Entry<Vector2d,Grass> entry = grassHashMap.entrySet().iterator().next();
        Vector2d key = entry.getKey();
        assertTrue(grassField.canMoveTo(key));
    }

    @Test
    void run_IteratesListAndMovesAnimal() {
        grassField.place(new Animal(grassField, new Vector2d(2,2)));
        List<MoveDirection> directions = new LinkedList<>();
        directions.add(MoveDirection.BACKWARD);
        directions.add(MoveDirection.FORWARD);
        grassField.run(directions);
        assertEquals(grassField.getAnimalHashMap().toString(),"{(2,2)=^}");
    }

    @Test
    void isOccupied_IfSomeObjectIsThere() {
        grassField.place(new Animal(grassField, new Vector2d(10,10)));
        HashMap<Vector2d, Grass> grassHashMap = grassField.getGrassHashMap();
        Map.Entry<Vector2d,Grass> entry = grassHashMap.entrySet().iterator().next();
        Vector2d key = entry.getKey();
        assertTrue(grassField.isOccupied(new Vector2d(10,10)));
        assertTrue(grassField.isOccupied(key));
    }

    @Test
    void objectAt_ReturnsAnObjectOnGivenPositionOrOptional() {
        // given + then
        GrassField grassField1 = new GrassField(4);
        grassField.place(new Animal(grassField, new Vector2d(10,10)));
        // when
        Optional<Object> opt = grassField1.objectAt(new Vector2d(10, 10));
        assertFalse(opt.isPresent());

    }
}