package dao;

import model.User;
import model.DailyCheckIn;
import model.TherapistSession;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages SQLite connection and CRUD
 */
public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:resources/wellness.db";
    private static DatabaseManager instance;
    private Connection conn;

    private DatabaseManager() throws SQLException {
        connect();
        createTablesIfNeeded();
    }

    public static DatabaseManager getInstance() throws SQLException {
        if (instance == null) instance = new DatabaseManager();
        return instance;
    }

    private void connect() throws SQLException {
        conn = DriverManager.getConnection(DB_URL);
    }

    private void createTablesIfNeeded() throws SQLException {
        String usersSql = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER, gender TEXT);";
        String checkinSql = "CREATE TABLE IF NOT EXISTS checkins (id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, mood INTEGER, stress INTEGER, notes TEXT, date TEXT, FOREIGN KEY(user_id) REFERENCES users(id));";
        String sessionSql = "CREATE TABLE IF NOT EXISTS sessions (id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, therapist TEXT, date TEXT, duration INTEGER, notes TEXT, FOREIGN KEY(user_id) REFERENCES users(id));";
        try (Statement st = conn.createStatement()) {
            st.execute(usersSql);
            st.execute(checkinSql);
            st.execute(sessionSql);
        }
        if (getAllUsers().isEmpty()) {
            insertUser(new User(0, "Default User", 25, "Other"));
        }
    }

    public void insertUser(User u) throws SQLException {
        String sql = "INSERT INTO users(name, age, gender) VALUES(?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getName());
            ps.setInt(2, u.getAge());
            ps.setString(3, u.getGender());
            ps.executeUpdate();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> list = new ArrayList<>();
        String sql = "SELECT id, name, age, gender FROM users";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new User(rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("gender")));
            }
        }
        return list;
    }

    public void insertCheckIn(DailyCheckIn c) throws SQLException {
        String sql = "INSERT INTO checkins(user_id, mood, stress, notes, date) VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, c.getUserId());
            ps.setInt(2, c.getMoodRating());
            ps.setInt(3, c.getStressLevel());
            ps.setString(4, c.getNotes());
            ps.setString(5, c.getDate());
            ps.executeUpdate();
        }
    }

    public List<DailyCheckIn> getAllCheckIns() throws SQLException {
        List<DailyCheckIn> list = new ArrayList<>();
        String sql = "SELECT id, user_id, mood, stress, notes, date FROM checkins ORDER BY date DESC";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new DailyCheckIn(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("mood"), rs.getInt("stress"), rs.getString("notes"), rs.getString("date")));
            }
        }
        return list;
    }

    public void updateCheckIn(DailyCheckIn c) throws SQLException {
        String sql = "UPDATE checkins SET mood=?, stress=?, notes=?, date=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, c.getMoodRating());
            ps.setInt(2, c.getStressLevel());
            ps.setString(3, c.getNotes());
            ps.setString(4, c.getDate());
            ps.setInt(5, c.getCheckInId());
            ps.executeUpdate();
        }
    }

    public void deleteCheckIn(int id) throws SQLException {
        String sql = "DELETE FROM checkins WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void insertSession(TherapistSession s) throws SQLException {
        String sql = "INSERT INTO sessions(user_id, therapist, date, duration, notes) VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, s.getUserId());
            ps.setString(2, s.getTherapistName());
            ps.setString(3, s.getSessionDate());
            ps.setInt(4, s.getDurationMinutes());
            ps.setString(5, s.getNotes());
            ps.executeUpdate();
        }
    }

    public List<TherapistSession> getAllSessions() throws SQLException {
        List<TherapistSession> list = new ArrayList<>();
        String sql = "SELECT id, user_id, therapist, date, duration, notes FROM sessions ORDER BY date DESC";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new TherapistSession(rs.getInt("id"), rs.getInt("user_id"), rs.getString("therapist"), rs.getString("date"), rs.getInt("duration"), rs.getString("notes")));
            }
        }
        return list;
    }

    public void updateSession(TherapistSession s) throws SQLException {
        String sql = "UPDATE sessions SET therapist=?, date=?, duration=?, notes=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getTherapistName());
            ps.setString(2, s.getSessionDate());
            ps.setInt(3, s.getDurationMinutes());
            ps.setString(4, s.getNotes());
            ps.setInt(5, s.getSessionId());
            ps.executeUpdate();
        }
    }

    public void deleteSession(int id) throws SQLException {
        String sql = "DELETE FROM sessions WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
