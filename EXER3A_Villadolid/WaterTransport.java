public class WaterTransport extends Transportation {
    public WaterTransport(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public void travel() {
        System.out.println(name + " sails in water with capacity of " + capacity);
    }
}
