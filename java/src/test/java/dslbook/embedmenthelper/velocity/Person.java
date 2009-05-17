package dslbook.embedmenthelper.velocity;

/**
 * @author Michael Hunger
 * @since 17.05.2009
 */
class Person {
    private final String fullName;
    private String url;
    private String email;

    Person(final String fullName) {
        this.fullName = fullName;
    }

    public Person url(final String url) {
        this.url = url;
        return this;
    }

    public Person email(final String email) {
        this.email = email;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUrl() {
        return url;
    }

    public String getEmail() {
        return email;
    }

    public static Person name(final String name) {
        return new Person(name);
    }
}
