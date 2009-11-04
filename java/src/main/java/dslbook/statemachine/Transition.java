package dslbook.statemachine;

import static java.lang.String.format;

/**
 * @author Michael Hunger
 * @since 02.11.2009
 */
public class Transition {
    private final State source;
    private final Event trigger;
    private final State target;

    public Transition(State source, Event trigger, State target) {
        this.source = source;
        this.trigger = trigger;
        this.target = target;
    }

    public State getSource() {
        return source;
    }

    public Event getTrigger() {
        return trigger;
    }

    public State getTarget() {
        return target;
    }
    public String getEventCode() {
        return trigger.getCode();
    }
    @Override
    public String toString() {
        return format("%s --%s--> %s",source,trigger,target);
    }

}

