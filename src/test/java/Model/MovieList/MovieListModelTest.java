package Model.MovieList;

import Model.User.Exception.UserNotFoundException;
import Model.User.User;
import Model.User.UserImpl;
import Model.User.UserModel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MovieListModelTest {

    private static MovieListModel movieListModel;
    private static MovieList movieList1;
    private static MovieList movieList2;
    private static User user;

    private static final String MOVIE_LIST_NAME = "MovieList";
    private static final String USER_NAME = "test@test.de";
    private static final String USER_PASSWORD = "test";

    @BeforeClass
    public static void beforeClass() throws Exception {
        // init values
        movieListModel = MovieListModel.getInstance();
        user = new UserImpl(USER_NAME, USER_PASSWORD);

        // check if this user exists
        if (UserModel.getInstance().findById(user.getUsername()) == null)

            // otherwise register him, he does not have to be logged in
            UserModel.getInstance().register(user);

        // create the movie list
        movieList1 = new MovieListImpl(MOVIE_LIST_NAME, user.getUsername());
        movieList2 = new MovieListImpl(MOVIE_LIST_NAME + "2", user.getUsername());

        // add to the database
        movieListModel.persist((MovieListImpl) movieList1);
        movieListModel.persist((MovieListImpl) movieList2);

    }

    @AfterClass
    public static void afterClass() {
        movieListModel.remove((MovieListImpl) movieList1);
        movieListModel.remove((MovieListImpl) movieList2);
        UserModel.getInstance().remove((UserImpl) user);
    }

    /* ------------------ Database Tests ------------------ */

    @Test
    public void persist() throws UserNotFoundException {
        // create a new moveList
        MovieListImpl movieList = new MovieListImpl(user.getUsername(), USER_PASSWORD);

        // persist this list
        movieListModel.persist(movieList);

        // retrieve it again
        MovieList tmpMovieList = movieListModel.findById(movieList.getId());

        // check if they were the same
        assertEquals(movieList, tmpMovieList);
    }

    @Test
    public void remove() {

    }

    @Test
    public void findById() {
        fail();
    }

    @Test
    public void findByIds() {
        List<MovieList> movieListList = movieListModel.findByIds(Arrays.asList(movieList1.getId(), movieList2.getId()));
        if (!movieListList.contains(movieList1))
            fail();
        if (!movieListList.contains(movieList2))
            fail();
        if (movieListList.size() != 2)
            fail();

        assertTrue(true);
    }

    @After
    public void tearDown() throws Exception {
    }
}