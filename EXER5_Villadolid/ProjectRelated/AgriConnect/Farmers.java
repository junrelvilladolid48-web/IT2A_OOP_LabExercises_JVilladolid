package AgriConnect;

public class Farmers {
    private String name;
    private String location;

    public Farmers(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void displayInfo() {
        System.out.println("Farmer: " + name + " | Location: " + location);
    }
}
