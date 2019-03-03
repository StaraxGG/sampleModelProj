package Model.Movie;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MovieModelTest {

    MovieModel movieModel = null;
    MovieDb curMovieDb = null;
    MovieImpl curMovie = null;

    @Before
    public void setUp() throws Exception {
        movieModel = MovieModel.getInstance();

        TmdbApi api = new TmdbApi("652086fc44227443a5017d1f532898da");
        TmdbMovies movies = new TmdbMovies(api);
        curMovieDb = movies.getMovie(5353, "en");
        curMovie = movieModel.parseTmdbMovie(curMovieDb);
    }

    @Test
    public void testParseTmdbMovie() {
        MovieImpl parsedMovieOne = movieModel.parseTmdbMovie(curMovieDb);
        MovieImpl parsedMovieTwo = movieModel.parseTmdbMovie(curMovieDb);
        assertTrue(parsedMovieOne.equals(parsedMovieTwo));
    }

    @Test
    public void testGetTmdbMovie() {
        MovieImpl testMovieOne = (MovieImpl) MovieModel.getInstance().getTmdbMovie(5353);
        MovieImpl testMovieTwo = curMovie;
        assertTrue(testMovieOne.getTmdbId().equals(testMovieTwo.getTmdbId()));
    }

    @Test
    public void testGetTmdbMovies() {
        List<Movie> testMovieListOne = movieModel.getTmdbMovies("Karate Tiger", 1);
        List<Movie> testMovieListTwo = movieModel.getTmdbMovies("Karate Tiger", 1);
        assertTrue(testMovieListOne.equals(testMovieListTwo));
    }

    @Test
    public void testGetSimilarMovies() {
        List<Movie> testMovieListOne = movieModel.getSimilarMovies(curMovie, 1);
        List<Movie> testMovieListTwo = movieModel.getSimilarMovies(curMovie, 1);
        assertTrue(testMovieListOne.equals(testMovieListTwo));
    }

    @Test
    public void testParseTmdbMovieList() {
        //getTmdbMovies uses private method parseTmdbMovieList
        List<Movie> testMovieListOne = movieModel.getTmdbMovies("Marvel", 1);
        List<Movie> testMovieListTwo = movieModel.getTmdbMovies("Marvel", 1);
        assertTrue(testMovieListOne.equals(testMovieListTwo));
    }

    @Test
    public void testGetPopularMovies() {
        List<Movie> testMovieListOne = movieModel.getPopularMovies(1);
        List<Movie> testMovieListTwo = movieModel.getPopularMovies(1);
        assertTrue(testMovieListOne.equals(testMovieListTwo));
    }

    @After
    public void tearDown() throws Exception {
        movieModel = null;
    }
}