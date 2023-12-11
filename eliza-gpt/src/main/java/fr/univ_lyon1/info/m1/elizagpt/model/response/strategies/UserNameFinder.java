package fr.univ_lyon1.info.m1.elizagpt.model.response.strategies;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class  UserNameFinder {

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
}
