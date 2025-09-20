public class AirTransport extends Transportation {
    public AirTransport(String name) {
        super(name);
    }

    public void takeOff() {
        System.out.println("The " + getName() + " is taking off.");
    }

    public void land() {
        System.out.println("The " + getName() + " is landing.");
    }
}