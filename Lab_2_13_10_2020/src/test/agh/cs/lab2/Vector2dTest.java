package agh.cs.lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Vector2dTest {

    @Test
    void testToString() {
        //given
        Vector2d to_check = new Vector2d(1, 2);
        //when
        String res = to_check.toString();
        //then
        assertEquals(res, "(1,2)");
    }

    @Test
    void precedes() {
        //given
        Vector2d to_check = new Vector2d(1, 2);
        Vector2d to_check_1 = new Vector2d(0, 0);
        Vector2d to_check_2 = new Vector2d(0, -2);
        Vector2d to_check_3 = new Vector2d(-1, -2);

        //then
        assertTrue(to_check_1.precedes(to_check));
        assertTrue(to_check_2.precedes(to_check_1));
        assertTrue(to_check_3.precedes(to_check_2));

    }

    @Test
    void follows() {
        //given
        Vector2d to_check = new Vector2d(1, 2);
        Vector2d to_check_1 = new Vector2d(0, 0);
        Vector2d to_check_2 = new Vector2d(0, -2);
        Vector2d to_check_3 = new Vector2d(-1, -2);

        //then
        assertTrue(to_check.follows(to_check_1));
        assertTrue(to_check_1.follows(to_check_2));
        assertTrue(to_check_2.follows(to_check_3));
    }

    @Test
    void upperRight() {
        //given
        Vector2d to_check = new Vector2d(1, 2);
        Vector2d to_check_1 = new Vector2d(0, 0);
        Vector2d to_check_2 = new Vector2d(0, -2);
        Vector2d to_check_3 = new Vector2d(-1, -2);

        //then
        assertEquals(to_check.upperRight(to_check_1), new Vector2d(1, 2));
        assertEquals(to_check_1.upperRight(to_check_2), new Vector2d(0, 0));
        assertEquals(to_check_2.upperRight(to_check_3), new Vector2d(0, -2));
    }

    @Test
    void lowerLeft() {
        //given
        Vector2d to_check = new Vector2d(1, 2);
        Vector2d to_check_1 = new Vector2d(0, 0);
        Vector2d to_check_2 = new Vector2d(0, -2);
        Vector2d to_check_3 = new Vector2d(-1, -2);

        //then
        assertEquals(to_check.lowerLeft(to_check_1), new Vector2d(0, 0));
        assertEquals(to_check_1.lowerLeft(to_check_2), new Vector2d(0, -2));
        assertEquals(to_check_2.lowerLeft(to_check_3), new Vector2d(-1, -2));
    }

    @Test
    void add() {
        //given
        Vector2d to_check = new Vector2d(1, 2);
        Vector2d to_check_1 = new Vector2d(0, 0);
        Vector2d to_check_2 = new Vector2d(0, -2);
        Vector2d to_check_3 = new Vector2d(-1, -2);

        //then
        assertEquals(to_check.add(to_check_1), new Vector2d(1, 2));
        assertEquals(to_check_1.add(to_check_2), new Vector2d(0, -2));
        assertEquals(to_check_2.add(to_check_3), new Vector2d(-1, -4));
    }

    @Test
    void subtract() {
        //given
        Vector2d to_check = new Vector2d(1, 2);
        Vector2d to_check_1 = new Vector2d(0, 0);
        Vector2d to_check_2 = new Vector2d(0, -2);
        Vector2d to_check_3 = new Vector2d(-1, -2);

        //then
        assertEquals(to_check.subtract(to_check_1), new Vector2d(1, 2));
        assertEquals(to_check_1.subtract(to_check_2), new Vector2d(0, 2));
        assertEquals(to_check_2.subtract(to_check_3), new Vector2d(1, 0));
    }

    @Test
    void testEquals() {
        //given
        Vector2d to_check = new Vector2d(1, 2);
        Vector2d to_check_1 = new Vector2d(0, 0);
        Vector2d to_check_2 = new Vector2d(0, -2);
        Vector2d to_check_3 = new Vector2d(-1, -2);
        Vector2d to_check_4 = new Vector2d(0, 0);

        //then
        assertTrue(to_check.equals(to_check));
        assertTrue(to_check_1.equals(to_check_4));
        assertTrue(to_check_2.equals(to_check_2));
        assertFalse(to_check_2.equals(to_check_3));
    }

    @Test
    void opposite() {
        //given
        Vector2d to_check = new Vector2d(1, 2);
        Vector2d to_check_1 = new Vector2d(0, 0);
        Vector2d to_check_2 = new Vector2d(0, 0);
        Vector2d to_check_3 = new Vector2d(-1, -2);

        //then
        assertEquals(to_check.opposite(), to_check_3);
        assertEquals(to_check_1.opposite(), to_check_2);
    }
}