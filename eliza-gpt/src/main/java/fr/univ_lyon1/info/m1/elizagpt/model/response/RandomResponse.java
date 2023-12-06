package fr.univ_lyon1.info.m1.elizagpt.model.response;

import java.util.Random;

public abstract class RandomResponse {
    protected final Random random = new Random();
    public <T> T pickRandom(final T[] array) {
        return array[random.nextInt(array.length)];
    }

}
