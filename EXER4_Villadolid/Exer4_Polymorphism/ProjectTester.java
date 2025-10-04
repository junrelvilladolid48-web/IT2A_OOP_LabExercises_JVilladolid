// ProjectTester.java
public class ProjectTester {
    public static void main(String[] args) {
        System.out.println("--- Creating User and Recording Health Data ---");
        // Create an instance of the User class
        User user1 = new User("Junrel", "JM123", "junrelvilladolid48@gmail.com");
        user1.login();
        user1.updateProfile("junrelvilladolid48@gmail.com");

        System.out.println("\n--- Performing Health Check ---");
        // Create an instance of the HealthCheck subclass
        HealthCheck todayCheck = new HealthCheck(36.8, "No symptoms");
        todayCheck.checkTemperature();
        todayCheck.recordSymptoms();
        user1.addHealthData(todayCheck);

        System.out.println("\n--- Setting Wellness Reminders ---");
        // Create instances of WellnessReminder and its subclasses
        WellnessReminder generalReminder = new WellnessReminder("Time for a short break!");
        generalReminder.setReminder();

        ExerciseReminder exerciseReminder = new ExerciseReminder("30-minute run");
        exerciseReminder.setReminder();
        exerciseReminder.setExerciseReminder();

        HydrationReminder hydrationReminder = new HydrationReminder();
        hydrationReminder.setReminder();
        hydrationReminder.setHydrationReminder(2500);

        MedicationReminder medReminder = new MedicationReminder("Vitamins", 1);
        medReminder.setReminder();
        medReminder.setMedicationReminder();
        
        System.out.println("\nAll tasks completed for the day.");
    }
}