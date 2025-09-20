public class SpaceShuttle extends AirTransport {
    private String mission;

    public SpaceShuttle(String mission) {
        super("Space Shuttle");
        this.mission = mission;
    }

    public void launch() {
        System.out.println("The " + getName() + " is launching for the " + mission + " mission.");
    }
}