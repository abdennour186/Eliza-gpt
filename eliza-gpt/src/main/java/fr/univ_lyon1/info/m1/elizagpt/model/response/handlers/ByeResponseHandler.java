package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.UserName;
import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A specific implementation of
 * {@link fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler}
 * that handles farewell messages.
 * <p>This handler checks if the user's message contains a
 * specific pattern related to saying goodbye
 * and provides a personalized farewell message. If the pattern does not match, it delegates
 * the request to the next handler in the chain of responsibility.</p>
 *
 * @see fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler
 * @version 1.0
 */
public class ByeResponseHandler implements ResponseHandler {
    private ResponseHandler nextHandler;
    private final UserName userName;

    /**
     * Constructs a new ByeResponseHandler instance with the specified user name.
     *
     * @param userName The user's name.
     */
    public ByeResponseHandler(final UserName userName) {
        this.userName = userName;
    }

    /**
     * Generates a response based on the user's input, specifically handling farewell messages.
     *
     * @param userMessage The user's input message.
     * @return The generated response.
     */
    @Override
    public String handleResponse(final String userMessage) {
        Pattern pattern = Pattern.compile("Au revoir.", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(userMessage);
        if (matcher.matches()) {
            String userName = this.userName.getUserName();
            if (userName != null) {
                return "Oh non, c'est trop triste de se quitter "
                        + userName
                        + " !";
            } else {
                return "Oh non, c'est trop triste de se quitter !";
            }
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
