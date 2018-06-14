package cn.neil;

import cn.neil.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import cn.neil.model.Director;
import cn.neil.model.Movie;
import cn.neil.model.Genre;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Slf4j
public class EsApplication implements CommandLineRunner{

    @Autowired
    private MovieService movieService;

    private static Logger ywaLog = LoggerFactory.getLogger("ywA");

    private static Logger ywbLog = LoggerFactory.getLogger("ywB");

    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        log.debug("[Spring boot start] loading data...");

        //test 不同业务输出到不同地方
        ywaLog.info("测试业务A输出");
        ywbLog.info("测试业务b输出");

        addMovies();

        List<Movie> dabanggNamedQuery = movieService.getByName("dabangg");
        log.info("Content of dabangg name book {}", dabanggNamedQuery);

        List<Movie> readyMovieQuery = movieService.getByName("ready");
        log.info("Content of dabangg name book {}", dabanggNamedQuery);

        List<Movie> byRating = movieService.getByRatingInterval(7d, 9d);
        log.info("Content of book by rating 7 9 {}", byRating);
    }

    private void addMovies() {
        Movie movie1 = getFirstMovie();
        movieService.addMovie(movie1);

        Movie movie2 = getSecondMovie();
        movieService.addMovie(movie2);

        Movie mvoie3 = getThirdMovie();
        movieService.addMovie(mvoie3);

        Movie movie4 = getForthMovie();
        movieService.addMovie(movie4);
    }

    private Movie getFirstMovie() {
        Movie firstMovie = new Movie();
        firstMovie.setId(1L);
        firstMovie.setRating(8.4d);
        firstMovie.setName("Batman v Superman: Dawn of Justice");

        Director director = new Director("Zack Snyder");
        firstMovie.setDirector(director);

        List<Genre> genres = new ArrayList<Genre>();
        genres.add(new Genre("DRAMA"));
        genres.add(new Genre("ACTION"));


        firstMovie.setGenre(genres);

        return firstMovie;
    }

    private Movie getSecondMovie() {
        Movie secondMovie = new Movie();
        secondMovie.setId(2L);
        secondMovie.setRating(9.4d);
        secondMovie.setName("The Dark Knight Rises");

        Director director = new Director("Christopher Nolan");
        secondMovie.setDirector(director);

        List<Genre> genres = new ArrayList<Genre>();
        genres.add(new Genre("ROMANTIC"));
        genres.add(new Genre("ACTION"));

        secondMovie.setGenre(genres);

        return secondMovie;
    }

    private Movie getThirdMovie() {
        Movie thirdMovie = new Movie();
        thirdMovie.setId(3L);
        thirdMovie.setRating(8d);
        thirdMovie.setName("Batman Begins");

        Director director = new Director("Christopher Nolan");
        thirdMovie.setDirector(director);

        List<Genre> genres = new ArrayList<Genre>();
        genres.add(new Genre("ROMANTIC"));
        genres.add(new Genre("ACTION"));

        thirdMovie.setGenre(genres);

        return thirdMovie;
    }

    private Movie getForthMovie() {
        Movie forthMovie = new Movie();
        forthMovie.setId(4L);
        forthMovie.setRating(8d);
        forthMovie.setName("Batman & Robin");

        Director director = new Director("Joel Schumacher");
        forthMovie.setDirector(director);

        List<Genre> genres = new ArrayList<Genre>();
        genres.add(new Genre("ROMANTIC"));
        genres.add(new Genre("ACTION"));

        forthMovie.setGenre(genres);

        return forthMovie;
    }
}
