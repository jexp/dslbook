package dslbook.statemachine;

import static java.util.Arrays.asList;
import java.util.Collection;
import java.util.HashSet;

/**
 * @author Michael Hunger
 * @since 02.11.2009
 */
public class StateMachine {
    private final State start;
    private final Collection<Event> resetEvents = new HashSet<Event>();

    public StateMachine(final State start) {
        this.start = start;
    }

    public Collection<State> getStates() {
        return start.gatherForwards();
    }
    public void addResetEvents(final Event... events) {
        resetEvents.addAll(asList(events));
    }

    public State getStart() {
        return start;
    }

    public boolean isResetEvent(final String eventCode) {
        return resetEvents.contains(new Event(eventCode,eventCode));
    }
    public boolean isResetEvent2(final String eventCode) {
        for (final Event event : resetEvents) {
            if (event.matchesCode(eventCode)) return true;
        }
        return false;
    }
}
