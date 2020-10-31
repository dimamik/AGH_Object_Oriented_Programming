package agh.cs.lab4;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Help Unit Tests to verify the methods on development stage
 */
class RectangularMapTest {

    @Test
    void place_ShouldPlaceAnimalOnTheMap_ReturnsTrueIfSucceed() {
        // given
        RectangularMap rectangularMap = new RectangularMap(4, 4);
        Animal animal = new Animal();
        // then
        rectangularMap.place(animal);
        // when
        assertTrue(rectangularMap.mapOfAnimals2d[2][2] != null);
    }

    @Test
    void objectAt_ReturnsAnObjectOnGivenPositionOrOptional() {
        // given
        RectangularMap rectangularMap = new RectangularMap(4, 4);
        Animal animal = new Animal();
        // then
        rectangularMap.place(animal);
        // when
        Optional<Object> opt = rectangularMap.objectAt(new Vector2d(2, 3));
        assertFalse(opt.isPresent());
    }
}