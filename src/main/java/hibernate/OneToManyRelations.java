package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class OneToManyRelations {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(CourseDetails.class)
                .addAnnotatedClass(Instructor.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Instructor instructor = new Instructor();
      instructor.addCourse(new Course("Jakiś kurs", new CourseDetails("jego opis")));
        session.save(instructor);
        transaction.commit();
        session.close();


    }
}
