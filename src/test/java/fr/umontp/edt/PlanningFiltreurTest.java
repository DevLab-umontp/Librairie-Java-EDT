package fr.umontp.edt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class PlanningFiltreurTest {

    @Test
    void test_PlanningFiltreur_par_date() {
        PlanningFiltreur planningFiltreur = PlanningFiltreur.filtrer().par(LocalDate.now());

        assertEquals(LocalDate.now(), planningFiltreur.getDates()[0]);
    }
}
