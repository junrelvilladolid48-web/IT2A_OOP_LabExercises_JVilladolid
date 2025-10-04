public class HealthCheck extends HealthData {
    private double temperature;
    private String symptoms;

    public HealthCheck(double temperature, String symptoms) {
        super();
        this.temperature = temperature;
        this.symptoms = symptoms;
    }

    public void checkTemperature() {
        System.out.println("Temperature recorded: " + temperature + "°C");
    }

    public void recordSymptoms() {
        System.out.println("Symptoms recorded: " + symptoms);
    }
}