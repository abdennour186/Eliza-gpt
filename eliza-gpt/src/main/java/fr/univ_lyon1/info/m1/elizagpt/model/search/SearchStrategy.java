package fr.univ_lyon1.info.m1.elizagpt.model.search;

import fr.univ_lyon1.info.m1.elizagpt.model.Message;

import java.util.ArrayList;

public interface SearchStrategy {

    ArrayList<Message> search(final ArrayList<Message> messages,final String text);
}