package Model.User;

import Model.Movie.Movie;
import Model.MovieList.MovieList;
import Model.MovieList.MovieListImpl;
import Model.User.Exception.UserNotFoundException;
import org.junit.*;

import static java.util.Objects.hash;
import static org.junit.Assert.*;

public class UserImplTest {

    private static final String TEST_USER_NAME = "test@test.de";
    private static final String TEST_USER_PASSWORD = "test_password";

    private User user;
    private UserModel userModel;

    @BeforeClass
    public void beforeClass(){
        userModel = UserModel.getInstance();

        if (userModel.findById(TEST_USER_NAME) == null) {
            // add this user once
            UserModel.getInstance().register(user);
        } else {
            user = userModel.login(new UserImpl(TEST_USER_NAME, TEST_USER_PASSWORD));
        }

    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void addMovieList() {
        MovieList movieList = null;
        try {
            movieList = new MovieListImpl("MovieList1", TEST_USER_NAME);

        } catch (UserNotFoundException e) {
            e.printStackTrace();
            fail();
        }

        user.addMovieList(movieList);
        assertTrue(user.getMovieLists().contains(movieList));
    }

    @Test
    public void deleteMovieList() {
        fail();
    }

    @Test
    public void getUsername() {
        fail();
    }

    @Test
    public void getMovieLists() {
        fail();
    }

    @Test
    public void getPasswordHash() {
        assertEquals(hash(TEST_USER_PASSWORD), (int) user.getPasswordHash());
    }

    @Test
    public void setPasswordHash() {
        fail();
    }

    @Test
    public void setUserName() {
        fail();
    }

    @Test
    public void setMovieLists() {
        fail();
    }

    @After
    public void tearDown() throws Exception {
        user = null;
    }

    @AfterClass
    public void afterClass(){

        // remove the first added user
        UserModel.getInstance().remove((UserImpl) user);
    }
}