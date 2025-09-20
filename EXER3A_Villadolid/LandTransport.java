public class LandTransport extends Transportation {
    public LandTransport(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public void travel() {
        System.out.println(name + " drives on land with capacity of " + capacity);
    }
}
