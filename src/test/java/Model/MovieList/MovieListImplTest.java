package Model.MovieList;

import Model.Movie.Movie;
import Model.Movie.MovieImpl;
import Model.User.Exception.UserNotFoundException;
import Model.User.User;
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
    private static final String SEC_TEST_USER_NAME = "testuser@test.de";
    private static final String SEC_TEST_USER_PASSWORD = "123456";
    private static final String TEST_MOVIE_LIST_NAME = "Testname";
    private static final Long MOVIE_LIST_ID = 123456L;

    private MovieImpl testMovie;
    private MovieImpl testMovieTwo;
    private MovieListImpl movieListImpl;


    @Before
    public void setUp() {
        testMovie = new MovieImpl();
        testMovieTwo = new MovieImpl();
        UserImpl testUser = new UserImpl(TEST_USER_NAME, TEST_USER_PASSWORD);
        UserImpl testUserTwo = new UserImpl(SEC_TEST_USER_NAME, SEC_TEST_USER_PASSWORD);
        UserModel.getInstance().register(testUser);
        UserModel.getInstance().register(testUserTwo);

        try {
            movieListImpl = new MovieListImpl(TEST_MOVIE_LIST_NAME, TEST_USER_NAME);
            this.movieListImpl.setMovieListID(MOVIE_LIST_ID);

            this.movieListImpl.addUserByName(TEST_USER_NAME);
        }
        catch(UserNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testAddMovie() {
        this.movieListImpl.addMovie(testMovie);
        assertTrue(this.movieListImpl.getMovies().contains(testMovie));
    }

    @Test
    public void testAddMovie_Size() {
        this.movieListImpl.addMovie(testMovie);
        assertTrue(this.movieListImpl.getMovies().size() == 1);
    }

    @Test
    public void testAddMovies() {
        fail();
    }

    @Test
    public void testAddMovies_AlreadyContainsMovie() {
        fail();
    }

    @Test
    public void testDeleteMovieFalse() {
        assertFalse(movieListImpl.deleteMovie(testMovie));
    }

    @Test
    public void testDeleteMovieTrue() {
        this.movieListImpl.addMovie(testMovie);
        assertTrue(movieListImpl.deleteMovie(testMovie));
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
    public void testGetCreatorUserName() {
        assertEquals(TEST_USER_NAME, this.movieListImpl.getCreatorUserName());
    }

    @Test
    public void testGetUsers() {
        Set<UserImpl> list = this.movieListImpl.getUsers();
        assertTrue(this.movieListImpl.getUsers().equals(list));
    }

    @Test
    public void testSetName() {
        this.movieListImpl.setName("test setName");
        assertTrue(this.movieListImpl.getName().equals("test setName"));
    }

    @Test
    public void testAddUserByName() throws UserNotFoundException {
        this.movieListImpl.addUserByName(SEC_TEST_USER_NAME);
        assertTrue(this.movieListImpl.getUsers().contains(UserModel.getInstance().findById(SEC_TEST_USER_NAME)));
    }

    @Test (expected = UserNotFoundException.class)
    public void testAddUserByNameEXCEPTION() throws UserNotFoundException{
        this.movieListImpl.addUserByName("not a username");
    }

    @Test
    public void testAddUser() throws UserNotFoundException {
        UserImpl user = UserModel.getInstance().findById(SEC_TEST_USER_NAME);
        this.movieListImpl.addUser(user);
        assertTrue(this.movieListImpl.getUsers().contains(user));
    }

    @Test
    public void testAddUserEXCEPTION() {
        UserImpl user = new UserImpl(TEST_USER_NAME, "123456");
        this.movieListImpl.getUsers().add(user);
        assertTrue(this.movieListImpl.getUsers().contains(user));
    }

    @Test
    public void testSetMovieListID() {
        //ID is already set in setUp()
        assertTrue(this.movieListImpl.getId() == MOVIE_LIST_ID);

    }

    @Test
    public void testSetMovieListID_AlreadySet() {
        this.movieListImpl.setMovieListID(123456L);
        this.movieListImpl.setMovieListID(334455L);
        assertTrue(this.movieListImpl.getId().equals(123456L));
    }

    @Test
    public void testContainsTrue() {
        this.movieListImpl.addMovie(testMovie);
        assertTrue(movieListImpl.contains(testMovie));
    }

    @Test
    public void testContainsFalse() {
        assertFalse(movieListImpl.contains(testMovie));
    }

    @Test
    public void testEquals() {
        fail();
    }

    @Test
    public void testHashcode() {
        fail();
    }

    @After
    public void tearDown() {
        //UserModel.getInstance().remove(testUser);
    }

}