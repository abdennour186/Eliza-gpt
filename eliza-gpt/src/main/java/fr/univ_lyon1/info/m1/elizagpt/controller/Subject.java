package fr.univ_lyon1.info.m1.elizagpt.controller;

import fr.univ_lyon1.info.m1.elizagpt.command.Update;
import fr.univ_lyon1.info.m1.elizagpt.view.ViewObserver;

import java.util.ArrayList;

/**
 * The abstract class Subject serves as the subject in the Observer design pattern.
 * It maintains a list of observers, notifies them of state changes,
 * and allows them to register and unregister.
 */
public abstract class Subject {
    /**
     * The list of observers subscribed to updates from this subject.
     */
    private final ArrayList<ViewObserver> observers;

    /**
     * Enum representing possible actions that can trigger updates to observers.
     */
    enum ACTION {
        ADD,
        DELETE,
        SEARCH,
        UNDOSEARCH
    }

    /**
     * Constructs a new Subject with an empty list of observers.
     */
    public Subject() {
        this.observers = new ArrayList<>();
    }

    /**
     * Notifies all registered observers of a state change
     * based on the specified action and update.
     *
     * @param action  The action that triggered the update (ADD, DELETE, SEARCH, UNDOSEARCH).
     * @param update  The update object containing additional information about the state change.
     * @throws IllegalArgumentException If the specified action is undefined.
     */
    public void notifyObservers(final ACTION action, final Update update) {
        observers.forEach(observer -> {
            switch (action) {
                case ADD:
                    observer.onMessageAddUpdate(update);
                    break;
                case DELETE:
                    observer.onDeleteUpdate(update);
                    break;
                case SEARCH:
                    observer.onSearchUpdate(update);
                    break;
                case UNDOSEARCH:
                    observer.onUndoSearchUpdate(update);
                    break;
                default:
                    throw new IllegalArgumentException("Undefined Action");
            }
        });
    }

    /**
     * Registers a new observer to receive updates from this subject.
     *
     * @param observer The observer to register.
     */
    public void registerObserver(final ViewObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Unregisters an observer, removing it from the list of observers.
     *
     * @param observer The observer to unregister.
     */
    public void unregisterObserver(final ViewObserver observer) {
        this.observers.remove(observer);
    }
}
