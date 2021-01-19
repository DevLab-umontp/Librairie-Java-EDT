package edt.umontp.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlanningTest {

        private Planning planning;

        private ArrayList<Cours> coursEnsemble;

        private Cours cours1 = new Cours(LocalDate.of(2021, 1, 20), new String[] { "prof" }, LocalTime.of(13, 30),
                        LocalTime.of(14, 30), "K133", Groupe.S1, "Compta1");
        private Cours cours2 = new Cours(LocalDate.of(2021, 1, 21), new String[] { "prof" }, LocalTime.of(14, 30),
                        LocalTime.of(15, 30), "K133", Groupe.S3, "Compta2");
        private Cours cours3 = new Cours(LocalDate.of(2021, 1, 21), new String[] { "prof" }, LocalTime.of(13, 30),
                        LocalTime.of(14, 30), "K133", Groupe.S2, "Compta3");
        private Cours cours4 = new Cours(LocalDate.of(2021, 1, 22), new String[] { "prof" }, LocalTime.of(11, 30),
                        LocalTime.of(12, 30), "K133", Groupe.S1, "Compta4");

        @BeforeEach
        void initPlanning() {
                coursEnsemble = new ArrayList<>();
                coursEnsemble.add(cours1);
                coursEnsemble.add(cours2);
                coursEnsemble.add(cours3);
                coursEnsemble.add(cours4);
                planning = new Planning(coursEnsemble);
        }

        @Test
        void test_getPlanningOf_date() {
                assertEquals(cours4, planning.getPlanningOf(cours4.getDate()).iterator().next());
        }

        @Test
        void test_getPlanningOf_groupe_simpleGroupe() {
                Planning planningA1 = planning.getPlanningOf(Groupe.S3);
                assertEquals(cours2, planningA1.iterator().next());
        }

        @Test
        void test_getPlanningOf_groupe_groupeCompose() {
                Planning planningA1 = planning.getPlanningOf(Groupe.A1);
                for (Cours cours : planningA1)
                        assertTrue(coursEnsemble.contains(cours));
        }

        @Test
        void test_getPlanningOf_groupe_groupeComposeVide() {
                Planning planningA2 = planning.getPlanningOf(Groupe.A2);
                assertFalse(planningA2.iterator().hasNext());
        }

        @Test
        void test_getPlanningOf_date_and_groupe() {
                assertNotEquals(cours2, planning.getPlanningOf(cours2.getDate()).iterator().next());
                assertEquals(cours2, planning.getPlanningOf(cours2.getDate(), cours2.getGroupe()).iterator().next());
        }

}
