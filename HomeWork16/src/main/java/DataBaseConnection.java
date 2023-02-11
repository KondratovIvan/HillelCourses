import java.sql.*;
public class DataBaseConnection {
    public static   Connection getConnection() {

        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/homework16";
        String user = "postgres";
        String password = "admin";

        try {
            connection = DriverManager.getConnection(url, user, password);
//            if (connection != null) {
//                System.out.println("Подключение к PostgreSQL серверу установлено");
//            }
        }
        catch (SQLException e) {
            System.out.println("Ошибка при подключении");
            System.out.println(e);
        }

        return connection;
    }
    public void close() throws SQLException {
        try {
            Connection connection=DataBaseConnection.getConnection();
            connection.close();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        System.out.println("З'єднання видалено успішно");
    }
}
