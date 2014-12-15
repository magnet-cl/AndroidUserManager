package cl.magnet.usermanager;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by ignacio on 15-12-14.
 */
public class IgnoredFieldsTest extends ActivityInstrumentationTestCase2<DummyActivity> {

    private UserManager<AnnotationsUser> manager;

    public IgnoredFieldsTest() {
        super(DummyActivity.class);
    }

    public IgnoredFieldsTest(Class<DummyActivity> activityClass) {
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

    public void testFieldIsIgnored(){
        AnnotationsUser user = new AnnotationsUser();

        user.setIgnoredName("Other name");

        manager.logInUser(user);

        assertEquals("Other name", user.getIgnoredName());

        AnnotationsUser storedUser = manager.getUser();

        assertEquals("Jose", storedUser.getIgnoredName());

        assertNotSame(user, storedUser);
    }
}
