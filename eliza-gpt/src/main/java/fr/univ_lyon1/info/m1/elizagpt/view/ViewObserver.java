package fr.univ_lyon1.info.m1.elizagpt.view;

import fr.univ_lyon1.info.m1.elizagpt.controller.Controller;

public interface ViewObserver {
     void onUserAddUpdate(Controller.Payload payload);
     void onElizaAddUpdate(Controller.Payload payload);
     void onDeleteUpdate(Controller.Payload payload);
}
