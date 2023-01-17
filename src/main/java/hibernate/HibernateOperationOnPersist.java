package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateOperationOnPersist {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(BookBlurb.class)
                .buildSessionFactory();


        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Book book = session.get(Book.class, 1);
     //   book.setPages(1000);
        System.out.println(book.getPages());
        System.out.println(book.getBookBlurb());
        book.getBookBlurb().setText("zzz");
        transaction.commit();
        session.close();
        factory.close();
    }
}
