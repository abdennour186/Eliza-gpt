package fr.univ_lyon1.info.m1.elizagpt.model.response;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DefaultResponseStrategy implements ResponseStrategy{

    @Override
    public String generateResponse(ArrayList<Message> messages, String userMessage) {
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
}
