public class LandTransport extends Transportation {
    public LandTransport(String name) {
        super(name);
    }

    public void drive() {
        System.out.println("The " + getName() + " is driving.");
    }

    public void park() {
        System.out.println("The " + getName() + " is parking.");
    }
}