package Model;

import Model.Movie.MovieImpl;
import Model.MovieList.MovieListImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * An implementation of MovieListImplTest
 * in samplemodelproject
 *
 * @author CHW
 * @version 1.0
 * @since 2019-Feb-14
 */

public class MovieListImplTest {

    private MovieImpl testMovie = new MovieImpl();
    private MovieImpl testMovieTwo = new MovieImpl();
    private MovieListImpl movieListImpl = new MovieListImpl("Testname", 333333L);
    //list of userIDs
    private List<Long> testUsers;

    @Before
    public void setUp() throws Exception {

        this.movieListImpl.setNewUser(456789L);
    }

    @Test
    public void testAddMovie() {
        this.movieListImpl.addMovie(testMovie);
        assertTrue(this.movieListImpl.getMovies().contains(testMovie));
    }

    @Test
    public void testAddMovieSize() {
        this.movieListImpl.addMovie(testMovie);
        assertTrue(this.movieListImpl.getMovies().size() == 1);
    }

    @Test
    public void testDeleteMovie() {
        //this.movieListImpl.getTmdbMovies().remove(testMovieTwo);
        assertTrue(this.movieListImpl.getMovies().size() == 0);
    }

    @Test
    public void testGetId() {
        this.movieListImpl.setMovieListID(123456L);
        assertTrue(this.movieListImpl.getId() == 123456L);
    }

    @Test
    public void testGetName() {
        assertTrue(this.movieListImpl.getName().equals("Testname"));
    }

    @Test
    public void testGetMovies() {
        List list = this.movieListImpl.getMovies();
        assertTrue(this.movieListImpl.getMovies().equals(list));
    }

    @Test
    public void testGetCreatorUserId() {
        assertTrue(this.movieListImpl.getCreatorUserId() == 333333L);
    }

    @Test
    public void testGetUsers() {
        List<Long> list = this.movieListImpl.getUsers();
        assertTrue(this.movieListImpl.getUsers().equals(list));
    }

    @Test
    public void testSetName() {
        this.movieListImpl.setName("test setName");
        assertTrue(this.movieListImpl.getName().equals("test setName"));
    }

    @Test
    public void testSetNewUser() {
        Long userID = 111111L;
        this.movieListImpl.getUsers().add(userID);
        assertTrue(this.movieListImpl.getUsers().contains(userID));
    }

    @Test
    public void testSetID() {
        Long testID = 334455L;
        this.movieListImpl.setMovieListID(testID);
        assertTrue(this.movieListImpl.getId() == 334455L);

    }

    @Test
    public void testSetIDalreadyset() {
        this.movieListImpl.setMovieListID(123456L);
        this.movieListImpl.setMovieListID(334455L);
        assertTrue(this.movieListImpl.getId().equals(123456L));
    }

}