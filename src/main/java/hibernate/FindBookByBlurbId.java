package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class FindBookByBlurbId {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(BookBlurb.class)
                .buildSessionFactory();


        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        BookBlurb blurb = session.get(BookBlurb.class, 1L);
        System.out.println(blurb.getBook());
        transaction.commit();
        session.close();
        factory.close();

    }
}
