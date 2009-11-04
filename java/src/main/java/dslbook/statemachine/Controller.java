package dslbook.statemachine;

/**
 * @author Michael Hunger
 * @since 02.11.2009
 */
public class Controller implements CommandSink {
    private State currentState;
    private final StateMachine machine;
    private CommandChannel commandChannel;

    public Controller(StateMachine machine) {
        this.machine = machine;
    }

    public CommandChannel getCommandChannel() {
        return commandChannel;
    }


    public void handle(final String eventCode) {
        if (currentState.hasTransition(eventCode))
            transitionTo(currentState.targetState(eventCode));
        else if (machine.isResetEvent(eventCode))
            transitionTo(machine.getStart()); // ignore unknown events
    }

    private void transitionTo(final State target) {
        currentState = target;
        currentState.executeActions(commandChannel);
    }

    public void setCommandChannel(CommandChannel commandChannel) {
        this.commandChannel = commandChannel;
    }
}
