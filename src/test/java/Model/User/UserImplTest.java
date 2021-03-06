package Model.User;

import Model.MovieList.MovieList;
import Model.MovieList.MovieListImpl;
import Model.User.Exception.UserNotFoundException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static java.util.Objects.hash;
import static org.junit.Assert.*;

public class UserImplTest {

    private static final String TEST_USER_NAME = "test@test.de";
    private static final String TEST_USER_PASSWORD = "test_password";
    private static final String MOVIELIST_NAME = "BaseMovieList";

    private static User user;
    private static UserModel userModel;

    @BeforeClass
    public static void beforeClass() {

        // we create a user
        user = new UserImpl(TEST_USER_NAME, TEST_USER_PASSWORD);
        userModel = UserModel.getInstance();

        try {
            if ((userModel.login(user)) == null) {
                userModel.register(user);
            }
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (Model.User.Exception.UserWrongPasswordException e) {
            e.printStackTrace();
        }

    }

    @Test (expected = Exception.class)
    public void UserImpl(){
        User user = new UserImpl(null, "");
    }


    @Test
    public void addMovieList() {
        MovieList movieList = null;

        try {
            movieList = new MovieListImpl("MovieList1", TEST_USER_NAME);

            user.addMovieList(movieList);
            assertTrue(user.getMovieLists().contains(movieList));

        } catch (UserNotFoundException e) {
            e.printStackTrace();
            fail();
        }


    }

    @Test
    public void deleteMovieList() {
        // we ensure that he has at least one movielist
        try {
            MovieList movieList = new MovieListImpl(MOVIELIST_NAME, user.getUsername());
            user.addMovieList(movieList);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            fail();
        }

        // delete the movielist
        MovieList movieListFromUser = user.getMovieLists().get(0);
        user.deleteMovieList(movieListFromUser);
        assertTrue(!user.getMovieLists().contains(movieListFromUser));
    }

    @Test
    public void getUsername() {
        assertEquals(TEST_USER_NAME, user.getUsername());
    }

    @Test
    public void getPasswordHash() {
        assertEquals(hash(TEST_USER_PASSWORD), (int) user.getPasswordHash());
    }


    @AfterClass
    public static void afterClass() {

        // remove the first added user
        UserModel.getInstance().remove((UserImpl) user);
    }
}