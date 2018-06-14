package cn.neil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import cn.neil.model.Director;
import cn.neil.repository.MovieRepository;
import cn.neil.model.Movie;

import java.util.List;

/**
 * Created by Nasir on 12-09-2015.
 */
@Service
public class MovieService {

    @Autowired
    private Environment env;

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getByName(String name) {
        String test = env.getProperty("test");
        System.out.println(test);
        return movieRepository.findByName(name);
    }

    public List<Movie> getByRatingInterval(Double start, Double end) {
        return movieRepository.findByRatingBetween(start, end);
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        Movie movie = new Movie();
        movie.setId(id);
        movieRepository.delete(movie);
    }

    public List<Movie> findByDirector(Director director) {
        return movieRepository.findByDirector(director);
    }

}
