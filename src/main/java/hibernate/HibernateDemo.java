package hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateDemo {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Book.class);
        configuration.configure("hibernate.cfg.xml");
        //SessionFactory
       SessionFactory sessionFactory = configuration.buildSessionFactory(); //SessionFactory to odpowiednik Connection z JDBC
        //SessionFactory - ciężki obiekt, potrzebny tylko jeden na całą aplikację

        //ORM - Object-Relation Mapper
        Book book = new Book("DDD Kompedium wiedzy","Vaughn Vernon", 130);

        Session session = sessionFactory.openSession();
        session.save(book);

        //Aby klasa była encją bazodanową, rozumianą przez hibernate musi:
        //być oznaczona adnotacją @Entity
        //mysi być wskazana przy konfiguracji (addAnnotatedClass)
        //musi być wskazane id adnotacją @Id
        //oraz....




    }
}
