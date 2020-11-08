package agh.cs.lab5.movement;

import agh.cs.lab5.directions.MoveDirection;

import java.util.LinkedList;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] array) {
        List<MoveDirection> listOfMoves = new LinkedList<>();
        for (String el : array) {
            changeToMapDirectionIfPossible(el, listOfMoves);
        }
        return listOfMoves;
    }

    private static void changeToMapDirectionIfPossible(String el, List<MoveDirection> listOfMoves) {
        switch (el) {
            case "f", "forward" -> listOfMoves.add(MoveDirection.FORWARD);
            case "b", "backward" -> listOfMoves.add(MoveDirection.BACKWARD);
            case "r", "right" -> listOfMoves.add(MoveDirection.RIGHT);
            case "l", "left" -> listOfMoves.add(MoveDirection.LEFT);
        }
        ;
    }
}
