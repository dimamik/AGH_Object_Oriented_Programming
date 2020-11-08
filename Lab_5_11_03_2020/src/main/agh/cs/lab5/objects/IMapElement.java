package agh.cs.lab5.objects;

import agh.cs.lab5.movement.Vector2d;

/**
 * Useless for now interface which can help us in future in adding new
 * objects
 */
public interface IMapElement {
    String toString();

    boolean equals(Object o);

    int hashCode();

    Vector2d getPosition();
}
