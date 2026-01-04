package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValidationUtils {
    public static String validateCheckIn(int mood, int stress, String date) {
        if (mood < 1 || mood > 10) return "Mood must be between 1 and 10.";
        if (stress < 1 || stress > 10) return "Stress must be between 1 and 10.";
        if (!isValidDate(date)) return "Date must be YYYY-MM-DD.";
        return "";
    }

    public static String validateSession(String therapist, String date, String durationText) {
        if (therapist == null || therapist.trim().isEmpty()) return "Therapist name is required.";
        if (!isValidDate(date)) return "Date must be YYYY-MM-DD.";
        try {
            int d = Integer.parseInt(durationText);
            if (d <= 0) return "Duration must be a positive integer (minutes).";
        } catch (NumberFormatException ex) {
            return "Duration must be numeric (minutes).";
        }
        return "";
    }

    public static boolean isValidDate(String d) {
        if (d == null) return false;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        fmt.setLenient(false);
        try {
            fmt.parse(d);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }
}
