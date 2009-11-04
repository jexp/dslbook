package dslbook.statemachine;

/**
 * @author Michael Hunger
 * @since 03.11.2009
 */
public interface CommandSink {
    void handle(String text);
}
