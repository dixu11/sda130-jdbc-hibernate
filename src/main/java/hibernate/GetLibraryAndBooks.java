package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class GetLibraryAndBooks {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(BookBlurb.class)
                .addAnnotatedClass(Library.class)
                .buildSessionFactory();



        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Library library = session.get(Library.class, 2L);
        System.out.println(library.getBooks());
        transaction.commit();
        session.close();
        factory.close();


    }
}
