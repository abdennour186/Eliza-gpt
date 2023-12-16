package fr.univ_lyon1.info.m1.elizagpt.model.verb;

public class Verb {
    private final String firstSingular;
    private final String secondPlural;


    public Verb(final String firstSingular, final String secondPlural) {
        this.firstSingular = firstSingular;
        this.secondPlural = secondPlural;
    }

    public String getFirstSingular() {
        return firstSingular;
    }

    public String getSecondPlural() {
        return secondPlural;
    }

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
