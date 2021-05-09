package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sql.SQLConnection;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLivre {

    @Test
    @DisplayName("BDD -> cache")
    public void chargerLivres() {
        SQLConnection sqlConnection;

        try {
            sqlConnection = new SQLConnection();
            sqlConnection.openConnection();
        } catch (SQLException err) {
            err.printStackTrace();
            return;
        }

        Livre.chargerLivres();

        assertEquals(Livre.catalogue.size(), 100);
    }
}
