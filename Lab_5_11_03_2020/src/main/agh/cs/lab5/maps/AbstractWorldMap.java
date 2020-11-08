package agh.cs.lab5.maps;

import agh.cs.lab5.directions.MoveDirection;
import agh.cs.lab5.movement.Vector2d;
import agh.cs.lab5.objects.Animal;
import agh.cs.lab5.visual.MapVisualiser;

import java.util.*;

/**
 * Abstract class representing map where different types of
 * objects can be placed.
 * //TODO Can be better standardised using IMovable interface
 */
public abstract class AbstractWorldMap implements IWorldMap {
    protected HashMap<Vector2d, Animal> animalHashMap;
    protected MapVisualiser mapVisualiser;

    /**
     * Default Constructor to be used by children
     */
    public AbstractWorldMap() {
        animalHashMap = new HashMap<>();
        mapVisualiser = new MapVisualiser(this);
    }

    /**
     * Indicate if any object can move to the given position.
     *
     * @param position The position checked for the movement possibility.
     * @return True if the object can move to that position.
     * @implNote Depends on object-transaction properties
     */
    abstract public boolean canMoveTo(Vector2d position);

    /**
     * Place an animal on the map.
     *
     * @param animal The animal to place on the map.
     * @return True if the animal was placed. The animal cannot be placed if the map
     * is already occupied.
     */
    public boolean place(Animal animal) {
        if (animal.getPosition() != null && canMoveTo(animal.getPosition())) {
            animalHashMap.put(animal.getPosition(), animal);
            return true;
        }
        return false;
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
    public void run(List<MoveDirection> directions) {
        List<Animal> listOfAnimals = new LinkedList<>(animalHashMap.values());
        Iterator<Animal> animalIterator = listOfAnimals.iterator();
        Iterator<MoveDirection> directionsIterator = directions.iterator();
        while (animalIterator.hasNext() && directionsIterator.hasNext()) {
            Animal currAnimal = animalIterator.next();
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
    public Optional<Object> objectAt(Vector2d position) {
        return Optional.ofNullable(animalHashMap.get(position));
    }

    /**
     * Help function for toString(),
     * Calculates the left bottom and right top borders of map
     *
     * @return List of Vector2d where zero element is left_bottom corner
     * and first element is right_top corner
     */
    abstract protected List<Vector2d> getBorders();

    /**
     * Function uses MapVisualiser to convert map to String
     *
     * @return String representation of map
     */
    public String toString() {
        List<Vector2d> list = getBorders();
        return mapVisualiser.draw(list.get(0), list.get(1));
    }

    public HashMap<Vector2d, Animal> getAnimalHashMap() {
        return animalHashMap;
    }
}
