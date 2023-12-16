package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A specific implementation of
 * {@link fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler}
 * that handles queries about the most something.
 *
 * <p>This handler checks if the user's message contains a
 * specific pattern related to asking about
 * the "most" in a certain context and provides a predefined response. If the pattern does not match,
 * it delegates the request to the next handler in the chain of responsibility.</p>
 *
 * @see fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler
 * @version 1.0
 */
public class TeacherResponseHandler implements ResponseHandler {

    private ResponseHandler nextHandler;

    /**
     * Generates a response based on the user's input, specifically handling queries about the most something.
     *
     * @param userMessage The user's input message.
     * @return The generated response.
     */
    @Override
    public String handleResponse(final String userMessage) {
        Pattern pattern = Pattern.compile("Qui est le plus (.*) \\?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(userMessage);
        if (matcher.matches()) {
            return "Le plus " + matcher.group(1) + " est bien sûr votre enseignant de MIF01!";
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
