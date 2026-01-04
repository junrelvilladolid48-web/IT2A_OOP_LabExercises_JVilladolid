package model;

public class DailyCheckIn {
    private int checkInId;
    private int userId;
    private int moodRating; // 1-10
    private int stressLevel; // 1-10
    private String notes;
    private String date; // YYYY-MM-DD

    public DailyCheckIn(){}

    public DailyCheckIn(int checkInId, int userId, int moodRating, int stressLevel, String notes, String date) {
        this.checkInId = checkInId;
        this.userId = userId;
        this.moodRating = moodRating;
        this.stressLevel = stressLevel;
        this.notes = notes;
        this.date = date;
    }

    public int getCheckInId() { return checkInId; }
    public void setCheckInId(int checkInId) { this.checkInId = checkInId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getMoodRating() { return moodRating; }
    public void setMoodRating(int moodRating) { this.moodRating = moodRating; }
    public int getStressLevel() { return stressLevel; }
    public void setStressLevel(int stressLevel) { this.stressLevel = stressLevel; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    @Override
    public String toString() {
        return date + " mood:" + moodRating + " stress:" + stressLevel;
    }
}
