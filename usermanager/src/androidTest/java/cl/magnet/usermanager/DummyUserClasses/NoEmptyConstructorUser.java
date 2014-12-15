package cl.magnet.usermanager.DummyUserClasses;

import cl.magnet.usermanager.User;

/**
 * Created by ignacio on 15-12-14.
 */
public class NoEmptyConstructorUser extends User {

    private String dummyField;

    public NoEmptyConstructorUser(String dummyField) {
        this.dummyField = dummyField;
    }
}
