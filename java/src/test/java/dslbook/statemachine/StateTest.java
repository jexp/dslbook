package dslbook.statemachine;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

/**
 * @author Michael Hunger
 * @since 02.11.2009
 */
public class StateTest {
    private State start;
    private State first;
    private State second;
    private State third;
    private State end;

    @Before
    public void setupStates() {
        start = new State("start");
        first = new State("first");
        second = new State("second");
        third = new State("third");
        end = new State("end");

        start.addTransition(evt("toSecond"), second);
        first.addTransition(evt("toSecond"), second);
        start.addTransition(evt("toFirst"), first);
        second.addTransition(evt("toThird"), third);
        third.addTransition(evt("end"), end);
        start.addTransition(evt("end"), end);
    }

    private Event evt(String name) {
        return new Event(name, name);
    }

    @Test
    public void testGetStates() {
        assertEquals(set(second,first,end),start.getTargets());
        assertEquals(set(start,second,first,third,end),start.gatherForwards());
    }
    private <T> Set<T> set(T...values) {
        return new HashSet<T>(asList(values));
    }
}
