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
        assertEquals(user, userModel.login(user));

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

    /* ------------------ Database Tests ------------------ */

    @Test
    public void persist(){

        // we persist the test user
        userModel.persist((UserImpl) user);

        // we check if the new user is there
        User tmpUser = userModel.findById(user.getUsername());

        // check if they are the same
        assertEquals(user, tmpUser);
    }

    @Test
    public void remove(){

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
    public void findById(){
        // persist our user first
        userModel.persist((UserImpl) user);

        // try to get him
        User tmpUser = userModel.findById(user.getUsername());

        // check if it is really him
        assertEquals(user, tmpUser);
    }

    @After
    public void tearDown() throws Exception {

        // remove the now added user
        userModel.remove((UserImpl) user);
    }
}