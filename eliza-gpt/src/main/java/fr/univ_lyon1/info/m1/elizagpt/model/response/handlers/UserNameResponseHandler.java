package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.UserName;
import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The UserNameResponseHandler class handles user
 * name-related responses in the ElizaGPT application.
 * It specifically responds to queries about the user's name.
 *
 * <p>This class implements the {@link ResponseHandler} interface to provide a standardized way
 * of handling responses in a chain of responsibility.</p>
 *
 * @see ResponseHandler
 * @version 1.0
 */
public class UserNameResponseHandler implements ResponseHandler {

    private final UserName userName;
    private ResponseHandler nextHandler;

    /**
     * Constructs a new UserNameResponseHandler with the specified UserName instance.
     *
     * @param userName The UserName instance associated with the user's name.
     */
    public UserNameResponseHandler(final UserName userName) {
        this.userName = userName;
    }

    /**
     * Handles the user's message, responding to queries about the user's name.
     *
     * @param userMessage The user's message.
     * @return The response related to the user's name.
     */
    @Override
    public String handleResponse(final String userMessage) {
        Pattern pattern = Pattern.compile("Quel est mon nom \\?", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(userMessage);
        if (matcher.matches()) {
            String userName = this.userName.getUserName();
            if (userName != null) {
                return "Votre nom est " + userName + ".";
            } else {
                return "Je ne connais pas votre nom.";
            }
        }
        return nextHandler.handleResponse(userMessage);
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
