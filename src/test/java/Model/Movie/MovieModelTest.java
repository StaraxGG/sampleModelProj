package Model.Movie;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieModelTest {

    MovieModel movieModel = null;
    MovieDb curMovieDb = null;

    @Before
    public void setUp() throws Exception {
        movieModel = MovieModel.getInstance();

        TmdbApi api = new TmdbApi("652086fc44227443a5017d1f532898da");
        TmdbMovies movies = new TmdbMovies(api);
        curMovieDb = movies.getMovie(5353, "en");
    }

    @Test
    public void parseTmdbMovie() {
        MovieImpl parsedMovie = movieModel.parseTmdbMovie(curMovieDb);
        //TODO: CHW complete this stuff
    }

    @Test
    public void getTmdbMovie() {
        fail();
        // we know the values for the TmdbMovie
    }

    @Test
    public void getTmdbMovies() {
        fail();
    }

    @Test
    public void getSimilarMovies() {
        fail();
    }

    /* ------------------ Database Tests ------------------ */

    @Test
    public void persist(){
        fail();
    }

    @Test
    public void remove(){
        fail();
    }

    @Test
    public void findById(){
        fail();
    }


    @After
    public void tearDown() throws Exception {
        movieModel = null;
    }
}