package agh.cs.lab4;

import java.util.Objects;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;
    private IWorldMap map;

    public Animal() {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    public Animal(IWorldMap map) {
        this();
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this(map);
        this.position = initialPosition;
    }

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

    @Override
    public String toString() {
        return orientation.toString();
    }

    private Vector2d moveIfPossible(Vector2d move) {
        if (map.canMoveTo(position.add(move))) {
            return position.add(move);
        } else {
            return position;
        }
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> position = moveIfPossible(orientation.toUnitVector());
            case BACKWARD -> position = moveIfPossible(orientation.toUnitVector().opposite());
        }
    }

    public Vector2d getPosition() {
        return position;
    }
}
