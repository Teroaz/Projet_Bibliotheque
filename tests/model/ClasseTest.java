package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sql.SQLConnection;

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

        assertEquals(Livre.catalogue.size(), 101);
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

        System.out.println(Etudiant.liste);

        assertEquals(Etudiant.liste.values().size(), 4);
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
        Livre.ajoutLivre(new Livre(101,"Le portrait de Dorian Gray", new Auteur("Wilde", "Oscar")));
    }

    @Test
    @DisplayName("Suppression livre")
    public void suppressionLivre() {
        initialiserConnexion();
        Livre.suppressionLivre(new Livre(101,"Le portrait de Dorian Gray", new Auteur("Wilde", "Oscar")));
    }

    @Test
    @DisplayName("Ajout etudiant")
    public void ajoutEtudiant() {
        initialiserConnexion();
        Etudiant.ajoutEtudiant(new Etudiant(4, "EMICA", "Samaïna", "sama@gmail.com", "sama"));
    }

    @Test
    @DisplayName("Suppression etudiant")
    public void suppressionEtudiant() {
        initialiserConnexion();
        Etudiant.suppressionEtudiant(new Etudiant(4, "EMICA", "Samaïna", "sama@gmail.com", "sama"));
    }

    @Test
    @DisplayName("Ajout exemplaire")
    public void ajoutExemplaire() {
        initialiserConnexion();
        Livre livre = new Livre(101,"Le portrait de Dorian Gray", new Auteur("Wilde", "Oscar"));
        Exemplaire.ajoutExemplaire(new Exemplaire(2,livre,false, Exemplaire.ETAT.NEUF));
    }

    @Test
    @DisplayName("Suppression exemplaire")
    public void suppressionExemplaire() {
        Livre livre = new Livre(101,"Le portrait de Dorian Gray", new Auteur("Wilde", "Oscar"));
        Exemplaire.suppressionExemplaire(new Exemplaire(2,livre,false, Exemplaire.ETAT.NEUF));
    }

    @Test
    @DisplayName("Ajout emprunt")
    public void ajoutEmprunt() {
        initialiserConnexion();
        Etudiant etudiant = new Etudiant(4, "EMICA", "Samaïna", "sama@gmail.com", "sama");
        Exemplaire exemplaire = new Exemplaire(2, Livre.getLivre(101),false, Exemplaire.ETAT.NEUF);
        Emprunt.ajoutEmprunt(new Emprunt(new Date(), etudiant, exemplaire));
    }

    @Test
    @DisplayName("Suppression emprunt")
    public void suppressionEmprunt() {
        Etudiant etudiant = new Etudiant(4, "EMICA", "Samaïna", "sama@gmail.com", "sama");
        Exemplaire exemplaire = new Exemplaire(2, Livre.getLivre(101),false, Exemplaire.ETAT.NEUF);
        Emprunt.suppressionEmprunt(new Emprunt(new Date(), etudiant, exemplaire));
    }

    @Test
    @DisplayName("Ajout réservation")
    public void ajoutReservation() {
        initialiserConnexion();
        Etudiant etudiant = new Etudiant(4, "EMICA", "Samaïna", "sama@gmail.com", "sama");
        Livre livre = new Livre(101,"Le portrait de Dorian Gray", new Auteur("Wilde", "Oscar"));
        Reservation.ajoutReservation(new Reservation(new Date(), etudiant, livre));
    }

    @Test
    @DisplayName("Suppression réservation")
    public void suppressionReservation() {
        Etudiant etudiant = new Etudiant(4, "EMICA", "Samaïna", "sama@gmail.com", "sama");
        Livre livre = new Livre(101,"Le portrait de Dorian Gray", new Auteur("Wilde", "Oscar"));
        Reservation.suppressionReservation(new Reservation(new Date(), etudiant, livre));
    }

    @Test
    @DisplayName("Test script")
    public void test() {
        ajoutEtudiant();
        suppressionEtudiant();
        ajoutExemplaire();
        suppressionExemplaire();
        ajoutEmprunt();
        suppressionEmprunt();
        ajoutReservation();
        suppressionReservation();
    }

}
