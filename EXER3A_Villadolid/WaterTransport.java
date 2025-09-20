public class WaterTransport extends Transportation {
    public WaterTransport(String name) {
        super(name);
    }

    public void sail() {
        System.out.println("The " + getName() + " is sailing on water.");
    }

    public void dock() {
        System.out.println("The " + getName() + " is docking.");
    }
}