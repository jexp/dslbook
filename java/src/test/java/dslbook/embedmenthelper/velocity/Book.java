package dslbook.embedmenthelper.velocity;

import java.util.Collection;

/**
 * @author Michael Hunger
* @since 17.05.2009
*/
class Book {
    Collection<Person> people;

    Book(Person...people) {
        this.people = java.util.Arrays.asList(people);
    }

    public Collection<Person> getPeople() {
        return people;
    }
}
