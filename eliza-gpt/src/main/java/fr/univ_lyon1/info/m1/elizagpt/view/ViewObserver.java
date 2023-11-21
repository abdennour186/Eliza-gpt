package fr.univ_lyon1.info.m1.elizagpt.view;

import fr.univ_lyon1.info.m1.elizagpt.controller.Controller;
import fr.univ_lyon1.info.m1.elizagpt.model.Payload;

public interface ViewObserver {
     void onUserAddUpdate(Payload payload);
     void onElizaAddUpdate(Payload payload);
     void onDeleteUpdate(Payload payload);

     void onSearchUpdate(Payload payload);
     void onUndoSearchUpdate(Payload payload);
}
