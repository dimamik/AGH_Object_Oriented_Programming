package agh.cs.lab5_and_others.maps;

import agh.cs.lab5_and_others.directions.MoveDirection;
import agh.cs.lab5_and_others.movement.Vector2d;
import agh.cs.lab5_and_others.objects.Animal;
import agh.cs.lab5_and_others.objects.IMapElement;
import agh.cs.lab5_and_others.visual.MapVisualiser;

import java.util.*;

/**
 * Abstract class representing map where different types of
 * objects can be placed.
 * //TODO Can be better standardised using IMovable interface
 */
public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected final HashMap<Vector2d, IMapElement> animalHashMap;
    protected final MapVisualiser mapVisualiser;
    protected MapBoundary mapBoundary;

    /**
     * Default Constructor to be used by children
     */
    public AbstractWorldMap() {
        animalHashMap = new HashMap<>();
        mapVisualiser = new MapVisualiser(this);
    }

    /**
     * Place an existing animal on the map.
     * Is used only with previous removing that animal
     *
     * @param animal The animal to place on the map.
     * @throws IllegalArgumentException if can't place an animal
     */
    @Override
    public void place(Animal animal) {
        if (animal.getPosition() != null && canMoveTo(animal.getPosition())) {
            animalHashMap.put(animal.getPosition(), animal);
            return;
        }
        if (animal.getPosition() != null)
            throw new IllegalArgumentException(animal.getPosition().toString() + " is wrong position");
        else
            throw new IllegalArgumentException("Got null as a position");
    }

    /**
     * Move the animal on the map according to the provided move directions. Every
     * n-th direction should be sent to the n-th animal on the map.
     *
     * @param directions List of move directions.
     * @implNote transform hashmap to list and then backwards,
     * because if we won't do this, we'll need to deal
     * with ConcurrentModificationException
     */
    @Override
    public void run(List<MoveDirection> directions) {
        List<IMapElement> listOfAnimals = new LinkedList<>(animalHashMap.values());
        Iterator<IMapElement> animalIterator = listOfAnimals.iterator();
        Iterator<MoveDirection> directionsIterator = directions.iterator();
        while (animalIterator.hasNext() && directionsIterator.hasNext()) {
            Animal currAnimal = (Animal) animalIterator.next();
            animalHashMap.remove(currAnimal.getPosition());
            MoveDirection currDirection = directionsIterator.next();
            currAnimal.move(currDirection);
            place(currAnimal);
            if (!animalIterator.hasNext() && directionsIterator.hasNext()) {
                animalIterator = listOfAnimals.iterator();
            }
        }
    }

    /**
     * Return true if given position on the map is occupied. Should not be confused
     * with canMove since there might be empty positions where the animal cannot
     * move.
     *
     * @param position Position to check.
     * @return True if the position is occupied.
     */
    @Override
    public boolean isOccupied(Vector2d position) {
        return animalHashMap.containsKey(position);
    }

    /**
     * Return an object at a given position.
     *
     * @param position The position of the object.
     * @return Object or empty Optional if the position is not occupied.
     * @implNote Depends on objects on map (which can be grouped by interface)
     */
    @Override
    public Optional<Object> objectAt(Vector2d position) {
        return Optional.ofNullable(animalHashMap.get(position));
    }


    /**
     * Function uses MapVisualiser to convert map to String
     *
     * @return String representation of map
     */
    @Override
    public String toString() {
        return mapVisualiser.draw(mapBoundary.getLower(), mapBoundary.getUpper());
    }

    /**
     * @param movedElement - element placed to newPosition
     * @param oldPosition  - old position of element
     * @param newPosition  - new position of element
     */
    @Override
    public void positionChanged(IMapElement movedElement, Vector2d oldPosition, Vector2d newPosition) {
        animalHashMap.remove(oldPosition);
        animalHashMap.put(newPosition, movedElement);
    }

}
