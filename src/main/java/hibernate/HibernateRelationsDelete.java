package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateRelationsDelete {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(BookBlurb.class)
                .buildSessionFactory();


        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.get(Book.class,1)); //to było potrzebne żeby pokasować te książki i zademonstrować kaskadowość
        session.delete(session.get(Book.class,2)); //mało wydajny sposób
        session.delete(session.get(Book.class,4));
        session.delete(session.get(Book.class,5));
        session.delete(session.get(Book.class,6));
       transaction.commit();
        session.close();
        factory.close();
    }
}
