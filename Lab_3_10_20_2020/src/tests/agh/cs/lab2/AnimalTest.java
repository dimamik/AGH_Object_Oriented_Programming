package agh.cs.lab2;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    @Test
    void move_Right_ShouldChangeOrientationToTheRightOne() {
        // given
        Animal animal = new Animal();
        // when
        MoveDirection moveDirection = MoveDirection.RIGHT;
        Animal to_compare = new Animal(MapDirection.EAST, 2, 2);
        // then
        animal.move(moveDirection);
        assertEquals(animal, to_compare);
    }

    @Test
    void move_Left_ShouldChangeOrientationToTheLeftOne() {
        // given
        Animal animal = new Animal();
        // when
        MoveDirection moveDirection = MoveDirection.LEFT;
        Animal to_compare = new Animal(MapDirection.WEST, 2, 2);
        // then
        animal.move(moveDirection);
        assertEquals(animal, to_compare);
    }

    // Tests for Forward moves

    @Test
    void move_Forward_North_ShouldAddOneToY() {
        // given
        Animal animal = new Animal();
        // when
        MoveDirection moveDirection = MoveDirection.FORWARD;
        Animal to_compare = new Animal(MapDirection.NORTH, 2, 3);
        // then
        animal.move(moveDirection);
        assertEquals(animal, to_compare);
    }

    @Test
    void move_Forward_South_ShouldSubtractOneFromY() {
        // given
        Animal animal = new Animal(MapDirection.SOUTH, 2, 2);
        // when
        MoveDirection moveDirection = MoveDirection.FORWARD;
        Animal to_compare = new Animal(MapDirection.SOUTH, 2, 1);
        // then
        animal.move(moveDirection);
        assertEquals(animal, to_compare);
    }

    @Test
    void move_Forward_East_ShouldAddOneToX() {
        // given
        Animal animal = new Animal(MapDirection.EAST, 2, 2);
        // when
        MoveDirection moveDirection = MoveDirection.FORWARD;
        Animal to_compare = new Animal(MapDirection.EAST, 3, 2);
        // then
        animal.move(moveDirection);
        assertEquals(animal, to_compare);
    }

    @Test
    void move_Forward_West_ShouldSubtractOneFromX() {
        // given
        Animal animal = new Animal(MapDirection.WEST, 2, 2);
        // when
        MoveDirection moveDirection = MoveDirection.FORWARD;
        Animal to_compare = new Animal(MapDirection.WEST, 1, 2);
        // then
        animal.move(moveDirection);
        assertEquals(animal, to_compare);
    }

    // Tests For Backward moves

    @Test
    void move_Backward_North_ShouldAddOneToY() {
        // given
        Animal animal = new Animal();
        // when
        MoveDirection moveDirection = MoveDirection.BACKWARD;
        Animal to_compare = new Animal(MapDirection.NORTH, 2, 1);
        // then
        animal.move(moveDirection);
        assertEquals(animal, to_compare);
    }

    @Test
    void move_Backward_South_ShouldSubtractOneFromY() {
        // given
        Animal animal = new Animal(MapDirection.SOUTH, 2, 2);
        // when
        MoveDirection moveDirection = MoveDirection.BACKWARD;
        Animal to_compare = new Animal(MapDirection.SOUTH, 2, 3);
        // then
        animal.move(moveDirection);
        assertEquals(animal, to_compare);
    }

    @Test
    void move_Backward_East_ShouldAddOneToX() {
        // given
        Animal animal = new Animal(MapDirection.EAST, 2, 2);
        // when
        MoveDirection moveDirection = MoveDirection.BACKWARD;
        Animal to_compare = new Animal(MapDirection.EAST, 1, 2);
        // then
        animal.move(moveDirection);
        assertEquals(animal, to_compare);
    }

    @Test
    void move_Backward_West_ShouldSubtractOneFromX() {
        // given
        Animal animal = new Animal(MapDirection.WEST, 2, 2);
        // when
        MoveDirection moveDirection = MoveDirection.BACKWARD;
        Animal to_compare = new Animal(MapDirection.WEST, 3, 2);
        // then
        animal.move(moveDirection);
        assertEquals(animal, to_compare);
    }
}