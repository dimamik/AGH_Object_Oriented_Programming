package agh.cs.lab4;

import java.util.LinkedList;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] array) {
        List<MoveDirection> listOfMoves = new LinkedList<>();
        for (String el : array) {
            if (changeToMapDirectionIfPossible(el) != null) {
                listOfMoves.add(changeToMapDirectionIfPossible(el));
            }
        }
        return listOfMoves;
    }

    private static MoveDirection changeToMapDirectionIfPossible(String el) {
        return switch (el) {
            case "f", "forward" -> MoveDirection.FORWARD;
            case "b", "backward" -> MoveDirection.BACKWARD;
            case "r", "right" -> MoveDirection.RIGHT;
            case "l", "left" -> MoveDirection.LEFT;
            // Returning of null can be replaced with returning Null Object Pattern
            default -> null;
        };
    }
}
