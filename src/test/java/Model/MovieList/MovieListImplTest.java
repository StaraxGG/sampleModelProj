package Model.MovieList;

import Model.MasterModel;
import Model.Movie.Movie;
import Model.Movie.MovieImpl;
import Model.User.Exception.UserNotFoundException;
import Model.User.User;
import Model.User.UserImpl;
import Model.User.UserModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
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
    private static final String SEC_TEST_MOVIE_LIST_NAME = "Different Testname";
    private static final Long MOVIE_LIST_ID = 123456L;

    private MovieImpl testMovie;
    private MovieImpl testMovieTwo;
    private MovieListImpl movieListImpl;
    private MovieListImpl otherMovieList;
    private MovieListImpl otherCreatorList;
    private UserImpl testUser;
    private UserImpl testUserTwo;
    private List<Movie> movies;
    private Object otherInst;


    @Before
    public void setUp() {
        testMovie = new MovieImpl();
        testMovieTwo = new MovieImpl();
        testUser = new UserImpl(TEST_USER_NAME, TEST_USER_PASSWORD);
        testUserTwo = new UserImpl(SEC_TEST_USER_NAME, SEC_TEST_USER_PASSWORD);
        movies = new LinkedList<>();
        movies.add(testMovieTwo);
        otherInst = new Object();
        UserModel.getInstance().register(testUser);
        UserModel.getInstance().register(testUserTwo);

        try {
            movieListImpl = new MovieListImpl(TEST_MOVIE_LIST_NAME, TEST_USER_NAME);
            otherMovieList = new MovieListImpl(TEST_MOVIE_LIST_NAME, TEST_USER_NAME);
            otherCreatorList = new MovieListImpl(TEST_MOVIE_LIST_NAME, SEC_TEST_USER_NAME);
            this.movieListImpl.setMovieListID(MOVIE_LIST_ID);

            //TODO remove if working CHW
            //this.movieListImpl.addUserByName(TEST_USER_NAME);
            this.movieListImpl.addUser(testUser);

        }
        catch(UserNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testAddMovieTrue() {
        this.movieListImpl.addMovie(testMovie);
        assertTrue(this.movieListImpl.getMovies().contains(testMovie));
    }

    @Test
    public void testAddMovieFalseNull() {
        testMovie = null;
        assertFalse(movieListImpl.addMovie(testMovie));
    }

    @Test
    public void testAddMovieFalse_AlreadyContains() {
        movieListImpl.addMovie(testMovie);
        assertFalse(movieListImpl.addMovie(testMovie));
    }

    @Test
    public void testAddMovie_Size() {
        this.movieListImpl.addMovie(testMovie);
        assertTrue(this.movieListImpl.getMovies().size() == 1);
    }

    @Test
    public void testAddMoviesTrue() {
        movieListImpl.addMovies(movies);
        assertTrue(movieListImpl.contains(testMovieTwo));
    }

    @Test
    public void testAddMoviesFalse() {
        movies = null;
        assertFalse(movieListImpl.addMovies(movies));

    }

    @Test
    public void testDeleteMovieFalse() {
        assertFalse(movieListImpl.deleteMovie(testMovie));
    }

    @Test
    public void testDeleteMovieFalseInstance() {
        testMovie = null;
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
    public void testAddUser() throws UserNotFoundException {
        UserImpl user = UserModel.getInstance().findById(SEC_TEST_USER_NAME);
        this.movieListImpl.addUser(user);
        assertTrue(this.movieListImpl.getUsers().contains(user));
    }

    @Test(expected = UserNotFoundException.class)
    public void testAddUserEXCEPTION() throws UserNotFoundException {
        UserModel.getInstance().remove(testUserTwo);
        movieListImpl.addUser(testUserTwo);
    }

    @Test
    public void testHasUserTrue() {
        assertTrue(movieListImpl.hasUser(testUser));
    }

    @Test
    public void testHasUserFalse() {
        assertFalse(movieListImpl.hasUser(testUserTwo));
    }

    @Test
    public void testHashcodeTrue() {
        int testHashCode = movieListImpl.hashCode();
        assertTrue(movieListImpl.hashCode()==testHashCode);
    }

    @Test
    public void testHashcodeFalse() {
        int testHashCode = movieListImpl.hashCode();
        movieListImpl.setName("different name");
        assertFalse(movieListImpl.hashCode()==testHashCode);
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
    public void testEqualsTrue() {
        otherMovieList = this.movieListImpl;
        assertTrue(this.movieListImpl.equals(otherMovieList));
    }

    @Test
    public void testEqualsFalse() {
        otherMovieList.addMovie(testMovie);
        assertFalse(this.movieListImpl.equals(otherMovieList));
    }

    @Test
    public void testEqualsFalseInstance() {
        assertFalse(this.movieListImpl.equals(otherInst));
    }

    @Test
    public void testEqualsFalseCreator() {
        assertFalse(this.movieListImpl.equals(otherCreatorList));
    }

    @Test
    public void testEqualsFalseUsers() {
        try{
        otherMovieList.addUser(testUserTwo);}
        catch(UserNotFoundException e){
            System.out.println(e.getMessage());
        }
        assertFalse(this.movieListImpl.equals(otherMovieList));
    }

    @Test
    public void testEqualsFalseMovies() {
        otherMovieList.addMovie(testMovieTwo);
        assertFalse(movieListImpl.equals(otherMovieList));
    }

    @Test
    public void testEqualsFalseName() {
        otherMovieList.setName(SEC_TEST_MOVIE_LIST_NAME);
        assertFalse(movieListImpl.equals(otherMovieList));
    }


    @After
    public void tearDown() {
        UserModel um = UserModel.getInstance();
        um.remove(testUser);
        um.remove(testUserTwo);

    }

}