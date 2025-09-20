import java.util.Date;

public class WellnessReminder extends HealthData {
    private String reminderMessage;
    private Date time;

    public WellnessReminder(String message) {
        super();
        this.reminderMessage = message;
        this.time = new Date();
    }

    public void setReminder() {
        System.out.println("Reminder set: '" + reminderMessage + "' at " + time);
    }

    public String getReminder() {
        return reminderMessage;
    }
}