package agh.cs.lab5_6.objects;

import agh.cs.lab5_6.directions.MapDirection;
import agh.cs.lab5_6.directions.MoveDirection;
import agh.cs.lab5_6.maps.IWorldMap;
import agh.cs.lab5_6.movement.Vector2d;

import java.util.Objects;

public class Animal implements IMapElement {
    private MapDirection orientation;
    private Vector2d position;
    private final IWorldMap map;

    public Animal(IWorldMap map) {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this(map);
        this.position = initialPosition;
    }

    public Animal(IWorldMap map, Vector2d initialPosition, MapDirection orientation) {
        this(map,initialPosition);
        this.orientation = orientation;
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

    @Override
    public boolean isMovable() {
        return true;
    }
}
