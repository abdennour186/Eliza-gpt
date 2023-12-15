package fr.univ_lyon1.info.m1.elizagpt.model.payload;

/**
 * The abstract base class for update types in the ElizaGPT application.
 * Concrete subclasses should extend this class to represent specific update operations.
 * Updates encapsulate changes or actions that can be applied to the application state.
 *
 * <p>This class serves as a foundation for implementing the Command Pattern, where each
 * concrete subclass represents a specific command or update to be executed.</p>
 *
 * <p>Subclasses should provide specific behavior by implementing their own execution logic.</p>
 *
 * @see AddUpdate
 * @see DeleteUpdate
 * @see SearchUpdate
 * @version 1.0
 */
public abstract class Update {
    // Abstract class with no specific methods at this level
}
