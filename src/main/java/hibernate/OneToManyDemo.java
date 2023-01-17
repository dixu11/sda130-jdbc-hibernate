package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class OneToManyDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(BookBlurb.class)
                .addAnnotatedClass(Library.class)
                .addAnnotatedClass(BookReader.class)
                .buildSessionFactory();


        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Library library = new Library("Biblioteka Pani Jadzi");
        Book book = new Book("7 Nawyków Skutecznego Działania", "Covey",400);
        session.save(book);
        library.addBook(book);
        library.addBook(session.get(Book.class,1));
        session.save(library);
        transaction.commit();
        session.close();
        factory.close();
    }
}
