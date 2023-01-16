package movies.repository;

import movies.model.Movie;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class HibernateMoviesRepository {

    private SessionFactory sessionFactory;

    public HibernateMoviesRepository() {
        initConnection();
    }

    private void initConnection(){
        sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Movie.class)
                .buildSessionFactory();
    }

    public void save(Movie movie){
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) { //try-with-resources - umieszczamy tu zasoby "zamykalne" - implementujące "Closeable"
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            if(transaction!=null){
                transaction.rollback();
            }
        }
    }

    public void update(int id, int correctYear) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Movie movie = currentSession.get(Movie.class, id);
        movie.setYear(correctYear);
        transaction.commit();
        currentSession.close();
    }

    public List<Movie> findAllMovies()  {
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            List<Movie> movies = session.createQuery("FROM Movie", Movie.class).getResultList();
            transaction.commit();
            return movies;
        }catch (HibernateException e){
            e.printStackTrace();
            if(transaction!=null){
                transaction.rollback();
            }
            return Collections.emptyList();
        }
    }

    public void closeAllResources()  {
        sessionFactory.close();
    }








   /* private <T> T execute(Function<Session,T> operation){
        Transaction transaction = null;
        try(Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            T result = operation.apply(session);
            transaction.commit();
            return result;
        }catch (HibernateException e){
            e.printStackTrace();
            if(transaction!=null){
                transaction.rollback();
            }
            return null;
        }
    }

    public List<Movie> findAllMovies2() {
        List<Movie> movies = execute(session -> session.createQuery("FROM Movie", Movie.class).getResultList());
        return movies != null ? movies : Collections.emptyList();
    }

    public void addMovie2(Movie movie) {
        execute(session -> session.save(movie));
    }

    public void update2(int id, int correctYear){
        execute(session -> {
            Movie movie = session.get(Movie.class, id);
            movie.setYear(correctYear);
            return null;
        });
    }*/
}
