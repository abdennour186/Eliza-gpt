package fr.univ_lyon1.info.m1.elizagpt.model.verb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The VerbManager class is responsible for managing and loading verb conjugations from a file.
 *
 * @version 1.0
 */
public class VerbManager {

    private static VerbManager instance = null;
    private final List<Verb> verbs;

    /**
     * Constructs a new VerbManager instance with an empty list of verbs and loads verbs from the specified file.
     *
     * @param filePath The path to the file containing verb conjugations.
     */
    private VerbManager(final String filePath) {
        this.verbs = new ArrayList<>();
        this.loadVerbsFromFile(filePath);
    }

    /**
     * Gets the singleton instance of the VerbManager, creating it if it doesn't exist.
     *
     * @param filePath The path to the file containing verb conjugations.
     * @return The singleton instance of the VerbManager.
     */
    public static VerbManager getInstance(String filePath) {
        if (instance == null) {
            instance = new VerbManager(filePath);
        }
        return instance;
    }

    /**
     * Gets the list of loaded verb conjugations.
     *
     * @return The list of verb conjugations.
     */
    public List<Verb> getVerbs() {
        return verbs;
    }

    /**
     * Loads verb conjugations from the specified file.
     *
     * @param filePath The path to the file containing verb conjugations.
     */
    private void loadVerbsFromFile(final String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Read the first line to get column titles
            if ((line = reader.readLine()) != null) {
                // Split the line into cells using a comma as the delimiter
                String[] columnTitles = line.split(",");

                int firstSingularIndex = Arrays.asList(columnTitles)
                        .indexOf("indicative|present|first person singular");
                int secondPluralIndex = Arrays.asList(columnTitles)
                        .indexOf("indicative|present|second person plural");

                while ((line = reader.readLine()) != null) {
                    // Split the line into cells using a comma as the delimiter
                    String[] verbs = line.split(",");

                    String firstSingular = Arrays.asList(verbs).get(firstSingularIndex);
                    String secondPlural = Arrays.asList(verbs).get(secondPluralIndex);
                    if (!firstSingular.isEmpty()) {
                        this.verbs.add(new Verb(firstSingular, secondPlural));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
    }
}
