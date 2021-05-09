package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {

    static Connection connection;
    static Statement statement;

    private final String url = SQLConfig.url;

    private final String login = SQLConfig.login;
    private final String mot_de_passe = SQLConfig.mot_de_passe;

    static boolean isConnected = false;

    public SQLConnection() throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    }

    public void openConnection() throws SQLException {
        if (isConnected) return;

        connection = DriverManager.getConnection(url, login, mot_de_passe);
        statement = connection.createStatement();
        isConnected = true;
    }

    public void disconnect() throws SQLException {
        if (!isConnected) return;
        connection.close();
        isConnected = false;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static Statement getStatement() {
        return statement;
    }
}
