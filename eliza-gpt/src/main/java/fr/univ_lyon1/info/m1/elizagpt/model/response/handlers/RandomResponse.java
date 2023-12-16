package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;

import java.util.Random;

/**
 * The RandomResponse class provides a base class
 * for handling responses with an element of randomness.
 * It includes a method for picking a random element from an array.
 *
 * @version 1.0
 */
public abstract class RandomResponse {

    protected final Random random = new Random();

    /**
     * Picks a random element from the given array.
     *
     * @param array The array from which to pick a random element.
     * @param <T>   The type of elements in the array.
     * @return The randomly selected element.
     */
    public <T> T pickRandom(final T[] array) {
        return array[random.nextInt(array.length)];
    }
}
