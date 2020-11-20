package agh.cs.lab5_and_others.maps;

import agh.cs.lab5_and_others.movement.Vector2d;
import agh.cs.lab5_and_others.objects.IMapElement;

public interface IPositionChangeObserver {
    /**
     * We delete from HashMap old position and add new position
     *
     * @param movedElement - element placed to newPosition
     * @param oldPosition  - old position of element
     * @param newPosition  - new position of element
     */
    void positionChanged(IMapElement movedElement, Vector2d oldPosition, Vector2d newPosition);
}
