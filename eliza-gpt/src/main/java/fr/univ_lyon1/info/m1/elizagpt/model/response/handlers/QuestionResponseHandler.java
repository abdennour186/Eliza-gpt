package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;


import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A specific implementation of
 * {@link fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler}
 * that handles user messages in the form of questions.
 * <p>This handler recognizes when the user's message ends with a question mark
 * and responds accordingly,
 * either by redirecting the question back to the user
 * or providing a random response from a predefined list.</p>
 *
 * @see fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler
 * @version 1.0
 */
public class QuestionResponseHandler extends RandomResponse implements ResponseHandler {

    private ResponseHandler nextHandler;

    /**
     * Generates a response based on the user's input,
     * specifically handling messages in the form of questions.
     *
     * @param userMessage The user's input message.
     * @return The generated response.
     */
    @Override
    public String handleResponse(final String userMessage) {
        Pattern pattern = Pattern.compile(".*\\?");
        Matcher matcher = pattern.matcher(userMessage);
        if (matcher.matches()) {
            return pickRandom(new String[]{
                    "Je vous renvoie la question.",
                    "Ici, c'est moi qui pose les questions.",
            });
        }
        return nextHandler.handleResponse(userMessage);
    }

    /**
     * Sets the next handler in the chain of responsibility.
     *
     * @param handler The next handler in the chain.
     */
    @Override
    public void setNextHandler(final ResponseHandler handler) {
        this.nextHandler = handler;
    }
}
