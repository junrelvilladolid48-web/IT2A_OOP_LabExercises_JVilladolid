import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String email;
    private List<HealthData> healthRecords;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.healthRecords = new ArrayList<>();
    }

    public void login() {
        System.out.println(username + " has logged in.");
    }

    public void logout() {
        System.out.println(username + " has logged out.");
    }

    public void updateProfile(String newEmail) {
        this.email = newEmail;
        System.out.println(username + "'s email updated to " + this.email);
    }
    
    public void addHealthData(HealthData data) {
        this.healthRecords.add(data);
    }
}
