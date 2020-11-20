package agh.cs.lab5_and_others.objects;

import agh.cs.lab5_and_others.directions.MapDirection;
import agh.cs.lab5_and_others.directions.MoveDirection;
import agh.cs.lab5_and_others.maps.IPositionChangeObserver;
import agh.cs.lab5_and_others.maps.IWorldMap;
import agh.cs.lab5_and_others.movement.Vector2d;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Animal implements IMapElement, IPositionChangedPublisher {
    private MapDirection orientation;
    private Vector2d position;
    private final IWorldMap map;
    private final List<IPositionChangeObserver> listOfObservers;

    public Animal(IWorldMap map) {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
        this.map = map;
        this.listOfObservers = new LinkedList<>();
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this(map);
        this.position = initialPosition;
    }

    public Animal(IWorldMap map, Vector2d initialPosition, MapDirection orientation) {
        this(map, initialPosition);
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
            case FORWARD -> {
                //Notify all observers
                if (!position.equals(moveIfPossible(orientation.toUnitVector()))) {
                    positionChanged(position, moveIfPossible(orientation.toUnitVector()));
                }
                position = moveIfPossible(orientation.toUnitVector());
            }
            case BACKWARD -> {
                //Notify all observers
                if (!position.equals(moveIfPossible(orientation.toUnitVector().opposite()))) {
                    positionChanged(position, moveIfPossible(orientation.toUnitVector().opposite()));
                }
                position = moveIfPossible(orientation.toUnitVector().opposite());
            }
        }
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public boolean isMovable() {
        return true;
    }

    @Override
    public void addObserver(IPositionChangeObserver observer) {
        listOfObservers.add(observer);
    }

    @Override
    public void removeObserver(IPositionChangeObserver observer) {
        listOfObservers.remove(observer);
    }

    private void positionChanged(Vector2d old_position, Vector2d new_position) {
        for (IPositionChangeObserver observer : listOfObservers) {
            observer.positionChanged(this, old_position, new_position);
        }
    }
}
