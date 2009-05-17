package dslbook.embedmenthelper.velocity;

/**
 * @author Michael Hunger
 * @since 17.05.2009
 */
public class PersonPM {
    private final Person person;

    public PersonPM(final Person person) {
        this.person = person;
    }

    public String getFullName() {
        return person.getFullName();
    }

    public boolean isLink() {
        return person.getUrl() != null || person.getEmail() != null;
    }

    public String getHref() {
        if (person.getUrl() != null) return person.getUrl();
        if (person.getEmail() != null) return "mailto:"+person.getEmail();
        throw new IllegalStateException("Person " + person + " has no link");
    }

    public static PersonPM present(Person person) {
        return new PersonPM(person);
    }
}
