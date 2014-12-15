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

        if (obj == this) {
            return true;
        }

        User user = (User) obj;

        String username = user.getUsername();
        String password = user.getPassword();

        if(mUsername != null && username != null){
            if (!mUsername.equals(username)) {
                return false;
            }
        } else if(mUsername != username){
            return false;
        }

        if (mPassword != null && password != null){
            if (!mPassword.equals(password)) {
                return false;
            }
        } else if(mPassword != password){
            return false;
        }

        return true;
    }
}
