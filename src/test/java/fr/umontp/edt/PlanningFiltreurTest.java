package fr.umontp.edt;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class PlanningFiltreurTest {

    @Test
    void test_PlanningFiltreur_par_date() {
        PlanningFiltreur planningFiltreur = PlanningFiltreur.filtrer().par(LocalDate.now());

        assertEquals(LocalDate.now(), planningFiltreur.getDates()[0]);
    }

    @Test
    void test_PlanningFiltreur_par_plusieursDates() {
        PlanningFiltreur planningFiltreur = PlanningFiltreur.filtrer().par(LocalDate.now(), LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2));
        LocalDate[] excepted = { LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now().plusDays(2) };
        assertArrayEquals(excepted, planningFiltreur.getDates());
    }

    @Test
    void test_PlanningFiltreur_par_groupe() {
        PlanningFiltreur planningFiltreur = PlanningFiltreur.filtrer().par(Groupe.S1);
        assertEquals(Groupe.S1, planningFiltreur.getGroupes()[0]);
    }

    @Test
    void test_PlanningFiltreur_par_plusieursGroupes() {
        PlanningFiltreur planningFiltreur = PlanningFiltreur.filtrer().par(Groupe.S1, Groupe.S3, Groupe.S4);
        Groupe[] excepted = { Groupe.S1, Groupe.S3, Groupe.S4 };
        assertArrayEquals(excepted, planningFiltreur.getGroupes());
    }

    @Test
    void test_PlanningFiltreur_par_Professeur() {
        Professeur professeur = new Professeur("Duipuis   Jean");
        PlanningFiltreur planningFiltreur = PlanningFiltreur.filtrer().par(professeur);
        assertEquals(professeur, planningFiltreur.getProfesseurs()[0]);
    }

}
