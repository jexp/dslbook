package dslbook.statemachine;

import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Collection;

/**
 * @author Michael Hunger
 * @since 02.11.2009
 */
public class CommandChannel {
    Collection<CommandSink> sinks=new ArrayList<CommandSink>();

    public CommandChannel(CommandSink...sinks) {
        this.sinks.addAll(asList(sinks));
    }

    void send(String text) {
        for (CommandSink sink : sinks) {
            sink.handle(text);
        }
    }
}
