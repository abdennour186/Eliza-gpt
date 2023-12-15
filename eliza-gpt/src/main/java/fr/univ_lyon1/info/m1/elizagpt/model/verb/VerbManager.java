package fr.univ_lyon1.info.m1.elizagpt.model.verb;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class VerbManager {

    private static VerbManager instance = null;
    private final List<Verb> verbs;
    private VerbManager(String filePath) {
        this.verbs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Read the first line to get column titles
            if ((line = reader.readLine()) != null) {
                // Split the line into cells using a comma as the delimiter
                String[] columnTitles = line.split(",");

                int firstSingularIndex = Arrays.asList(columnTitles).indexOf("indicative|present|first person singular");
                int secondPluralIndex = Arrays.asList(columnTitles).indexOf("indicative|present|second person plural");



                while ((line = reader.readLine()) != null) {
                    // Split the line into cells using a comma as the delimiter
                    String[] verbs = line.split(",");

                    String firstSingular = Arrays.asList(verbs).get(firstSingularIndex);
                    String secondPlural = Arrays.asList(verbs).get(secondPluralIndex);
                    if(!firstSingular.isEmpty())
                        this.verbs.add(new Verb(firstSingular,secondPlural));

                }
            }
        } catch (IOException e) {
            System.out.println("something went wrong !");
        }

    }

    public static VerbManager getInstance(String filePath) {
        if(instance == null)
            instance = new VerbManager(filePath);
        return instance;
    }

    public List<Verb> getVerbs() {
        return verbs;
    }
}
