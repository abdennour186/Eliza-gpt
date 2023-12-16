package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler;
import fr.univ_lyon1.info.m1.elizagpt.model.verb.Verb;
import fr.univ_lyon1.info.m1.elizagpt.model.verb.VerbManager;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The VerbResponseHandler class handles responses
 * related to verb conjugations in the ElizaGPT application.
 * It specifically responds to statements starting with "Je" (I)
 * by converting them to second-person questions.
 *
 * <p>This class extends the {@link RandomResponse} class
 * to incorporate randomization in responses
 * and implements the {@link ResponseHandler} interface
 * to provide a standardized way of handling
 * responses in a chain of responsibility.</p>
 *
 * @see RandomResponse
 * @see ResponseHandler
 * @version 1.0
 */
public class VerbResponseHandler extends RandomResponse implements ResponseHandler {

    private ResponseHandler nextHandler;
    protected static final List<Verb> VERBS = VerbManager
                            .getInstance("./src/main/resources/french-verb-conjugation.csv")
                            .getVerbs();

    /**
     * Handles the user's message, responding to statements starting
     * with "Je" (I) by converting them
     * to second-person questions related to verb conjugations.
     *
     * @param userMessage The user's message.
     * @return The response related to verb conjugations.
     */
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

    /**
     * Converts first-person statements to second-person questions related to verb conjugations.
     *
     * @param text The text containing first-person statements.
     * @return The processed text with converted pronouns and verb conjugations.
     */
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

    /**
     * Sets the next handler in the chain of responsibility.
     *
     * @param handler The next ResponseHandler in the chain.
     */
    @Override
    public void setNextHandler(final ResponseHandler handler) {
        this.nextHandler = handler;
    }
}
