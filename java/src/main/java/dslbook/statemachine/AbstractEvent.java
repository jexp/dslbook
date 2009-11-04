package dslbook.statemachine;

import static java.lang.String.format;

/**
 * @author Michael Hunger
 * @since 02.11.2009
 */
public class AbstractEvent {
    private final String name;
    private final String code;

    public AbstractEvent(String name,String code) {
        this.name = name;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object event) {
        if (this == event) return true;
        if (!(event instanceof AbstractEvent)) return false;

        return code.equals(((AbstractEvent) event).code);

    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public String toString() {
        return format("%s (%s)",code,name);
    }

    public boolean matchesCode(String code) {
        return this.code.equals(code);
    }
}
