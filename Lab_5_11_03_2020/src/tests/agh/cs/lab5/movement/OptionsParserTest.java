package agh.cs.lab5.movement;

import agh.cs.lab5.World;
import agh.cs.lab5.directions.MoveDirection;
import agh.cs.lab5.maps.RectangularMap;
import agh.cs.lab5.objects.Animal;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OptionsParserTest {

    @Test
    void parse_CheckIfInputIsWellHandled() {

        // given

        // then
        String[] list = new String[]{"l", "left", "b", "backward", "some stuff", "r", "right", "f", "forward",
                "another staff"};
        List<MoveDirection> listOfMoves = OptionsParser.parse(list);
        List<MoveDirection> listOfRightMoves = new LinkedList<MoveDirection>();
        listOfRightMoves.add(MoveDirection.LEFT);
        listOfRightMoves.add(MoveDirection.LEFT);
        listOfRightMoves.add(MoveDirection.BACKWARD);
        listOfRightMoves.add(MoveDirection.BACKWARD);
        listOfRightMoves.add(MoveDirection.RIGHT);
        listOfRightMoves.add(MoveDirection.RIGHT);
        listOfRightMoves.add(MoveDirection.FORWARD);
        listOfRightMoves.add(MoveDirection.FORWARD);
        // when
        assertEquals(listOfMoves,listOfRightMoves);
    }
}