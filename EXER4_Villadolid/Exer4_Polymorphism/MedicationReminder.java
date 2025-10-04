public class MedicationReminder extends WellnessReminder {
    private String medicationName;
    private int dosage;

    public MedicationReminder(String medicationName, int dosage) {
        super("Time to take your medicine!");
        this.medicationName = medicationName;
        this.dosage = dosage;
    }

    public void setMedicationReminder() {
        System.out.println("Medication reminder set for: " + medicationName + " (" + dosage + "mg)");
    }
}