package agh.cs.lab2;

import java.util.List;

public class World {
    public static void main(String[] args) {
        Animal animal = new Animal();
        List<MoveDirection> listOfMoves = OptionsParser.parse(args);
        move_through_list(listOfMoves, animal);
    }

    public static void move_through_list(List<MoveDirection> listOfMoves, Animal animal) {
        for (MoveDirection curr_move : listOfMoves) {
            animal.move(curr_move);
            // System.out.println(animal);
        }
    }
}
