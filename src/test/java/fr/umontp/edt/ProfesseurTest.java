package fr.umontp.edt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ProfesseurTest {

    @Test
    void testConstructeur_UnStrings_prenomNomCombine() {
        Professeur professeur = new Professeur("Dupuis   Jean");
        assertEquals("Dupuis", professeur.getNom());
        assertEquals("Jean", professeur.getPrenom());
    }

    @Test
    void testEquals_deuxProfesseursIdentiqueAvecConstructeurDifferent_avecDifferenceMajuscule() {
        Professeur professeur1 = new Professeur("DUpuis   JeAN");
        Professeur professeur2 = new Professeur("DupUis   JEan");
        assertEquals(professeur1, professeur2);
    }

    @Test
    void testEquals_deuxProfesseursIdentiqueAvecConstructeurDifferent_avecDifferenceCaractereSpeciaux() {
        Professeur professeur1 = new Professeur("prés-dupuis   Jean");
        Professeur professeur2 = new Professeur("pres-dupuis   Jean");
        assertEquals(professeur1, professeur2);
    }

    @Test
    void testEquals_deuxProfesseursIdentiqueAvecConstructeurDifferent_avecDifferenceEntreEspaceEtCaractereSpeciaux() {
        Professeur professeur1 = new Professeur("D'Ôrge   Jean");
        Professeur professeur2 = new Professeur("DÔrge   Jean");
        assertEquals(professeur1, professeur2);
    }

}
