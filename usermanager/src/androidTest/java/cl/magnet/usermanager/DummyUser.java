package cl.magnet.usermanager;

/**
 * Created by ignacio on 14-12-14.
 */
public class DummyUser extends User {

    private String mDummyField;

    public DummyUser(String mUsername, String mPassword, String mDummyField) {
        super(mUsername, mPassword);
        this.mDummyField = mDummyField;
    }

    public String getDummyField() {
        return mDummyField;
    }

    public void setDummyField(String dummyField) {
        this.mDummyField = dummyField;
    }
}
