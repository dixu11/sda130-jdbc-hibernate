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
            Course course2 = new Course("d", new CourseDetails("c details"));
//            Course course = new Course("Java od podstaw");
          //  Course course = session.get(Course.class, 2L);
        //    System.out.println("course");
         //   System.out.println(course.getDetails());
//            CourseDetails courseDetails = new CourseDetails("Pełny opis kursu Java od Podstaw....");
//            course.setCourseDetails(courseDetails);
//            session.persist(course2);
         //   session.delete(course);
            session.save(course2);
            System.out.println("del");
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
