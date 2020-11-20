package agh.cs.lab5_and_others.objects;

import agh.cs.lab5_and_others.maps.IPositionChangeObserver;

public interface IPositionChangedPublisher {

    /**
     * Adds observer to the lists of observers
     *
     * @param observer
     */
    void addObserver(IPositionChangeObserver observer);

    /**
     * Removes observer from list of observers
     *
     * @param observer
     */
    void removeObserver(IPositionChangeObserver observer);
}
