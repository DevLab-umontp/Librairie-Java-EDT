package fr.umontp.edt;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

@SuppressWarnings("deprecation")
class EmploiDuTempsTest {

    @Test
    void test_getInstance_verifierRetourneAucuneErreur() {
        assertAll(() -> EmploiDuTemps.getInstance());
    }

    @Test
    void test_getPlanningOf_date_verifierRetourneAucuneErreur() {
        EmploiDuTemps emploiDuTemps = EmploiDuTemps.getInstance();
        assertAll(() -> emploiDuTemps.getPlanningOf(LocalDate.now()));
    }

    @Test
    void test_getPlanningOf_groupe_verifierRetourneAucuneErreur() {
        EmploiDuTemps emploiDuTemps = EmploiDuTemps.getInstance();
        assertAll(() -> emploiDuTemps.getPlanningOf(Groupe.A1));
    }

    @Test
    void test_getPlanningOf_groupe_verifierRetournePasDeListeVide() {
        EmploiDuTemps emploiDuTemps = EmploiDuTemps.getInstance();
        assertTrue(emploiDuTemps.getPlanningOf(Groupe.A1).iterator().hasNext());
    }

    @Test
    void test_getPlanningOf_professeur_verifierRetourneAucuneErreur() {
        EmploiDuTemps emploiDuTemps = EmploiDuTemps.getInstance();
        assertAll(() -> emploiDuTemps.getPlanningOf(RepertoireProfesseur.get("Marie-jeanne", "Alain")));
    }

    @Test
    void test_getPlanningOf_plusieursProfesseurs_verifierRetourneAucuneErreur() {
        EmploiDuTemps emploiDuTemps = EmploiDuTemps.getInstance();
        assertAll(() -> emploiDuTemps.getPlanningOf(RepertoireProfesseur.get("Marie-jeanne", "Alain"),
                RepertoireProfesseur.get("messaoui", "anita")));
    }

    @Test
    void test_getPlanningOf_professeur_verifierRetournePasDeListeVide() {
        EmploiDuTemps emploiDuTemps = EmploiDuTemps.getInstance();
        assertTrue(emploiDuTemps.getPlanningOf(RepertoireProfesseur.get("Marie-jeanne", "Alain")).iterator().hasNext());
    }
}
