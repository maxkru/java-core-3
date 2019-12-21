import java.sql.*;

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

        disconnect();
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/?" +
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
