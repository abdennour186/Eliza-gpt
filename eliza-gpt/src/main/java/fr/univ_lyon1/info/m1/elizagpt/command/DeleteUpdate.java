package fr.univ_lyon1.info.m1.elizagpt.command;

/**
 * Represents a specific update type for deleting a message in the ElizaGPT application.
 * Instances of this class encapsulate information related to a delete operation, including
 * the identifier of the message to be deleted.
 *
 * <p>This class extends the {@link Update} abstract class and follows the Command Pattern
 * to provide a standardized way of representing delete operations that can be applied to
 * the application state.</p>
 *
 * <p>Instances of this class are typically created and used by the application's components,
 * such as the {@link fr.univ_lyon1.info.m1.elizagpt.controller.Controller}, to communicate and
 * execute delete-related updates.</p>
 *
 * @see fr.univ_lyon1.info.m1.elizagpt.command.Update
 * @see fr.univ_lyon1.info.m1.elizagpt.controller.Controller
 */
public class DeleteUpdate extends Update {
    private final int deletedMessageId;

    /**
     * Constructs a new DeleteUpdate instance with the
     * specified identifier of the message to be deleted.
     *
     * @param deletedMessageId The identifier of the message to be deleted.
     */
    public DeleteUpdate(final int deletedMessageId) {
        this.deletedMessageId = deletedMessageId;
    }

    /**
     * Gets the identifier of the message to be deleted.
     *
     * @return The deleted message's identifier.
     */
    public int getDeletedMessageId() {
        return deletedMessageId;
    }
}
