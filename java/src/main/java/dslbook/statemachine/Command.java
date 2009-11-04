package dslbook.statemachine;

/**
 * @author Michael Hunger
 * @since 02.11.2009
 */
public class Command extends AbstractEvent {
    public Command(String name,String code) {
        super(name, code);
    }
}
