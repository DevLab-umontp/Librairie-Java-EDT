package fr.umontp.edt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.Location;

class PlanningTest {

        private Planning planning;

        private ArrayList<Cours> coursEnsemble;

        private Cours cours1 = new Cours(LocalDate.of(2021, 1, 20),
                        new Professeur[] { new Professeur("Dupuis   Jean") }, LocalTime.of(13, 30),
                        LocalTime.of(14, 30), "K133", new Groupe[] { Groupe.S1 }, "Compta1");
        private Cours cours2 = new Cours(LocalDate.of(2021, 1, 21),
                        new Professeur[] { new Professeur("Lemaire   Marie") }, LocalTime.of(14, 30),
                        LocalTime.of(15, 30), "K133", new Groupe[] { Groupe.S3 }, "Compta2");
        private Cours cours3 = new Cours(LocalDate.of(2021, 1, 21),
                        new Professeur[] { new Professeur("Roule   Mathieu") }, LocalTime.of(13, 30),
                        LocalTime.of(14, 30), "K133", new Groupe[] { Groupe.S2 }, "Compta3");
        private Cours cours4 = new Cours(LocalDate.of(2021, 1, 22),
                        new Professeur[] { new Professeur("Lebouche   Alice") }, LocalTime.of(11, 30),
                        LocalTime.of(12, 30), "K133", new Groupe[] { Groupe.S1 }, "Compta4");

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
        void test_getPlanningOf_plusieursDate() {
                assertEquals(2, planning.getPlanningOf(cours4.getDate(), cours1.getDate()).getCours().size());
        }

        @Test
        void test_getPlanningOf_groupe_simpleGroupe() {
                Planning planningS3 = planning.getPlanningOf(Groupe.S3);
                assertEquals(cours2, planningS3.iterator().next());
        }

        @Test
        void test_getPlanningOf_plusieursGroupe_simpleGroupe() {
                Planning planningS3S2 = planning.getPlanningOf(Groupe.S3, Groupe.S2);
                assertEquals(2, planningS3S2.getCours().size());
        }

        @Test
        void test_getPlanningOf_groupe_groupeCompose() {
                Planning planningA1 = planning.getPlanningOf(Groupe.A1);
                assertEquals(0, planningA1.getCours().size());
        }

        @Test
        void test_getPlanningOf_sousGroupeDeA1_doitRetournerLeCoursA1() {
                coursEnsemble.add(new Cours(LocalDate.of(2021, 1, 21),
                                new Professeur[] { new Professeur("prof   prof") }, LocalTime.of(14, 30),
                                LocalTime.of(15, 30), "K133", new Groupe[] { Groupe.A1 }, "Compta4"));
                planning = new Planning(coursEnsemble);
                assertEquals(1, planning.getPlanningOf(Groupe.S4).getCours().size());
        }

        @Test
        void test_getPlanningOf_groupe_groupeComposeVide() {
                Planning planningA2 = planning.getPlanningOf(Groupe.A2);
                assertFalse(planningA2.iterator().hasNext());
        }

        @Test
        void test_getPlanningOf_date_and_groupe() {
                assertNotEquals(cours2, planning.getPlanningOf(cours2.getDate()).iterator().next());
                assertEquals(cours2,
                                planning.getPlanningOf(cours2.getDate(), cours2.getGroupes()[0]).iterator().next());
        }

        @Test
        void test_constructeur_Planning_casPlusieursCoursCommenceEnMemeTemps_neDoitSupprimerAucunCours() {
                coursEnsemble.add(new Cours(LocalDate.of(2021, 1, 21),
                                new Professeur[] { new Professeur("prof   prof") }, LocalTime.of(14, 30),
                                LocalTime.of(15, 30), "K133", new Groupe[] { Groupe.S4 }, "Compta4"));
                planning = new Planning(coursEnsemble);
                assertEquals(coursEnsemble.size(), planning.getCours().size());
        }

        @Test
        void test_getPlanningOf_groupe_viaComponentCalendar_doitRetournerPlusieursGroupe() {
                Planning planning = createPlanningViaComponent();

                assertTrue(planning.getPlanningOf(Groupe.A1).iterator().hasNext());
                assertTrue(planning.getPlanningOf(Groupe.A2).iterator().hasNext());
        }

        private Planning createPlanningViaComponent() {
                Component component;
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
                                "\n\nA2-Semestre-3 A1\nBELMECHERI   NASSIM\nHAETTEL   THOMAS\nLebouche   Alice\nLA   XUAN HOANG\nCHIROUZE   ANNE\nA valider\n(Exporté le:18/01/2021 10:51)\n"));
                component.getProperties().add(new Location("K133"));
                Cours cours = new Cours(component);
                ArrayList<Cours> coursList = new ArrayList<>();
                coursList.add(cours);
                Planning planning = new Planning(coursList);
                return planning;
        }

        @Test
        void test_getPlanningOf_professeur_retournUnCours() {
                Planning planning = createPlanningViaComponent();
                Planning planningAlice = planning.getPlanningOf(RepertoireProfesseur.get("LEBOUCHE", "Alice"));
                assertTrue(planningAlice.iterator().hasNext());
        }

        @Test
        void test_getPlanningOf_plusieurProfesseurs_retournUnCours() {
                Planning planning = createPlanningViaComponent();
                Planning planningAlice = planning.getPlanningOf(RepertoireProfesseur.get("LEBOUCHE", "Alice"),
                                RepertoireProfesseur.get("BELMECHERI", "NASSIM"));
                assertEquals(1, planningAlice.getCours().size());
        }

        @Test
        void test_getPlanningOf_PlanningFiltreur_parDate() {
                assertEquals(cours4, planning.getPlanningOf(PlanningFiltreur.filtrer().par(cours4.getDate())).iterator()
                                .next());
        }

        @Test
        void test_getPlanningOf_PlanningFiltreur_parGroupe() {
                assertEquals(cours2, planning.getPlanningOf(PlanningFiltreur.filtrer().par(cours2.getGroupes()))
                                .iterator().next());
        }

        @Test
        void test_getPlanningOf_PlanningFiltreur_parProfesseur() {
                assertEquals(cours2, planning.getPlanningOf(PlanningFiltreur.filtrer().par(cours2.getProfesseurs()))
                                .iterator().next());
        }

        @Test
        void test_getPlanningOf_PlanningFiltreur_parDateEtGroupe() {
                assertEquals(1, planning.getPlanningOf(
                                PlanningFiltreur.filtrer().par(LocalDate.of(2021, 1, 21)).par(cours2.getGroupes()))
                                .getCours().size());
        }

        @Test
        void test_getPlanningOf_PlanningFiltreur_parDateEtProfesseur() {
                assertEquals(1, planning.getPlanningOf(
                                PlanningFiltreur.filtrer().par(LocalDate.of(2021, 1, 21)).par(cours2.getProfesseurs()))
                                .getCours().size());
        }

        @Test
        void test_getPlanningOf_PlanningFiltreur_parGroupeEtProfesseur() {
                assertEquals(1, planning
                                .getPlanningOf(PlanningFiltreur.filtrer().par(Groupe.S1).par(cours4.getProfesseurs()))
                                .getCours().size());
        }
}
