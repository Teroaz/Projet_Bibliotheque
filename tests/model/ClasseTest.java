package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sql.SQLConnection;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ClasseTest {

    @Test
    @DisplayName("Connexion DB")
    public SQLConnection initialiserConnexion() {
        SQLConnection sqlConnection = null;

        try {
            sqlConnection = new SQLConnection();
            sqlConnection.openConnection();
        } catch (SQLException err) {
            err.printStackTrace();
        }

        assertNotNull(sqlConnection);

        return sqlConnection;
    }

    @Test
    @DisplayName("BDD -> cache")
    public void chargerLivres() {

        initialiserConnexion();
        Livre.chargerLivres();

        assertEquals(Livre.catalogue.size(), 100);
    }

    @Test
    @DisplayName("Recherche Livre")
    public void rechercheLivre() {
        chargerLivres();

        System.out.println(Livre.rechercherLivres("Char"));
    }
}
