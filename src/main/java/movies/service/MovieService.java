package movies.service;

import movies.exception.MovieServiceException;
import movies.model.Movie;
import movies.repository.HibernateMoviesRepository;
import movies.repository.JDBCMoviesRepository;
import movies.repository.MovieRepository;

import java.sql.SQLException;
import java.util.List;

//menu
public class MovieService {
    private MovieRepository repository;

    public MovieService(MovieRepository repository) { //dependency injection
        this.repository = repository;
    }

    public void save(Movie movie)throws MovieServiceException {
        if (movie.getPremiereYear() < 1800 || movie.getPremiereYear() > 2100) {
            throw new MovieServiceException("Podano nierealną datę premiery. " +
                    "Powinien być przedział: 1800 - 2100");
        }
        repository.save(movie);
    }

    public List<Movie> findAllMovies() throws MovieServiceException {
        return repository.findAllMovies();
    }

    public void closeAllResources() throws MovieServiceException {
        repository.closeAllResources();
    }


    //każda metoda powinna operować na jednym poziomie abstrakcji
}
