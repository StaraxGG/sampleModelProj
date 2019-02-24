package Model.User;

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
        if (userModel.login(user) != null)
            fail();

        // add the user
        userModel.register(user);

        // try to login with a wrong password
        UserImpl tmpUser = new UserImpl(TEST_USER_NAME, TEST_USER_PASSWORD + "x");
        if (userModel.login(tmpUser) == user)
            fail();

        // now try to login
        assertEquals(userModel.login(user), user);

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
        userModel.login(user);
        assertEquals(user, userModel.getCurrentUser());
    }

    @Test
    public void findMovielistForMovie() {
        fail();
    }

    /* ------------------ Database Tests ------------------ */

    @Test
    public void persist(){
        fail();
    }

    @Test
    public void remove(){
        fail();
    }

    @Test
    public void findById(){
        fail();
    }

    @After
    public void tearDown() throws Exception {

        // remove the now added user
        userModel.remove((UserImpl) user);
    }
}