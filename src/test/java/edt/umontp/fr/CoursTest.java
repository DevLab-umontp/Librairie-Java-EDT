package edt.umontp.fr;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.Location;

class CoursTest {

    @Nested
    class contructeur_test {
        private Component component;

        @BeforeEach
        void init_component() {
            DateTime startTime, endTime;
            try {
                startTime = new DateTime("20171127T150000");
                endTime = new DateTime("20171127T160000");
            } catch (ParseException pe) {
                // yyyymmddTHHmmss is the correct format, but to make the compiler happy...
                startTime = new DateTime();
                endTime = startTime;
            }
            component = new VEvent(startTime, endTime, "intitule");
            component.getProperties().add(new Description(
                    "\n\nA2-Semestre-3\nBELMECHERI   NASSIM\nHAETTEL   THOMAS\nLA   XUAN HOANG\nCHIROUZE   ANNE\nA valider\n(Exporté le:18/01/2021 10:51)\n"));
            component.getProperties().add(new Location("K133"));
        }

        @Test
        void test_constructeur_VEvent() {
            assertAll(() -> new Cours(component));
        }

        @Test
        void test_constructeur_VEvent_dateBon() {
            Cours cours = new Cours(component);
            assertEquals(LocalDate.of(2017, 11, 27), cours.getDate());
        }

        @Test
        void test_constructeur_VEvent_heureDebut_Bon() {
            Cours cours = new Cours(component);
            assertEquals(LocalTime.of(15, 00), cours.getHeureDebut());
        }

        @Test
        void test_constructeur_VEvent_heureFin_Bon() {
            Cours cours = new Cours(component);
            assertEquals(LocalTime.of(16, 00), cours.getHeureFin());
        }

        @Test
        void test_constructeur_VEvent_duree_Bon() {
            Cours cours = new Cours(component);
            assertEquals(60, cours.getDuree());
        }

        @Test
        void test_constructeur_VEvent_groupe_Bon() {
            Cours cours = new Cours(component);
            assertEquals(Groupe.A2, cours.getGroupe());
        }

        @Test
        void test_constructeur_VEvent_profs_Bon() {
            String[] expected = { "BELMECHERI   NASSIM", "HAETTEL   THOMAS", "LA   XUAN HOANG", "CHIROUZE   ANNE" };
            Cours cours = new Cours(component);
            assertEquals(Arrays.toString(expected), Arrays.toString(cours.getProf()));
        }

        @Test
        void test_constructeur_VEvent_lieu_Bon() {
            Cours cours = new Cours(component);
            assertEquals("K133", cours.getLieu());
        }

        @Test
        void test_constructeur_VEvent_intitule_Bon() {
            Cours cours = new Cours(component);
            assertEquals("intitule", cours.getIntitule());
        }
    }

    @Test
    void test_getProfFromDdesc_Desc_NomsDesProfs() {
        String desc = "\n\nA2-Semestre-3\nBELMECHERI   NASSIM\nHAETTEL   THOMAS\nLA   XUAN HOANG\nCHIROUZE   ANNE\nA valider\n(Exporté le:18/01/2021 10:51)\n";
        String[] expected = { "BELMECHERI   NASSIM", "HAETTEL   THOMAS", "LA   XUAN HOANG", "CHIROUZE   ANNE" };
        assertEquals(Arrays.toString(expected), Arrays.toString(Cours.getProfFromDesc(desc)));
    }
}