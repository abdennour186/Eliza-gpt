package fr.univ_lyon1.info.m1.elizagpt.model.verb;

/**
 * The Verb class represents a verb in a natural language, providing
 * information about its conjugations.
 *
 * @version 1.0
 */
public class Verb {
    private final String firstSingular;
    private final String secondPlural;

    /**
     * Constructs a new Verb instance with the specified conjugations.
     *
     * @param firstSingular The conjugation for the first person singular.
     * @param secondPlural  The conjugation for the second person plural.
     */
    public Verb(final String firstSingular, final String secondPlural) {
        this.firstSingular = firstSingular;
        this.secondPlural = secondPlural;
    }

    /**
     * Gets the conjugation for the first person singular.
     *
     * @return The conjugation for the first person singular.
     */
    public String getFirstSingular() {
        return firstSingular;
    }

    /**
     * Gets the conjugation for the second person plural.
     *
     * @return The conjugation for the second person plural.
     */
    public String getSecondPlural() {
        return secondPlural;
    }

    /**
     * Returns a string representation of the Verb.
     *
     * @return The string representation of the Verb.
     */
    @Override
    public String toString() {
        return "Verb{"
                + "firstSingular='"
                + firstSingular
                + '\''
                + ", secondPlural='"
                + secondPlural
                + '\''
                + '}';
    }
}
