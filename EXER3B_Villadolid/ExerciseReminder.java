public class ExerciseReminder extends WellnessReminder {
    private String exerciseType;

    public ExerciseReminder(String exerciseType) {
        super("Time for your workout!");
        this.exerciseType = exerciseType;
    }

    public void setExerciseReminder() {
        System.out.println("Exercise reminder set: " + exerciseType);
    }
}