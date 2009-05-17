package dslbook.embedmenthelper.velocity;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Michael Hunger
 * @since 17.05.2009
 */
public class BookPM {
    private final Book book;

    public BookPM(final Book book) {
        this.book = book;
    }

    public Collection<PersonPM> getPeople() {
        List<PersonPM> result=new ArrayList<PersonPM>();
        for (Person person : book.getPeople()) {
            result.add(PersonPM.present(person));
        }
        return  result;
    }

    public static BookPM present(Book book) {
        return new BookPM(book);
    }
}
