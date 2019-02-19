package Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
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
    private MovieListImpl movieListImpl = new MovieListImpl();
    private LinkedList<MovieImpl> testMovieList = new LinkedList<>();
    private Long testMovieListID;
    private String testMovieListName;
    private Long testCreatorUserID;
    //list of userIDs
    private List<Long> testUsers;

    @Before
    public void setUp() throws Exception {
        this.testMovieList.add(testMovieTwo);
        this.movieListImpl.setMovieListID(123456L);
        this.movieListImpl.setName("Testname");
        this.movieListImpl.setNewUser(456789L);
    }

    @Test
    public void testAddMovie() {
        this.testMovieList.add(testMovie);
        assertTrue(this.testMovieList.contains(testMovie));
    }

    @Test
    public void testAddMovieSize() {
        this.testMovieList.add(testMovie);
        assertTrue(this.testMovieList.size()==2);
    }

    @Test
    public void deleteMovie() {
        this.testMovieList.remove(testMovieTwo);
        assertTrue(this.testMovieList.size()==0);
    }

    @Test
    public void getId() {
        assertTrue(false);

    }

    @Test
    public void getName() {
        assertTrue(false);

    }

    @Test
    public void getMovies() {
        assertTrue(false);

    }

    @Test
    public void getCreatorUserId() {
        assertTrue(false);

    }

    @Test
    public void getUsers() {
        assertTrue(false);

    }

    @Test
    public void setName() {
        assertTrue(false);

    }

    @Test
    public void setNewUser() {
        assertTrue(false);

    }

    @Test
    public void testSetID() {
        assertTrue(false);

    }

    @Test
    public void testSetIDalreadyset() {
        assertTrue(false);

    }
}