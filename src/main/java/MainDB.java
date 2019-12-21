import java.sql.*;
import java.util.Random;

public class MainDB {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement pstm;

    public static void main(String[] args) {
        try {
            connect();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try {
            stmt.execute("CREATE TABLE IF NOT EXISTS main.students" +
                    " (id INT NOT NULL AUTO_INCREMENT," +
                    " name VARCHAR(128) NOT NULL," +
                    " score INT NOT NULL," +
                    " PRIMARY KEY (id));");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            connection.setAutoCommit(false);
            pstm = connection.prepareStatement("INSERT INTO main.students (name, score) VALUES (?, ?)");
            Random random = new Random();
            for (int i = 0; i < 100; i++) {
                pstm.setString(1, "Student " + i);
                pstm.setInt(2, random.nextInt(101));
                pstm.addBatch();
            }
            pstm.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmt.execute("UPDATE main.students SET score = 0 WHERE name = 'Student 10'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            int deletedNumber = stmt.executeUpdate("DELETE FROM main.students WHERE score < 20");
            System.out.println("Deleted " + deletedNumber + " students with low score");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ResultSet rs = stmt.executeQuery("SELECT name, score FROM main.students WHERE score > 80");
            System.out.println("Students with high scores: ");
            while(rs.next()) {
                System.out.println(rs.getString("name") + " - " + rs.getInt("score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        disconnect();
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/main?" +
                "user=chat-server&password=XgVbEF4vTzP!R&serverTimezone=Europe/Moscow");
        stmt = connection.createStatement();
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
