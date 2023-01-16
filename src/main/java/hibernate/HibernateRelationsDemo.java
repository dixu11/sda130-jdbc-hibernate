package hibernate;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateRelationsDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(CourseDetails.class)
                .buildSessionFactory();


        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
//            Course course = new Course("Java od podstaw");
            Course course = session.get(Course.class, 1L);
            CourseDetails courseDetails = new CourseDetails("Pełny opis kursu Java od Podstaw....");
            course.setCourseDetails(courseDetails);

            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

        sessionFactory.close();
    }
}
