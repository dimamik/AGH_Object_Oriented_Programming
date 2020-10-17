package agh.cs.lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
// In this particular test class
// I would be following Roy Osherove methods naming convention
class Vector2dTest {

    @Test
    void toString_ShouldReturnStringTuple() {
        //given
        Vector2d to_check = new Vector2d(1, 2);
        //when
        String res = to_check.toString();
        //then
        assertEquals(res, "(1,2)");
    }

    @Test
    void precede_TakesVector2d_ShouldCheckIfElementIsBehind() {
        //given
        Vector2d to_check = new Vector2d(0, 0);
        Vector2d to_check_1 = new Vector2d(0, 0);
        Vector2d to_check_2 = new Vector2d(1, 1);
        Vector2d to_check_3 = new Vector2d(-1, 1);
        Vector2d to_check_4 = new Vector2d(-1, -1);
        Vector2d to_check_5 = new Vector2d(1, -1);

        //when + then
        assertTrue(to_check_1.precedes(to_check));
        assertTrue(to_check.precedes(to_check_2));
        assertFalse(to_check.precedes(to_check_3));
        assertFalse(to_check.precedes(to_check_4));
        assertFalse(to_check.precedes(to_check_5));
    }

    @Test
    void follows_TakesVector2d_ShouldCheckIfElementIsFurther() {
        //given
        Vector2d to_check = new Vector2d(0, 0);
        Vector2d to_check_1 = new Vector2d(0, 0);
        Vector2d to_check_2 = new Vector2d(1, 1);
        Vector2d to_check_3 = new Vector2d(-1, 1);
        Vector2d to_check_4 = new Vector2d(-1, -1);
        Vector2d to_check_5 = new Vector2d(1, -1);

        //when + then
        assertTrue(to_check_1.follows(to_check));
        assertFalse(to_check.follows(to_check_2));
        assertFalse(to_check.follows(to_check_3));
        assertTrue(to_check.follows(to_check_4));
        assertFalse(to_check.follows(to_check_5));
    }

    @Test
    void upperRight_TakesVector2d_ShouldReturnUpperRightCornerOfRectangle() {
        //given
        Vector2d to_check = new Vector2d(0, 0);
        Vector2d to_check_1 = new Vector2d(0, 0);
        Vector2d to_check_2 = new Vector2d(1, 1);
        Vector2d to_check_3 = new Vector2d(-1, 1);
        Vector2d to_check_4 = new Vector2d(-1, -1);
        Vector2d to_check_5 = new Vector2d(1, -1);

        //when + then
        assertEquals(to_check.upperRight(to_check_1), new Vector2d(0, 0));
        assertEquals(to_check_1.upperRight(to_check_2), new Vector2d(1, 1));
        assertEquals(to_check_2.upperRight(to_check_3), new Vector2d(1, 1));
        assertEquals(to_check_3.upperRight(to_check_4), new Vector2d(-1, 1));
        assertEquals(to_check_4.upperRight(to_check_5), new Vector2d(1, -1));
    }

    @Test
    void lowerLeft_TakesVector2d_ShouldReturnLowerLeftCornerOfRectangle() {
        //given
        Vector2d to_check = new Vector2d(0, 0);
        Vector2d to_check_1 = new Vector2d(0, 0);
        Vector2d to_check_2 = new Vector2d(1, 1);
        Vector2d to_check_3 = new Vector2d(-1, 1);
        Vector2d to_check_4 = new Vector2d(-1, -1);
        Vector2d to_check_5 = new Vector2d(1, -1);

        //when + then
        assertEquals(to_check.lowerLeft(to_check_1), new Vector2d(0, 0));
        assertEquals(to_check_1.lowerLeft(to_check_2), new Vector2d(0, 0));
        assertEquals(to_check_2.lowerLeft(to_check_3), new Vector2d(-1, 1));
        assertEquals(to_check_3.lowerLeft(to_check_4), new Vector2d(-1, -1));
        assertEquals(to_check_4.lowerLeft(to_check_5), new Vector2d(-1, -1));
        assertEquals(to_check_3.lowerLeft(to_check_5), new Vector2d(-1, -1));
    }

    @Test
    void add_TakesVector2d_ReturnsTheSum() {
        //given
        Vector2d to_check = new Vector2d(0, 0);
        Vector2d to_check_1 = new Vector2d(0, 0);
        Vector2d to_check_2 = new Vector2d(1, 1);
        Vector2d to_check_3 = new Vector2d(-1, 1);
        Vector2d to_check_4 = new Vector2d(-1, -1);
        Vector2d to_check_5 = new Vector2d(1, -1);

        //when + then
        assertEquals(to_check.add(to_check_1), new Vector2d(0, 0));
        assertEquals(to_check_1.add(to_check_2), new Vector2d(1, 1));
        assertEquals(to_check_1.add(to_check_3), new Vector2d(-1, 1));
        assertEquals(to_check_1.add(to_check_4), new Vector2d(-1, -1));
        assertEquals(to_check_1.add(to_check_5), new Vector2d(1, -1));
        assertEquals(to_check_4.add(to_check_5), new Vector2d(0, -2));

    }

    @Test
    void subtract_TakesVector2d_ReturnsTheDifference() {
        //given
        Vector2d to_check = new Vector2d(0, 0);
        Vector2d to_check_1 = new Vector2d(0, 0);
        Vector2d to_check_2 = new Vector2d(1, 1);
        Vector2d to_check_3 = new Vector2d(-1, 1);
        Vector2d to_check_4 = new Vector2d(-1, -1);
        Vector2d to_check_5 = new Vector2d(1, -1);

        //when + then
        assertEquals(to_check.subtract(to_check_1), new Vector2d(0, 0));
        assertEquals(to_check_1.subtract(to_check_2), new Vector2d(-1, -1));
        assertEquals(to_check_1.subtract(to_check_3), new Vector2d(1, -1));
        assertEquals(to_check_1.subtract(to_check_4), new Vector2d(1, 1));
        assertEquals(to_check_1.subtract(to_check_5), new Vector2d(-1, 1));
        assertEquals(to_check_4.subtract(to_check_5), new Vector2d(-2, 0));
    }

    @Test
    void equals_TakesVector2d_ShouldTestWhetherAreEqual() {
        //given
        Vector2d to_check = new Vector2d(1, 2);
        Vector2d to_check_1 = new Vector2d(0, 0);
        Vector2d to_check_2 = new Vector2d(0, -2);
        Vector2d to_check_3 = new Vector2d(-1, -2);
        Vector2d to_check_4 = new Vector2d(0, 0);

        //when + then
        assertTrue(to_check.equals(to_check));
        assertTrue(to_check_1.equals(to_check_4));
        assertTrue(to_check_2.equals(to_check_2));
        assertFalse(to_check_2.equals(to_check_3));
    }

    @Test
    void opposite_ReturnsOppositeVector2d() {
        //given
        Vector2d to_check = new Vector2d(0, 0);
        Vector2d to_check_1 = new Vector2d(0, 0);
        Vector2d to_check_2 = new Vector2d(1, 1);
        Vector2d to_check_3 = new Vector2d(-1, 1);
        Vector2d to_check_4 = new Vector2d(-1, -1);
        Vector2d to_check_5 = new Vector2d(1, -1);

        //when + then
        assertEquals(to_check.opposite(), to_check_1);
        assertEquals(to_check_2.opposite(), to_check_4);
        assertEquals(to_check_3.opposite(), to_check_5);
    }
}