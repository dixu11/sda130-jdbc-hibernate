package hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libraries")
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL
    , fetch = FetchType.LAZY)//to property jest generowane na bazie property book o nazwie 'library'
    private List<Book> books = new ArrayList<>();

    public Library(String name) {
        this.name = name;
    }

    Library() {
    }

    public void addBook(Book book) {
        books.add(book);
        book.setLibrary(this);
    }

    public List<Book> getBooks() {
        return books;
    }
}
