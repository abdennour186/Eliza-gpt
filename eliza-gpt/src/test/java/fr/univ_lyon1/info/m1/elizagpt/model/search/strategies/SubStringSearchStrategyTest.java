package fr.univ_lyon1.info.m1.elizagpt.model.search.strategies;

import fr.univ_lyon1.info.m1.elizagpt.model.message.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SubStringSearchStrategyTest {

    SubStringSearchStrategy subStringSearchStrategy;
    ArrayList<Message> messages;

    @BeforeEach
    public void setUp() {
        subStringSearchStrategy = subStringSearchStrategy.getInstance();
        messages = new ArrayList<>();
        messages.add(new Message("Hello, how are you?", Message.Sender.USER));
        messages.add(new Message("I'm doing well, thank you!", Message.Sender.ELIZA));
        messages.add(new Message("Can you tell me a joke?", Message.Sender.USER));
    }

    @Test
    void search() {
        // Test for a matching pattern
        String subtext = "we";
        ArrayList<Message> result = subStringSearchStrategy.search(messages, subtext);
        assertEquals(1, result.size());
        assertEquals("I'm doing well, thank you!", result.get(0).getText());

        subtext = "ho.*";
        result = subStringSearchStrategy.search(messages, subtext);
        assertEquals(0, result.size());
    }

    @Test
    void getInstance() {
        RegexSearchStrategy instance1 = RegexSearchStrategy.getInstance();
        RegexSearchStrategy instance2 = RegexSearchStrategy.getInstance();
        assertSame(instance1, instance2);
    }
}