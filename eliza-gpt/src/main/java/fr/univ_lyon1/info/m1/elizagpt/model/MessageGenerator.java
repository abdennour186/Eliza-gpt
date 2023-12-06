package fr.univ_lyon1.info.m1.elizagpt.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MessageGenerator {
    private final Random random = new Random();
    protected static final List<Verb> VERBS = Arrays.asList(
            new Verb("suis", "êtes"),
            new Verb("vais", "allez"),
            new Verb("dis", "dites"),
            new Verb("ai", "avez"),
            new Verb("fais", "faites"),
            new Verb("sais", "savez"),
            // Question 02
            new Verb("peux", "pouvez"),
            new Verb("dois", "devez")
    );

    public String generateElizaResponse(final ArrayList<Message> messages,final String userMessage) {

        
        Pattern pattern;
        Matcher matcher;

        // First, try to answer specifically to what the user said
        pattern = Pattern.compile(".*Je m'appelle (.*)\\.", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(userMessage);
        if (matcher.matches()) {
            return "Bonjour " + matcher.group(1) + ".";
        }

        pattern = Pattern.compile("Quel est mon nom \\?", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(userMessage);
        if (matcher.matches()) {
            if (getUserName(messages) != null) {
                return "Votre nom est " + getUserName(messages) + ".";
            } else {
                return "Je ne connais pas votre nom.";
            }
        }

        pattern = Pattern.compile("Qui est le plus (.*) \\?", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(userMessage);
        if (matcher.matches()) {
            return "Le plus " + matcher.group(1) + " est bien sûr votre enseignant de MIF01!";
        }

        pattern = Pattern.compile("(Je .*)\\.", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(userMessage);
        if (matcher.matches()) {
            final String startQuestion = pickRandom(new String[]{
                    "Pourquoi dites-vous que ",
                    "Pourquoi pensez-vous que ",
                    "Êtes-vous sûr que ",
            });
            return startQuestion + firstToSecondPerson(matcher.group(1)) + " ?";
        }

        // Questions Detector
        pattern = Pattern.compile(".*\\?");
        matcher = pattern.matcher(userMessage);
        if (matcher.matches()) {
            return pickRandom(new String[]{
                    "Je vous renvoie la question.",
                    "Ici, c'est moi qui pose les questions.",
            });
        }

        // Nothing clever to say, answer randomly
        if (random.nextBoolean()) {
            return "Il fait beau aujourd'hui, vous ne trouvez pas ?";
        }
        if (random.nextBoolean()) {
            return "Je ne comprends pas.";
        }
        if (random.nextBoolean()) {
            return "Hmmm, hmm ...";
        }

        // Default answer
        if (getUserName(messages) != null) {
            return "Qu'est-ce qui vous fait dire cela, " + getUserName(messages) + " ?";
        } else {
            return "Qu'est-ce qui vous fait dire cela ?";
        }
    }

    public String getUserName(ArrayList<Message> messages) {

        Pattern pattern = Pattern.compile("Je m'appelle (.*)\\.", Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        ArrayList<Message> userMessages = (ArrayList<Message>) messages.stream().filter(
                message -> message.getSender() == Message.Sender.USER
        ).collect(Collectors.toList());

        for (Message message : userMessages) {
            matcher = pattern.matcher(message.getText());
            if (matcher.matches()) {
                return matcher.group(1);
            }
        }
        return null;
    }

    public String firstToSecondPerson(final String text) {
        String processedText = text
                .replaceAll("[Jj]e ([a-z]*)e ", "vous $1ez ");
        for (Verb v : VERBS) {
            processedText = processedText.replaceAll(
                    "[Jj]e " + v.getFirstSingular(),
                    "vous " + v.getSecondPlural());
        }
        processedText = processedText
                .replaceAll("[Jj]e ([a-z]*)s ", "vous $1ssez ")
                .replace("mon ", "votre ")
                .replace("ma ", "votre ")
                .replace("mes ", "vos ")
                .replace("moi", "vous");
        return processedText;
    }

    public <T> T pickRandom(final T[] array) {
        return array[random.nextInt(array.length)];
    }

}
