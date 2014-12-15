package cl.magnet.usermanager;

/**
 * Created by ignacio on 15-12-14.
 */
public class AnnotationsUser extends User {

    private String name = "Ignacio";

    @Ignore
    private String ignoredName = "Jose";

    public AnnotationsUser() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIgnoredName() {
        return ignoredName;
    }

    public void setIgnoredName(String ignoredName) {
        this.ignoredName = ignoredName;
    }
}
