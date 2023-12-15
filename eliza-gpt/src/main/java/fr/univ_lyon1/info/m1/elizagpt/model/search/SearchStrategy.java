package fr.univ_lyon1.info.m1.elizagpt.model.search;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;

import java.util.ArrayList;
import java.util.List;

public interface SearchStrategy {

    List<Message> search(final List<Message> messages, final String text);
}