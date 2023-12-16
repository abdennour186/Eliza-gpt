package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.UserName;
import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A specific implementation of
 * {@link fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler}
 * that handles user messages related to their name.
 *
 * <p>This handler attempts to recognize when the user introduces themselves by stating their name
 * and responds accordingly. If the user's input does not match the specified pattern, it delegates
 * the request to the next handler in the chain of responsibility.</p>
 *
 * @see fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler
 * @version 1.0
 */
public class NameResponseHandler implements ResponseHandler {

    private ResponseHandler nextHandler;
    private final UserName userName;

    /**
     * Constructs a new NameResponseHandler instance with the specified user name.
     *
     * @param userName The user's name.
     */
    public NameResponseHandler(final UserName userName) {
        this.userName = userName;
    }

    /**
     * Generates a response based on the user's input,
     * specifically handling messages related to their name.
     * @param userMessage The user's input message.
     * @return The generated response.
     */
    @Override
    public String handleResponse(final String userMessage) {
        Pattern pattern;
        Matcher matcher;

        // First, try to answer specifically to what the user said
        pattern = Pattern.compile(".*Je m'appelle (.*)\\.", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(userMessage);
        if (matcher.matches()) {
            String userName = matcher.group(1);
            this.userName.setUserName(userName);
            return "Bonjour " + matcher.group(1) + ".";
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
