package hibernate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book_readers")
public class BookReader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

//    @ElementCollection
//    private List<String> books;

    @ManyToMany()
    @JoinTable(name = "readers_books" ,
    joinColumns = @JoinColumn(name = "reader_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;
}
