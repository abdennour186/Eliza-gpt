package fr.univ_lyon1.info.m1.elizagpt.model;

/**
 * An interface representing the user's name in the ElizaGPT application.
 *
 * <p>Implementations of this interface should provide methods for setting and retrieving the user's name.</p>
 *
 * @version 1.0
 */
public interface UserName {
    /**
     * Gets the user's name.
     *
     * @return The user's name.
     */
    String getUserName();

    /**
     * Sets the user's name.
     *
     * @param userName The user's name to set.
     */
    void setUserName(String userName);
}
