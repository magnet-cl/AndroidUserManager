package cl.magnet.usermanager;

import android.test.ActivityInstrumentationTestCase2;

import cl.magnet.usermanager.DummyUserClasses.NoEmptyConstructorUser;
import cl.magnet.usermanager.DummyUserClasses.UnsupportedFieldsUser;

/**
 * Created by ignacio on 15-12-14.
 */
public class UnsupportedClassesTest extends ActivityInstrumentationTestCase2<DummyActivity> {

    private UserManager<NoEmptyConstructorUser> manager;
    private UserManager<UnsupportedFieldsUser> manager2;

    public UnsupportedClassesTest() {
        super(DummyActivity.class);
    }

    public UnsupportedClassesTest(Class<DummyActivity> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        manager = new UserManager<>(getActivity());
        manager2 = new UserManager<>(getActivity());
    }

    @Override
    protected void tearDown() throws Exception {
        manager.logout();
        manager2.logout();
        super.tearDown();
    }

    public void testEmptyConstructor(){
        boolean thrown = false;

        try {
            NoEmptyConstructorUser user = new NoEmptyConstructorUser("Test");

            manager.logInUser(user);
        } catch (UnsupportedOperationException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    public void testUnsupportedFields(){
        boolean thrown = false;

        try {
            UnsupportedFieldsUser user = new UnsupportedFieldsUser();

            manager2.logInUser(user);
        } catch (UnsupportedOperationException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }


}
