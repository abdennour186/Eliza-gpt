package fr.univ_lyon1.info.m1;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class CharManipulatorTest {
    private CharManipulator manipulator;

    @BeforeEach
    public void setUp() {
        manipulator = new CharManipulator();
    }

    @Test
    public void orderNormalString() {
        assertEquals("A", manipulator.invertOrder("A"));
        assertEquals("DCBA", manipulator.invertOrder("ABCD"));
        assertEquals("321DCBA", manipulator.invertOrder("ABCD123"));
        assertThat(manipulator.invertOrder("ABCD"), is("DCBA"));
    }

    @Test
    public void orderEmptyString()
    {
        assertEquals("", manipulator.invertOrder(""));
    }

    @Test
    void invertCaseTest() {
        assertEquals("A", manipulator.invertCase("a"));
        assertEquals("aBcD", manipulator.invertCase("AbCd"));
        assertEquals("sLiMANe", manipulator.invertCase("SlImanE"));
    }

    @Test
    public void invertCaseEmptyList()
    {
        assertEquals("", manipulator.invertCase(""));
    }
}