package cl.magnet.usermanager;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by ignacio on 14-12-14.
 */
public class UserManagerTest extends ActivityInstrumentationTestCase2<DummyActivity> {

    private UserManager<User> manager;

    public UserManagerTest() {
        super(DummyActivity.class);
    }

    public UserManagerTest(Class<DummyActivity> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        manager = new UserManager<>(getActivity());
    }

    @Override
    protected void tearDown() throws Exception {
        manager.logout();
        super.tearDown();
    }

    public void testCreateUserManager(){
        assertNotNull(manager);
    }

    public void testLoginInitializedUser(){
        User user = new User("Ignacio", "123");

        manager.logInUser(user);

        assertEquals(true, manager.isUserLogged());

        User storedUser = manager.getUser();
        assertEquals(true, User.class.equals(storedUser.getClass()));

        assertEquals(user, storedUser);
    }

    /*public void testFooThrowsIndexOutOfBoundsException() {
        boolean thrown = false;

        try {
            foo.doStuff();
        } catch (IndexOutOfBoundsException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }*/

    public void testLoginUninitializedUser(){
        User user = new User();

        manager.logInUser(user);

        assertEquals(true, manager.isUserLogged());

        User storedUser = manager.getUser();
        assertEquals(true, User.class.equals(storedUser.getClass()));

        assertEquals(user, storedUser);
    }
}
