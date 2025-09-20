public class HydrationReminder extends WellnessReminder {
    private double waterIntake;

    public HydrationReminder() {
        super("Remember to drink water!");
    }

    public void setHydrationReminder(double intake) {
        this.waterIntake = intake;
        System.out.println("Hydration goal set: " + waterIntake + "ml of water.");
    }
}