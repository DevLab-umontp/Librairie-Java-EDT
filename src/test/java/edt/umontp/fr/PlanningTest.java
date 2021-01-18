package edt.umontp.fr;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlanningTest {

        private Planning planning;

        private Cours cours1 = new Cours(LocalDate.of(2021, 1, 20), new String[] { "prof" }, LocalTime.of(13, 30),
                        LocalTime.of(14, 30), "K133", Groupe.S1, "Compta1");
        private Cours cours2 = new Cours(LocalDate.of(2021, 1, 21), new String[] { "prof" }, LocalTime.of(14, 30),
                        LocalTime.of(15, 30), "K133", Groupe.S3, "Compta2");
        private Cours cours3 = new Cours(LocalDate.of(2021, 1, 21), new String[] { "prof" }, LocalTime.of(14, 30),
                        LocalTime.of(14, 30), "K133", Groupe.S2, "Compta3");
        private Cours cours4 = new Cours(LocalDate.of(2021, 1, 22), new String[] { "prof" }, LocalTime.of(11, 30),
                        LocalTime.of(12, 30), "K133", Groupe.S1, "Compta4");

        @BeforeEach
        void initPlanning() {
                ArrayList<Cours> cours = new ArrayList<>();
                cours.add(cours1);
                cours.add(cours2);
                cours.add(cours3);
                cours.add(cours4);
                planning = new Planning(cours);
        }

        @Test
        void test_getPlanningOf_date() {
                assertEquals(cours4, planning.getPlanningOf(cours4.getDate()).iterator().next());
        }

        @Test
        void test_getPlanningOf_groupe(){
                assertEquals(cours2, planning.getPlanningOf(Groupe.S3).iterator().next());
        }

}
