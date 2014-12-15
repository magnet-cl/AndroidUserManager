package cl.magnet.usermanager.DummyUserClasses;

import cl.magnet.usermanager.User;

/**
 * Created by ignacio on 14-12-14.
 */
public class DummyUser extends User {

    private String mStringDummyField;
    private long mLongDummyField;
    private int mIntegerDummyField;
    private boolean mBooleanDummyField;
    private float mFloatDummyField;

    public DummyUser() {
    }

    public DummyUser(String mUsername, String mPassword, String mStringDummyField,
                     long mLongDummyField, int mIntegerDummyField, boolean mBooleanDummyField,
                     float mFloatDummyField) {
        super(mUsername, mPassword);
        this.mStringDummyField = mStringDummyField;
        this.mLongDummyField = mLongDummyField;
        this.mIntegerDummyField = mIntegerDummyField;
        this.mBooleanDummyField = mBooleanDummyField;
        this.mFloatDummyField = mFloatDummyField;
    }

    public String getStringDummyField() {
        return mStringDummyField;
    }

    public void setStringDummyField(String mStringDummyField) {
        this.mStringDummyField = mStringDummyField;
    }

    public long getLongDummyField() {
        return mLongDummyField;
    }

    public void setLongDummyField(long mLongDummyField) {
        this.mLongDummyField = mLongDummyField;
    }

    public int getIntegerDummyField() {
        return mIntegerDummyField;
    }

    public void setIntegerDummyField(int mIntegerDummyField) {
        this.mIntegerDummyField = mIntegerDummyField;
    }

    public boolean getBooleanDummyField() {
        return mBooleanDummyField;
    }

    public void setBooleanDummyField(boolean mBooleanDummyField) {
        this.mBooleanDummyField = mBooleanDummyField;
    }

    public float getFloatDummyField() {
        return mFloatDummyField;
    }

    public void setFloatDummyField(float mFloatDummyField) {
        this.mFloatDummyField = mFloatDummyField;
    }

    @Override
    public boolean equals(Object obj){

        if (obj == this) {
            return true;
        }

        DummyUser user = (DummyUser) obj;

        if(!super.equals(obj)){
            return false;
        }

        String dummyField = user.getStringDummyField();
        long longDummy = user.getLongDummyField();
        int intDummy = user.getIntegerDummyField();
        boolean booleanDummy = user.getBooleanDummyField();
        float floatDummy = user.getFloatDummyField();


        if((mStringDummyField != null && dummyField != null)) {
            if (!mStringDummyField.equals(dummyField)) {
                return false;
            }
        } else if(mStringDummyField != dummyField){
            return false;
        }

        if( mLongDummyField != longDummy){
            return false;
        }

        if (mIntegerDummyField != intDummy){
            return false;
        }
        if (mBooleanDummyField != booleanDummy){
            return false;
        }
        if (mFloatDummyField != floatDummy){
            return false;
        }

        return true;
    }
}
