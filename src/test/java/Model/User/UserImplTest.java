package Model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static java.util.Objects.hash;
import static org.junit.Assert.*;

public class UserImplTest {

    private static final String TEST_USER_NAME = "test@test.de";
    private static final String TEST_USER_PASSWORD = "test_password";

    User user;

    @Before
    public void setUp() throws Exception {
        user = new UserImpl(TEST_USER_NAME, TEST_USER_PASSWORD);
    }

    @Test
    public void addMovieList() {
    }

    @Test
    public void deleteMovieList() {
    }

    @Test
    public void getUsername() {
    }

    @Test
    public void getMovieLists() {
    }

    @Test
    public void getPasswordHash() {
        assertEquals(hash(TEST_USER_PASSWORD), (int) user.getPasswordHash());
    }

    @Test
    public void setPasswordHash() {
    }

    @Test
    public void setUserName() {
    }

    @Test
    public void setMovieLists() {
    }

    @After
    public void tearDown() throws Exception {
        user = null;
    }
}