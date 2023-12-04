package fr.univ_lyon1.info.m1.elizagpt.view;
import fr.univ_lyon1.info.m1.elizagpt.model.Payload;

/**
 * The {@code ViewObserver} interface defines methods to be implemented by classes
 * observing changes in the view of the Eliza GPT application. Observers can react to
 * updates related to message addition, deletion, search, and undo search operations.
 * <p>
 * Classes implementing this interface should handle updates received through the
 * specified methods to maintain synchronization with the application's model.
 */
public interface ViewObserver {

     /**
      * Notifies the observer when a new message is added to the application.
      *
      * @param payload The payload containing information about the new message.
      */
     void onMessageAddUpdate(Payload payload);

     /**
      * Notifies the observer when a message is deleted from the application.
      *
      * @param payload The payload containing information about the deleted message.
      */
     void onDeleteUpdate(Payload payload);

     /**
      * Notifies the observer when a search operation is performed in the application.
      *
      * @param payload The payload containing search-related information.
      */
     void onSearchUpdate(Payload payload);

     /**
      * Notifies the observer when the undo search operation is performed in the application.
      *
      * @param payload The payload containing information about the search result.
      */
     void onUndoSearchUpdate(Payload payload);
}

