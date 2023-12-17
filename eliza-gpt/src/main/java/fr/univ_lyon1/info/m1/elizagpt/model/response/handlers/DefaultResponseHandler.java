package fr.univ_lyon1.info.m1.elizagpt.model.response.handlers;

import fr.univ_lyon1.info.m1.elizagpt.model.UserName;
import fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler;



/**
 * A specific implementation of
 * {@link fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler}
 * that serves as the default response handler.
 *
 * <p>This handler generates a default response when no specific pattern or condition matches
 * the user's input. It can be the last handler in the chain of responsibility.</p>
 *
 * @see fr.univ_lyon1.info.m1.elizagpt.model.response.ResponseHandler
 * @version 1.0
 */
public class DefaultResponseHandler implements ResponseHandler {

    private final UserName userName;

    private ResponseHandler nextHandler;

    /**
     * Constructs a new DefaultResponseHandler instance with the specified user name.
     *
     * @param userName The user's name.
     */
    public DefaultResponseHandler(final UserName userName) {
        this.userName = userName;
    }

    /**
     * Generates a default response based on the user's input when no specific pattern matches.
     *
     * @param userMessage The user's input message.
     * @return The generated default response.
     */
    @Override
    public String handleResponse(final String userMessage) {
        String userName = this.userName.getUserName();
        if (userName != null) {
            return "Qu'est-ce qui vous fait dire cela, " + userName + " ?";
        } else {
            return "Qu'est-ce qui vous fait dire cela ?";
        }
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
