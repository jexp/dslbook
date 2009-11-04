package dslbook.statemachine;

import java.util.*;

/**
 * @author Michael Hunger
 * @since 02.11.2009
 */
public class State {
    private final String name;
    private final List<Command> actions = new ArrayList<Command>();
    private final Map<String, Transition> transitions = new HashMap<String, Transition>();

    public State(final String name) {
        this.name = name;
    }

    public void addTransition(final Event trigger, final State target) {
        transitions.put(trigger.getCode(), new Transition(this, trigger, target));
    }

    public Collection<Transition> getTransitions() {
        return transitions.values();
    }

    public Collection<State> getTargets() {
        final Collection<State> result=new HashSet<State>();
        for (final Transition transition : transitions.values()) {
            result.add(transition.getTarget());
        }
        return result;
    }

    public Collection<State> gatherForwards() {
        final Collection<State> result = new HashSet<State>();
        for (final State target : getTargets()) {
            if (result.contains(target)) continue;
            result.add(target);
            result.addAll(target.gatherForwards());
        }
        result.add(this);
        return result;
    }

    @Override
    public boolean equals(Object state) {
        if (this == state) return true;
        if (state == null || getClass() != state.getClass()) return false;

        return name.equals(((State) state).name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean hasTransition(String eventCode) {
        return transitions.containsKey(eventCode);
    }

    public State targetState(String eventCode) {
        final Transition transition = transitions.get(eventCode);
        return transition == null ? null : transition.getTarget();
    }

    public void executeActions(CommandChannel commandsChannel) {
        for (Command action : actions) {
            commandsChannel.send(action.getCode());
        }
    }

    public void addAction(Command command) {
       actions.add(command); 
    }
}
