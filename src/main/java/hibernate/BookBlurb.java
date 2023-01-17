package hibernate;

import javax.persistence.*;

@Entity
@Table(name = "book_blurbs")
final public class BookBlurb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;

    public BookBlurb(String text) {
        this.text = text;
    }

    BookBlurb() {
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "BookBlurb{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
