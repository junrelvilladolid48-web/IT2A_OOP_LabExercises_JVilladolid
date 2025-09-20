public class Transportation {
    private String name;

    public Transportation(String name) {
        this.name = name;
    }

    public void move() {
        System.out.println("The " + name + " is moving.");
    }

    public void stop() {
        System.out.println("The " + name + " has stopped.");
    }

    public String getName() {
        return name;
    }
}