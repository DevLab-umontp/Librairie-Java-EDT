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

}
