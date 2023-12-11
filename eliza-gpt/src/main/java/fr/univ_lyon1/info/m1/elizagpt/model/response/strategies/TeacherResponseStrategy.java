package fr.univ_lyon1.info.m1.elizagpt.model.response.strategies;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseStrategy;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TeacherResponseStrategy implements ResponseStrategy {

    @Override
    public String generateResponse(ArrayList<Message> messages, String userMessage) {
        Pattern pattern = Pattern.compile("Qui est le plus (.*) \\?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(userMessage);
        if (matcher.matches()) {
            return "Le plus " + matcher.group(1) + " est bien sûr votre enseignant de MIF01!";
        }
        return null;
    }
}
