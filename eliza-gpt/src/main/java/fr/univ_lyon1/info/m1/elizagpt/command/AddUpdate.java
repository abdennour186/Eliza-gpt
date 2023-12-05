package fr.univ_lyon1.info.m1.elizagpt.command;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;

/**
 * Represents a specific update type for adding a new message in the ElizaGPT application.
 * Instances of this class encapsulate information related to an add operation, including
 * the new message to be added.
 *
 * <p>This class extends the {@link Update} abstract class and follows the Command Pattern
 * to provide a standardized way of representing add operations that can be applied to
 * the application state.</p>
 *
 * <p>Instances of this class are typically created and used by the application's components,
 * such as the {@link fr.univ_lyon1.info.m1.elizagpt.controller.Controller}, to communicate and
 * execute add-related updates.</p>
 *
 * @see fr.univ_lyon1.info.m1.elizagpt.command.Update
 * @see fr.univ_lyon1.info.m1.elizagpt.controller.Controller
 */
public class AddUpdate extends Update {
    private final Message newMessage;

    /**
     * Constructs a new AddUpdate instance with the specified new message to be added.
     *
     * @param newMessage The new message to be added.
     */
    public AddUpdate(final Message newMessage) {
        this.newMessage = newMessage;
    }

    /**
     * Gets the new message to be added.
     *
     * @return The new message.
     */
    public Message getNewMessage() {
        return newMessage;
    }
}
