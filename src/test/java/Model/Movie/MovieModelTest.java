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
    }

    @Test
    public void getTmdbMovie() {
        // we know the values for the TmdbMovie
    }

    @Test
    public void getTmdbMovies() {
    }

    @Test
    public void getSimilarMovies() {
    }

    @After
    public void tearDown() throws Exception {
        movieModel = null;
    }
}