package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler;
import fr.univ_lyon1.info.m1.elizagpt.model.verb.Verb;
import fr.univ_lyon1.info.m1.elizagpt.model.verb.VerbManager;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class VerbResponseHandler extends RandomResponse implements ResponseHandler {

    private ResponseHandler nextHandler;
    protected static final List<Verb> VERBS = VerbManager.getInstance("./src/main/resources/french-verb-conjugation.csv").getVerbs();

    @Override
    public String handleResponse(final String userMessage) {
        Pattern pattern = Pattern.compile("(Je .*)\\.", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(userMessage);
        if (matcher.matches()) {
            final String startQuestion = pickRandom(new String[]{
                    "Pourquoi dites-vous que ",
                    "Pourquoi pensez-vous que ",
                    "Êtes-vous sûr que ",
            });
            return startQuestion + firstToSecondPerson(matcher.group(1)) + " ?";
        }
        return nextHandler.handleResponse(userMessage);
    }


    public String firstToSecondPerson(final String text) {

        String processedText = text
                .replace("mon ", "votre ")
                .replace("ma ", "votre ")
                .replace("mes ", "vos ")
                .replace("moi", "vous");

        for (Verb v : VERBS) {
                        processedText = processedText.replaceAll(
                            "[Jj]e " + v.getFirstSingular(),
                                "vous " + v.getSecondPlural());
        }
        return processedText;
    }

    @Override
    public void setNextHandler(final ResponseHandler handler) {
        this.nextHandler = handler;
    }
}
