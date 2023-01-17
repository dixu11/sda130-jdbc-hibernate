package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateRelationsDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(BookBlurb.class)
                .addAnnotatedClass(Library.class)
                .buildSessionFactory();
        //Transcient
        Book book = new Book("Inaczej", "Radek Kotarski",400);
        BookBlurb blurb = new BookBlurb("Jak pracować mniej, ale lepiej i przyjemniej");

        Session session = factory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        book.setBookBlurb(blurb); // ta modyfikacja zostanie uwzględniona
        session.save(book); //persistent
      //  session.save(blurb); //persistent
        System.out.println("all operations done");
        transaction.commit(); //wygenerowanie zapytania
        System.out.println("commit done");
        session.close();
        factory.close();
        //obiekty są już detached
        book.setBookBlurb(null);
        book.setPages(1);
        System.out.println("some more changes");
    }

    //OneToOne
    //ManyToOne || OneToMany
    //ManyToMany

}
