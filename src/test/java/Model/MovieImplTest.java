package Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieImplTest {

    private MovieImpl movie = new MovieImpl();

    @Before
    public void setUp() throws Exception {


        movie.setTmdbId(12345);
        movie.setTitle("Test Movie Title");
    }

    @Test
    public void testGetTmdbID() {
            assertTrue(this.movie.getTmdbId() == 12345);
    }

    @After
    public void tearDown() throws Exception {

    }
}