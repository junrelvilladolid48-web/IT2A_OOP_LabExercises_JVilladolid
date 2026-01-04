package util;

import model.DailyCheckIn;
import model.TherapistSession;

import java.io.FileWriter;
import java.util.List;

public class CSVUtils {
    public static void exportCheckinsToCSV(List<DailyCheckIn> list) throws Exception {
        try (FileWriter fw = new FileWriter("checkins.csv")) {
            fw.write("id,user_id,mood,stress,notes,date\n");
            for (DailyCheckIn d : list) {
                fw.write(String.format("%d,%d,%d,%d,%s,%s\n",
                        d.getCheckInId(), d.getUserId(), d.getMoodRating(), d.getStressLevel(),
                        escape(d.getNotes()), d.getDate()));
            }
        }
    }

    public static void exportSessionsToCSV(List<TherapistSession> list) throws Exception {
        try (FileWriter fw = new FileWriter("sessions.csv")) {
            fw.write("id,user_id,therapist,date,duration,notes\n");
            for (TherapistSession s : list) {
                fw.write(String.format("%d,%d,%s,%s,%d,%s\n",
                        s.getSessionId(), s.getUserId(), escape(s.getTherapistName()),
                        s.getSessionDate(), s.getDurationMinutes(), escape(s.getNotes())));
            }
        }
    }

    private static String escape(String v) {
        if (v == null) return "";
        return v.replace("\n", " ").replace(",", " ");
    }
}
