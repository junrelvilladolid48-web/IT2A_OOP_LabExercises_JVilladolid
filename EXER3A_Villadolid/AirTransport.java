public class AirTransport extends Transportation {
    public AirTransport(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public void travel() {
        System.out.println(name + " flies in the air with capacity of " + capacity);
    }
}
