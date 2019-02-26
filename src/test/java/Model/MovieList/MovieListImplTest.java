package Model.MovieList;

import Model.Movie.*;
import Model.User.UserImpl;
import Model.User.UserModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * An implementation of Model.MovieList.MovieListImplTest
 * in samplemodelproject
 *
 * @author CHW
 * @version 1.0
 * @since 2019-Feb-14
 */

public class MovieListImplTest {

    private static final String TEST_USER_NAME = "CHW@weizenbaum.de";
    private static final String TEST_USER_PASSWORD = "test_password";
    private static final String TEST_MOVIE_LIST_NAME = "Testname";
    private static final Long MOVIE_LIST_ID = 123456L;

    private MovieImpl testMovie;
    private MovieImpl testMovieTwo;
    private MovieListImpl movieListImpl;
    //list of userIDs
    private List<Long> testUsers;
    UserImpl testUser;

    @Before
    public void setUp() throws Exception {
        testMovie = new MovieImpl();
        testMovieTwo = new MovieImpl();
        UserImpl testUser = new UserImpl(TEST_USER_NAME, TEST_USER_PASSWORD);
        UserModel.getInstance().register(testUser);

        movieListImpl = new MovieListImpl(TEST_MOVIE_LIST_NAME, TEST_USER_NAME);
        this.movieListImpl.setMovieListID(MOVIE_LIST_ID);

        this.movieListImpl.addNewUser(TEST_USER_NAME);
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
        MovieListImpl tmpMovieList = this.movieListImpl;
        assertEquals(this.movieListImpl.getId(), tmpMovieList.getId());
    }

    @Test
    public void testGetName() {
        assertTrue(this.movieListImpl.getName().equals(TEST_MOVIE_LIST_NAME));
    }

    @Test
    public void testGetMovies() {
        Set<MovieImpl> list = this.movieListImpl.getMovies();
        assertTrue(this.movieListImpl.getMovies().equals(list));
    }

    @Test
    public void testSetCreatorUserName(){
        assertEquals(TEST_USER_NAME, this.movieListImpl.getCreatorUserName());
    }

    @Test
    public void testGetUsers() {
        Set<UserImpl> list = this.movieListImpl.getUsers();
        assertTrue(this.movieListImpl.getUsers().equals(list));
    }

    @Test
    public void testAddNewUser() {
        this.movieListImpl.setName("test setName");
        assertTrue(this.movieListImpl.getName().equals("test setName"));
    }

    @Test
    public void testSetNewUser() {
        //Long userID = 111111L;
        UserImpl user = new UserImpl("username", "123456");
        this.movieListImpl.getUsers().add(user);
        assertTrue(this.movieListImpl.getUsers().contains(user));
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

    @After
    public void tearDown(){
        //UserModel.getInstance().remove(testUser);
    }

}