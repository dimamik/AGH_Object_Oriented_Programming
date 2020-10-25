package agh.cs.lab3;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalIT {
    // Integration tests

    @Test
    public void IT_CheckIfTheOrientationIsGood() {
        // given
        Animal animal = new Animal(MapDirection.NORTH, 0, 0);
        // then
        String[] list = new String[] { "s", " ", "l", "l", "r", "f", "FORWARD", "l" };
        List<MoveDirection> listOfMoves = OptionsParser.parse(list);
        World.move_through_list(listOfMoves, animal);
        // when
        assertEquals(animal.getOrientation(), MapDirection.SOUTH);
    }

    @Test
    public void IT_CheckIfThePositioningIsRight() {
        // given
        Animal animal = new Animal(MapDirection.NORTH, 0, 0);
        // then
        String[] list = new String[] { "r", "f", "f", "f", "l", "f", "backward" };
        List<MoveDirection> listOfMoves = OptionsParser.parse(list);
        World.move_through_list(listOfMoves, animal);
        // when
        assertEquals(animal.getPosition(), new Vector2d(3, 0));
    }

    @Test
    public void IT_CheckIfNotLeavingTheMap() {
        // given
        Animal animal = new Animal(MapDirection.NORTH, 0, 0);
        // then
        String[] list = new String[] { "l", "f", "f", "backward" };
        List<MoveDirection> listOfMoves = OptionsParser.parse(list);
        World.move_through_list(listOfMoves, animal);
        // when
        assertEquals(animal.getPosition(), new Vector2d(1, 0));
    }

    @Test
    public void IT_CheckIfInputIsWellHandled() {
        // given
        Animal animal = new Animal(MapDirection.NORTH, 0, 0);
        // then
        String[] list = new String[] { "l", "left", "b", "backward", "some stuff", "r", "right", "f", "forward",
                "another staff" };
        List<MoveDirection> listOfMoves = OptionsParser.parse(list);
        World.move_through_list(listOfMoves, animal);
        // when
        assertEquals(animal.getPosition(), new Vector2d(0, 4));
    }
}