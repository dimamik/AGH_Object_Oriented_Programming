package agh.cs.lab3;

import java.util.Objects;

public class Animal {
    private final static Vector2d RIGHT_BOARDER = new Vector2d(4, 4);
    private final static Vector2d LEFT_BOARDER = new Vector2d(0, 0);

    private MapDirection orientation;
    private Vector2d position;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Animal animal = (Animal) o;
        return orientation == animal.orientation && position.equals(animal.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orientation, position);
    }

    public Animal() {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    public Animal(MapDirection orientation, int x, int y) {
        this.orientation = orientation;
        this.position = new Vector2d(x, y);
    }

    @Override
    public String toString() {
        return "Animal{" + "orientation=" + orientation + ", position=" + position + '}';
    }

    private Vector2d moveForwardIfPossible() {
        Vector2d possible_move = position.add(orientation.toUnitVector());
        if (possible_move.precedes(RIGHT_BOARDER) && possible_move.follows(LEFT_BOARDER)) {
            return possible_move;
        }
        return position;
    }

    private Vector2d moveBackwardsIfPossible() {
        Vector2d possible_move = position.subtract(orientation.toUnitVector());
        if (possible_move.precedes(RIGHT_BOARDER) && possible_move.follows(LEFT_BOARDER)) {
            return possible_move;
        }
        return position;
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> position = moveForwardIfPossible();
            case BACKWARD -> position = moveBackwardsIfPossible();
        }
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }
}
