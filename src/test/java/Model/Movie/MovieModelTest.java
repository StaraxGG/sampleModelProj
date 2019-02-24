package Model.Movie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieModelTest {

    MovieModel movieModel = null;

    @Before
    public void setUp() throws Exception {
        movieModel = MovieModel.getInstance();
    }

    @Test
    public void parseTmdbMovie() {
        fail();
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

    @After
    public void tearDown() throws Exception {
        movieModel = null;
    }
}