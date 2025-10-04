import java.util.Date;

public class HealthData {
    private Date date;

    public HealthData() {
        this.date = new Date();
    }

    public void recordData() {
        System.out.println("Recording general health data for " + date);
    }

    public Date getDate() {
        return date;
    }
}