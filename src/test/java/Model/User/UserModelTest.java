package Model.User;

import Model.MovieList.MovieListImpl;
import Model.MovieList.MovieListModel;
import Model.User.Exception.UserNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserModelTest {

    UserModel userModel;
    User user;

    private static final String TEST_USER_NAME = "test@test.de";
    private static final String TEST_USER_PASSWORD = "test_password";

    @Before
    public void setUp() throws Exception {
        userModel = UserModel.getInstance();
        user = new UserImpl(TEST_USER_NAME, TEST_USER_PASSWORD);
    }

    @Test
    public void getInstance() {
        // this test seems a bit inefficient
        assertEquals(userModel, UserModel.getInstance());
    }

    @Test
    public void login() {
        // this should return null as this user does not exist
        try {
            if (userModel.login(user) != null)
                fail();
        } catch (Model.User.Exception.UserNotFoundException e) {
            e.printStackTrace();
        } catch (Model.User.Exception.UserWrongPasswordException e) {
            e.printStackTrace();
        }

        // add the user
        userModel.register(user);

        // try to login with a wrong password
        UserImpl tmpUser = new UserImpl(TEST_USER_NAME, TEST_USER_PASSWORD + "x");
        try {
            if (userModel.login(tmpUser) == user)
                fail();
        } catch (Model.User.Exception.UserNotFoundException e) {
            e.printStackTrace();
        } catch (Model.User.Exception.UserWrongPasswordException e) {
            e.printStackTrace();
        }

        // now try to login
        try {
            assertEquals(user, userModel.login(user));
        } catch (Model.User.Exception.UserNotFoundException e) {
            e.printStackTrace();
        } catch (Model.User.Exception.UserWrongPasswordException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void register() {

        // register a new user
        if (!userModel.register(user))
            fail();

        // this user should now be logged in
        if (!userModel.getCurrentUser().equals(user))
            fail();

        // now tries to add the user again -- which should return false
        if (userModel.register(user))
            fail();

        // delete the user
        userModel.remove((UserImpl) user);

        // everything worked fine
        assertTrue(true);
    }

    @Test
    public void getCurrentUser() {
        try {
            userModel.login(user);
        } catch (Model.User.Exception.UserNotFoundException e) {
            e.printStackTrace();
        } catch (Model.User.Exception.UserWrongPasswordException e) {
            e.printStackTrace();
        }
        assertEquals(user, userModel.getCurrentUser());
    }

    /* ------------------ Database Tests ------------------ */

    @Test
    public void persist() {

        // we persist the test user
        userModel.persist((UserImpl) user);

        // we check if the new user is there
        User tmpUser = userModel.findById(user.getUsername());

        // check if they are the same
        assertEquals(user, tmpUser);
    }

    @Test
    public void remove() {

        // create a user
        UserImpl newUser = new UserImpl("new_user@test.de", "new_user_pass");

        // persist him
        userModel.persist(newUser);

        // remove him
        userModel.remove(newUser);

        // check if he is there
        User tmpUser = userModel.findById(newUser.getUsername());

        assertNull(tmpUser);
    }

    @Test
    public void findById() {
        // persist our user first
        userModel.persist((UserImpl) user);

        // try to get him
        User tmpUser = userModel.findById(user.getUsername());

        // check if it is really him
        assertEquals(user, tmpUser);
    }

    @Test
    public void update() {

        // we persist our user
        user = userModel.persist((UserImpl) user);

        // we get the value
        int initialSize = user.getMovieLists().size();

        // we change something about him
        // like adding an empty movieList
        try {
            MovieListImpl emptyList = new MovieListImpl("test_movie_list", user.getUsername());
            user.addMovieList(emptyList);

            // now we update the user and check the return object
            user = userModel.update((UserImpl) user);

            // remove the added list --this should not have any effect
            MovieListModel.getInstance().remove(emptyList);

            // check the object
            assertTrue(user.getMovieLists().size() > initialSize && user.getMovieLists().contains(emptyList));

        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

    }

    @After
    public void tearDown() throws Exception {

        // remove the now added user
        userModel.remove((UserImpl) user);
    }
}