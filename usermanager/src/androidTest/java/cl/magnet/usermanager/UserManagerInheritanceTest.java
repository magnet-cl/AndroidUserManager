package cl.magnet.usermanager;

import android.test.ActivityInstrumentationTestCase2;

import cl.magnet.usermanager.DummyUserClasses.DummyUser;

/**
 * Created by ignacio on 15-12-14.
 */
public class UserManagerInheritanceTest extends ActivityInstrumentationTestCase2<DummyActivity> {

    private UserManager<DummyUser> managerInheritance;

    public UserManagerInheritanceTest() {
        super(DummyActivity.class);
    }

    public UserManagerInheritanceTest(Class<DummyActivity> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        managerInheritance = new UserManager<>(getActivity());
    }

    @Override
    protected void tearDown() throws Exception {
        managerInheritance.logout();
        super.tearDown();
    }

    public void testUserInheritance(){
        DummyUser user = new DummyUser("Ignacio", "123", "dummy", 1L, 2, true, 2f );
        managerInheritance.logInUser(user);

        assertEquals(true, managerInheritance.isUserLogged());

        DummyUser storedUser = managerInheritance.getUser();
        assertEquals(true, DummyUser.class.equals(storedUser.getClass()));

        boolean result = user.equals(storedUser);
        assertEquals(result, true);
    }

    public void testLoginUninitializedUser(){
        DummyUser user = new DummyUser();

        managerInheritance.logInUser(user);

        assertEquals(true, managerInheritance.isUserLogged());

        User storedUser = managerInheritance.getUser();
        assertEquals(true, DummyUser.class.equals(storedUser.getClass()));

        boolean result = user.equals(storedUser);
        assertEquals(result, true);
    }
}
