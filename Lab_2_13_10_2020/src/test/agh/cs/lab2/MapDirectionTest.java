package agh.cs.lab2;

import org.junit.jupiter.api.Test;
// In this test class
// I would be using default methods naming convention
import static org.junit.jupiter.api.Assertions.*;
class MapDirectionTest {



    @Test
    void next() {
        //given
        MapDirection east = MapDirection.EAST;
        MapDirection west = MapDirection.WEST;
        MapDirection south = MapDirection.SOUTH;
        MapDirection north = MapDirection.NORTH;
        //when
        Vector2d result_1 = east.toUnitVector();
        Vector2d result_2 = west.toUnitVector();
        Vector2d result_3 = south.toUnitVector();
        Vector2d result_4 = north.toUnitVector();
        //then
        assertEquals(result_1, new Vector2d(1, 0));
        assertEquals(result_2, new Vector2d(-1, 0));
        assertEquals(result_3, new Vector2d(0, -1));
        assertEquals(result_4,new Vector2d(0, 1));

    }

    @Test
    void previous() {
        //given
        MapDirection east = MapDirection.EAST;
        MapDirection west = MapDirection.WEST;
        MapDirection south = MapDirection.SOUTH;
        MapDirection north = MapDirection.NORTH;
        //when
        MapDirection result_1 = east.previous();
        MapDirection result_2 = west.previous();
        MapDirection result_3 = south.previous();
        MapDirection result_4 = north.previous();
        //then
        assertEquals(result_1, MapDirection.NORTH);
        assertEquals(result_2,MapDirection.SOUTH);
        assertEquals(result_3, MapDirection.EAST);
        assertEquals(result_4,MapDirection.WEST);
    }
}