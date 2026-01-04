package model;

public class User extends Person {
    public User() { super(); }
    public User(int id, String name, int age, String gender) {
        super(id, name, age, gender);
    }

    @Override
    public String getRole() {
        return "User";
    }

    @Override
    public String toString() {
        return getName();
    }
}
