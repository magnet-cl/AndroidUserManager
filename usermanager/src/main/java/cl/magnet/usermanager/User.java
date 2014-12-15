package cl.magnet.usermanager;

/**
 * Created by ignacio on 14-12-14.
 */
public class User {

    private  String mUsername, mPassword;

    public User(){

    }

    public User(String mUsername, String mPassword) {
        this.mUsername = mUsername;
        this.mPassword = mPassword;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    @Override
    public boolean equals(Object obj){

        if (!(obj instanceof User)) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        User user = (User) obj;

        return (mUsername == user.getUsername() && mPassword == user.getPassword());
    }
}
