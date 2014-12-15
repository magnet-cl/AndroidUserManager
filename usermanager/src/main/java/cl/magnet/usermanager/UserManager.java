package cl.magnet.usermanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ignacio on 14-12-14.
 */
public class UserManager <T extends User> {

    private static final String TAG = UserManager.class.toString();
    private static final String PREFS_NAME = UserManager.class.toString();
    private static final String USER_CLASS = "UserClass";

    protected SharedPreferences mUserCredentials;

    public UserManager(Context context) {
        this.mUserCredentials = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void logInUser(T user){

        Class userClass = user.getClass();

        getEmptyConstructor(userClass);

        Field[] fields = getAllFields(userClass);

        for(Field field: fields){
            storeField(field, user);
        }

        SharedPreferences.Editor editor = mUserCredentials.edit();

        editor.putString(USER_CLASS, userClass.getName());
        editor.apply();
    }

    public void logout(){
        SharedPreferences.Editor editor = mUserCredentials.edit();
        editor.clear();
        editor.apply();
    }

    public boolean isUserLogged(){
        return !mUserCredentials.getString(USER_CLASS, "").equals("");
    }

    public T getUser(){

        if(isUserLogged()){

            String userClass = mUserCredentials.getString(USER_CLASS, "");
            T user;

            try {
                Class klass = Class.forName(userClass);
                user = constructUser(klass);

                addFieldsValues(user);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
            return user;
        }
        return null;
    }

    private Field[] getAllFields(Class userClass){
        ArrayList<Field> fields = new ArrayList<>(
                Arrays.asList(userClass.getDeclaredFields()));

        Class parent = userClass.getSuperclass();

        while (!parent.equals(Object.class)){
            fields.addAll(Arrays.asList(parent.getDeclaredFields()));
            parent = parent.getSuperclass();
        }

        return fields.toArray(new Field[fields.size()]);
    }

    private void storeField(Field field, T user){

        if(isIgnored(field)) {
            return;
        }

        SharedPreferences.Editor editor = mUserCredentials.edit();

        field.setAccessible(true);

        Class type = field.getType();
        try {

            if (type.equals(String.class)){
                Log.d(TAG, "It's a string!");
                editor.putString(field.getName(), (String) field.get(user));

            }else if (type.equals(Long.TYPE) || type.equals(Long.class)){
                Log.d(TAG, "It's a long!");
                editor.putLong(field.getName(), field.getLong(user));

            }else if (type.equals(Integer.TYPE) || type.equals(Integer.class)){
                Log.d(TAG, "It's a integer!");
                editor.putInt(field.getName(), field.getInt(user));

            }else if (type.equals(Boolean.TYPE) || type.equals(Boolean.class)){
                Log.d(TAG, "It's a boolean!");
                editor.putBoolean(field.getName(), field.getBoolean(user));

            }else if (type.equals(Float.TYPE) || type.equals(Float.class)){
                Log.d(TAG, "It's a float!");
                editor.putFloat(field.getName(), field.getFloat(user));

            } else {
                throw new UnsupportedOperationException("Type of the field in the class is not" +
                        "supported");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        editor.apply();
        field.setAccessible(false);
    }

    private boolean isIgnored(Field field){
        Annotation annotation = field.getAnnotation(Ignore.class);

        if(annotation == null){
            return false;
        }

        return true;
    }

    private void addFieldsValues(T user){

        Class userClass = user.getClass();
        Field[] fields = getAllFields(userClass);

        for(Field field: fields){
            retrieveField(field, user);
        }
    }

    private void retrieveField(Field field, T user){

        if(isIgnored(field)) {
            return;
        }

        field.setAccessible(true);

        Class type = field.getType();
        try {

            if (type.equals(String.class)){
                Log.d(TAG, "It's a string!");
                field.set(user, mUserCredentials.getString(field.getName(), null));

            }else if (type.equals(Long.TYPE) || type.equals(Long.class)){
                Log.d(TAG, "It's a long!");
                field.setLong(user, mUserCredentials.getLong(field.getName(), 0L));

            }else if (type.equals(Integer.TYPE) || type.equals(Integer.class)){
                Log.d(TAG, "It's a integer!");
                field.setInt(user, mUserCredentials.getInt(field.getName(), 0));

            }else if (type.equals(Boolean.TYPE) || type.equals(Boolean.class)){
                Log.d(TAG, "It's a boolean!");
                field.setBoolean(user, mUserCredentials.getBoolean(field.getName(), false));

            }else if (type.equals(Float.TYPE) || type.equals(Float.class)){
                Log.d(TAG, "It's a float!");
                field.setFloat(user, mUserCredentials.getFloat(field.getName(), 0.0f));

            } else {
                throw new UnsupportedOperationException("Type is not supported");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        field.setAccessible(false);
    }

    private T constructUser(Class klass){

        T user = null;

        Constructor constructor = getEmptyConstructor(klass);

        try {
            constructor.setAccessible(true);
            user = (T)constructor.newInstance();

            // production code should handle these exceptions more gracefully
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return user;
    }

    private Constructor getEmptyConstructor(Class klass){
        Constructor[] constructors = klass.getDeclaredConstructors();
        Constructor constructor = null;
        for (Constructor auxConstructor : constructors) {
            constructor = auxConstructor;
            if (constructor.getGenericParameterTypes().length == 0) {
                break;
            }
        }

        assert constructor != null;
        if(constructor.getGenericParameterTypes().length != 0){
            throw new UnsupportedOperationException("User class needs to have an empty " +
                    "constructor");
        }

        return constructor;
    }
}
