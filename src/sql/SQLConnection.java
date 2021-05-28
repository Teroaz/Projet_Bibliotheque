package sql;

import exceptions.ConfigurationException;
import exceptions.DatabaseException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SQLConnection {

    private static Connection connection;
    private static Statement statement;

    private String url;
    private String login;
    private String mot_de_passe;

    static boolean isConnected = false;

    public SQLConnection() throws SQLException {
        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    }

    public void openConnection() throws SQLException {
        if (isConnected) return;

        try {
            chargerProperties();
            connection = DriverManager.getConnection(url, login, mot_de_passe);
            statement = connection.createStatement();
            isConnected = true;
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() throws SQLException {
        if (!isConnected) return;
        connection.close();
        isConnected = false;
    }

    public static Connection getConnection() throws DatabaseException {
        if (!isConnected) {
            throw new DatabaseException("Impossible de récupérer la connexion si l'application n'est pas connectée à la base de données");
        }
        return connection;
    }

    public static Statement getStatement() {
        return statement;
    }


    private void chargerProperties() throws ConfigurationException {

        try {
            Properties properties = new Properties();
            FileInputStream fichierProperties;

            fichierProperties = new FileInputStream("src/resources/sqlconfig.properties");
            properties.load(fichierProperties);

            url = properties.getProperty("SGBD.URL");
            login = properties.getProperty("SGBD.USER");
            mot_de_passe = properties.getProperty("SGBD.MOT_DE_PASSE");

        } catch (IOException e) {
            throw new ConfigurationException("Le fichier sqlconfig.properties est introuvable ou mal configuré");
        }
    }
}
