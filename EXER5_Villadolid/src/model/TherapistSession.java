package model;

public class TherapistSession {
    private int sessionId;
    private int userId;
    private String therapistName;
    private String sessionDate; // YYYY-MM-DD
    private int durationMinutes;
    private String notes;

    public TherapistSession(){}

    public TherapistSession(int sessionId, int userId, String therapistName, String sessionDate, int durationMinutes, String notes) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.therapistName = therapistName;
        this.sessionDate = sessionDate;
        this.durationMinutes = durationMinutes;
        this.notes = notes;
    }

    public int getSessionId() { return sessionId; }
    public void setSessionId(int sessionId) { this.sessionId = sessionId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getTherapistName() { return therapistName; }
    public void setTherapistName(String therapistName) { this.therapistName = therapistName; }
    public String getSessionDate() { return sessionDate; }
    public void setSessionDate(String sessionDate) { this.sessionDate = sessionDate; }
    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    @Override
    public String toString() {
        return sessionDate + " with " + therapistName;
    }
}
