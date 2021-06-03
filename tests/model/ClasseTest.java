package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sql.SQLConnection;
import utils.CryptUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

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
    @DisplayName("Livre : BDD -> cache")
    public void chargerLivres() {

        initialiserConnexion();
        Livre.chargerLivres();

        System.out.println(Livre.catalogue.size());
//        assertEquals(Livre.catalogue.size(), 101);
    }

    @Test
    @DisplayName("Recherche Livre")
    public void rechercheLivre() {
        chargerLivres();

        System.out.println(Livre.rechercherLivres("Char"));
    }

    @Test
    @DisplayName("Etudiant : BDD -> cache")
    public void chargerEtudiants() {

        initialiserConnexion();
        Etudiant.chargerEtudiants();

        System.out.println(Etudiant.liste.size());
//        assertEquals(Etudiant.liste.values().size(), 4);
    }

    @Test
    @DisplayName("Emprunt : BDD -> cache")
    public void chargerEmprunts() {
        initialiserConnexion();

        Emprunt.chargerEmprunt();
        System.out.println(Emprunt.emprunt.size());
    }

    @Test
    @DisplayName("Réservation : BDD -> cache")
    public void chargerReservations() {
        initialiserConnexion();

        Reservation.chargerReservation();
        System.out.println(Reservation.reservation.size());
    }

    @Test
    @DisplayName("Ajout livre")
    public void ajoutLivre() {
        initialiserConnexion();
        Livre.ajoutLivre("Le portrait de Dorian Gray", new Auteur("Wilde", "Oscar"));
    }

    @Test
    @DisplayName("Suppression livre")
    public void suppressionLivre() {
        initialiserConnexion();
        Livre.suppressionLivre(101);
    }

    @Test
    @DisplayName("Ajout etudiant")
    public void ajoutEtudiant() {
        initialiserConnexion();
        Etudiant.ajoutEtudiant(4, "EMICA", "Samaïna", "sama@gmail.com", "sama");
    }

    @Test
    @DisplayName("Modification etudiant")
    public void modificationEtudiant() {
        initialiserConnexion();
        Etudiant.modificationEtudiant(3, "samaina@gmail.com");
    }

    @Test
    @DisplayName("Suppression etudiant")
    public void suppressionEtudiant() {
        initialiserConnexion();
        chargerEtudiants();
        chargerEmprunts();
        chargerReservations();
        Etudiant.suppressionEtudiant(4);
    }

    @Test
    @DisplayName("Ajout exemplaire")
    public void ajoutExemplaire() {
        initialiserConnexion();
        chargerLivres();
        Exemplaire.ajoutExemplaire(101);
    }

    @Test
    @DisplayName("Suppression exemplaire")
    public void suppressionExemplaire() {
        chargerLivres();
        Exemplaire.suppressionExemplaire(2);
    }

    @Test
    @DisplayName("Ajout emprunt")
    public void ajoutEmprunt() {
        initialiserConnexion();
        chargerEmprunts();
        Emprunt.ajoutEmprunt(new Date(), 4, 2);
    }

    @Test
    @DisplayName("Suppression emprunt")
    public void suppressionEmprunt() {
        initialiserConnexion();
        Emprunt.suppressionEmprunt(new Date(), 4, 2);
    }

    @Test
    @DisplayName("Ajout réservation")
    public void ajoutReservation() {
        initialiserConnexion();
        chargerReservations();
        Reservation.ajoutReservation(new Date(), 4, 101);
    }

    @Test
    @DisplayName("Suppression réservation")
    public void suppressionReservation() {
        initialiserConnexion();
        chargerReservations();
        Reservation.suppressionReservation(new Date(),4, 101);
    }

}
