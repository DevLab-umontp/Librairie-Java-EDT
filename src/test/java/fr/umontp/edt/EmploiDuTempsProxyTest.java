package fr.umontp.edt;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class EmploiDuTempsProxyTest {
    // Attention les tests de cette classe ne peuvent être executé que dans un
    // environement stable comme le runner Github Actions
    private EmploiDuTempsProxy emploiDuTempsProxy;

    @AfterEach
    void init() {
        emploiDuTempsProxy.actualiser();
    }

    @Test
    void test_getInstance_retourneAucuneErreur() {
        assertAll(() -> emploiDuTempsProxy = EmploiDuTempsProxy.getInstance());
    }

    @Test
    void test_getPlanningOf_date_plusRapide() {
        emploiDuTempsProxy = EmploiDuTempsProxy.getInstance();

        long tempsExecution1 = rapiditeGetPlanningOfDate();
        long tempsExecution2 = rapiditeGetPlanningOfDate();

        assertTrue(tempsExecution1 > tempsExecution2);
    }

    private long rapiditeGetPlanningOfDate() {
        long startTime = System.currentTimeMillis();
        emploiDuTempsProxy.getPlanningOf(LocalDate.now());
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(1));
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(2));
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(3));
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(4));
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(5));
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(6));
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(7));
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(8));
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(9));
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(10));
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(11));
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(12));
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(13));
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(14));
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(15));
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(16));
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(17));
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(18));
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(19));

        return System.currentTimeMillis() - startTime;
    }

    @Test
    void test_getPlanningOf_plusieursDates_plusRapideInteligent() {
        emploiDuTempsProxy = EmploiDuTempsProxy.getInstance();

        long tempsExecution1 = rapiditeGetPlanningOfDate();
        long tempsExecution2 = rapiditeGetPlanningOfDateBrut();

        assertTrue(tempsExecution1 > tempsExecution2);
    }

    private long rapiditeGetPlanningOfDateBrut() {
        long startTime = System.currentTimeMillis();
        emploiDuTempsProxy.getPlanningOf(LocalDate.now(), LocalDate.now().plusDays(1), LocalDate.now().plusDays(2),
                LocalDate.now().plusDays(3), LocalDate.now().plusDays(4), LocalDate.now().plusDays(5),
                LocalDate.now().plusDays(6), LocalDate.now().plusDays(7), LocalDate.now().plusDays(8),
                LocalDate.now().plusDays(9), LocalDate.now().plusDays(10), LocalDate.now().plusDays(11),
                LocalDate.now().plusDays(12), LocalDate.now().plusDays(13), LocalDate.now().plusDays(14),
                LocalDate.now().plusDays(15), LocalDate.now().plusDays(16), LocalDate.now().plusDays(17),
                LocalDate.now().plusDays(18), LocalDate.now().plusDays(19));
        return System.currentTimeMillis() - startTime;
    }

    @Test
    void test_getPlanningOf_Groupe_plusRapide() {
        emploiDuTempsProxy = EmploiDuTempsProxy.getInstance();

        long tempsExecution1 = rapiditeGetPlanningOfGroupe();
        long tempsExecution2 = rapiditeGetPlanningOfGroupe();

        assertTrue(tempsExecution1 > tempsExecution2);
    }

    private long rapiditeGetPlanningOfGroupe() {
        long startTime = System.currentTimeMillis();
        emploiDuTempsProxy.getPlanningOf(Groupe.S1);
        emploiDuTempsProxy.getPlanningOf(Groupe.S2);
        emploiDuTempsProxy.getPlanningOf(Groupe.S3);
        emploiDuTempsProxy.getPlanningOf(Groupe.S4);
        emploiDuTempsProxy.getPlanningOf(Groupe.A1);
        emploiDuTempsProxy.getPlanningOf(Groupe.Q1);
        emploiDuTempsProxy.getPlanningOf(Groupe.Q2);
        emploiDuTempsProxy.getPlanningOf(Groupe.Q3);
        emploiDuTempsProxy.getPlanningOf(Groupe.Q4);
        emploiDuTempsProxy.getPlanningOf(Groupe.G1);
        emploiDuTempsProxy.getPlanningOf(Groupe.G2);
        emploiDuTempsProxy.getPlanningOf(Groupe.G3);
        emploiDuTempsProxy.getPlanningOf(Groupe.G4);
        emploiDuTempsProxy.getPlanningOf(Groupe.A2);
        return System.currentTimeMillis() - startTime;
    }

    @Test
    void test_getPlanningOf_Groupe_plusRapideInteligent() {
        emploiDuTempsProxy = EmploiDuTempsProxy.getInstance();

        long tempsExecution1 = rapiditeGetPlanningOfGroupe();
        long tempsExecution2 = rapiditeGetPlanningOfGroupeBrut();

        assertTrue(tempsExecution1 > tempsExecution2);
    }

    private long rapiditeGetPlanningOfGroupeBrut() {
        long startTime = System.currentTimeMillis();
        emploiDuTempsProxy.getPlanningOf(Groupe.S1, Groupe.S2, Groupe.S3, Groupe.S4, Groupe.A1, Groupe.Q1, Groupe.Q2,
                Groupe.Q3, Groupe.Q4, Groupe.G1, Groupe.G2, Groupe.G3, Groupe.G4, Groupe.A2);
        return System.currentTimeMillis() - startTime;
    }

    @Test
    void test_getPlanningOf_DateGroupe_plusRapide() {
        emploiDuTempsProxy = EmploiDuTempsProxy.getInstance();

        long tempsExecution1 = rapiditeGetPlanningOfDateGroupe();
        long tempsExecution2 = rapiditeGetPlanningOfDateGroupe();

        assertTrue(tempsExecution1 > tempsExecution2);
    }

    private long rapiditeGetPlanningOfDateGroupe() {
        long startTime = System.currentTimeMillis();
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(1), Groupe.A1);
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(2), Groupe.Q1);
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(3), Groupe.Q2);
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(4), Groupe.Q3);
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(5), Groupe.Q4);
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(6), Groupe.G1);
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(7), Groupe.G2);
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(8), Groupe.G3);
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(9), Groupe.G4);
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(10), Groupe.A2);
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(11), Groupe.A1);
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(12), Groupe.Q1);
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(13), Groupe.Q2);
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(14), Groupe.Q3);
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(15), Groupe.Q4);
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(16), Groupe.G1);
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(17), Groupe.G2);
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(18), Groupe.G3);
        emploiDuTempsProxy.getPlanningOf(LocalDate.now().plusDays(19), Groupe.G4);
        return System.currentTimeMillis() - startTime;
    }

    @Test
    void test_getPlanningOf_DateGroupe_plusRapideInteligent() {
        emploiDuTempsProxy = EmploiDuTempsProxy.getInstance();

        long tempsExecution1 = rapiditeGetPlanningOfDateGroupe();
        emploiDuTempsProxy.actualiser();
        rapiditeGetPlanningOfDate();
        long tempsExecution2 = rapiditeGetPlanningOfDateGroupe();

        assertTrue(tempsExecution1 > tempsExecution2);
    }

    @Test
    void test_getPlanningOf_Professeur_plusRapide() {
        emploiDuTempsProxy = EmploiDuTempsProxy.getInstance();

        long tempsExecution1 = rapiditeGetPlanningOfProfesseur();
        long tempsExecution2 = rapiditeGetPlanningOfProfesseur();

        assertTrue(tempsExecution1 > tempsExecution2);
    }

    private long rapiditeGetPlanningOfProfesseur() {
        long startTime = System.currentTimeMillis();
        emploiDuTempsProxy.getPlanningOf(RepertoireProfesseur.get("Marie-Jeanne", "Alain"));
        emploiDuTempsProxy.getPlanningOf(RepertoireProfesseur.get("Valicov", "Petru"));
        emploiDuTempsProxy.getPlanningOf(RepertoireProfesseur.get("Bougeret", "Marin"));
        emploiDuTempsProxy.getPlanningOf(RepertoireProfesseur.get("Garcia", "Francis"));
        emploiDuTempsProxy.getPlanningOf(RepertoireProfesseur.get("Lazaar", "Nadjib"));
        emploiDuTempsProxy.getPlanningOf(RepertoireProfesseur.get("lebreton", "romain"));
        emploiDuTempsProxy.getPlanningOf(RepertoireProfesseur.get("messaoui", "anita"));
        emploiDuTempsProxy.getPlanningOf(RepertoireProfesseur.get("Gouaich", "Abdelkader"));
        emploiDuTempsProxy.getPlanningOf(RepertoireProfesseur.get("Chollet", "Antoine"));
        emploiDuTempsProxy.getPlanningOf(RepertoireProfesseur.get("Ouherrou", "Nihal"));
        emploiDuTempsProxy.getPlanningOf(RepertoireProfesseur.get("Rosenfeld", "Matthieu"));
        emploiDuTempsProxy.getPlanningOf(RepertoireProfesseur.get("Chirouze", "Anne"));
        emploiDuTempsProxy.getPlanningOf(RepertoireProfesseur.get("Weber", "Marie-Laure"));
        emploiDuTempsProxy.getPlanningOf(RepertoireProfesseur.get("Palleja", "Nathalie"));
        emploiDuTempsProxy.getPlanningOf(RepertoireProfesseur.get("Molnar", "Miklos"));
        emploiDuTempsProxy.getPlanningOf(RepertoireProfesseur.get("Delebarre", "Justine"));
        emploiDuTempsProxy.getPlanningOf(RepertoireProfesseur.get("Nabitz", "Sophie"));
        emploiDuTempsProxy.getPlanningOf(RepertoireProfesseur.get("Poupet", "Victor"));
        emploiDuTempsProxy.getPlanningOf(RepertoireProfesseur.get("Coletta", "Rémi"));
        return System.currentTimeMillis() - startTime;
    }

    @Test
    void test_getPlanningOf_Professeur_plusRapideInteligent() {
        emploiDuTempsProxy = EmploiDuTempsProxy.getInstance();

        long tempsExecution1 = rapiditeGetPlanningOfProfesseur();
        long tempsExecution2 = rapiditeGetPlanningOfProfesseurBrut();

        assertTrue(tempsExecution1 > tempsExecution2);
    }

    private long rapiditeGetPlanningOfProfesseurBrut() {
        long startTime = System.currentTimeMillis();
        emploiDuTempsProxy.getPlanningOf(RepertoireProfesseur.get("Marie-Jeanne", "Alain"),
                RepertoireProfesseur.get("Valicov", "Petru"), RepertoireProfesseur.get("Bougeret", "Marin"),
                RepertoireProfesseur.get("Garcia", "Francis"), RepertoireProfesseur.get("Lazaar", "Nadjib"),
                RepertoireProfesseur.get("lebreton", "romain"), RepertoireProfesseur.get("messaoui", "anita"),
                RepertoireProfesseur.get("Gouaich", "Abdelkader"), RepertoireProfesseur.get("Chollet", "Antoine"),
                RepertoireProfesseur.get("Ouherrou", "Nihal"), RepertoireProfesseur.get("Rosenfeld", "Matthieu"),
                RepertoireProfesseur.get("Chirouze", "Anne"), RepertoireProfesseur.get("Weber", "Marie-Laure"),
                RepertoireProfesseur.get("Palleja", "Nathalie"), RepertoireProfesseur.get("Molnar", "Miklos"),
                RepertoireProfesseur.get("Delebarre", "Justine"), RepertoireProfesseur.get("Nabitz", "Sophie"),
                RepertoireProfesseur.get("Poupet", "Victor"), RepertoireProfesseur.get("Coletta", "Rémi"));
        return System.currentTimeMillis() - startTime;
    }

    @Test
    void test_getPlanningOf_ProfesseurAvecPlanningFiltreur_plusRapide() {
        emploiDuTempsProxy = EmploiDuTempsProxy.getInstance();

        long tempsExecution1 = rapiditeGetPlanningOfProfesseurAvecPlanningFiltreur();
        long tempsExecution2 = rapiditeGetPlanningOfProfesseurAvecPlanningFiltreur();

        assertTrue(tempsExecution1 > tempsExecution2);
    }

    private long rapiditeGetPlanningOfProfesseurAvecPlanningFiltreur() {
        long startTime = System.currentTimeMillis();
        emploiDuTempsProxy
                .getPlanningOf(PlanningFiltreur.filtrer().par(RepertoireProfesseur.get("Marie-Jeanne", "Alain")));
        emploiDuTempsProxy.getPlanningOf(PlanningFiltreur.filtrer().par(RepertoireProfesseur.get("Valicov", "Petru")));
        emploiDuTempsProxy.getPlanningOf(PlanningFiltreur.filtrer().par(RepertoireProfesseur.get("Bougeret", "Marin")));
        emploiDuTempsProxy.getPlanningOf(PlanningFiltreur.filtrer().par(RepertoireProfesseur.get("Garcia", "Francis")));
        emploiDuTempsProxy.getPlanningOf(PlanningFiltreur.filtrer().par(RepertoireProfesseur.get("Lazaar", "Nadjib")));
        emploiDuTempsProxy
                .getPlanningOf(PlanningFiltreur.filtrer().par(RepertoireProfesseur.get("lebreton", "romain")));
        emploiDuTempsProxy.getPlanningOf(PlanningFiltreur.filtrer().par(RepertoireProfesseur.get("messaoui", "anita")));
        emploiDuTempsProxy
                .getPlanningOf(PlanningFiltreur.filtrer().par(RepertoireProfesseur.get("Gouaich", "Abdelkader")));
        emploiDuTempsProxy
                .getPlanningOf(PlanningFiltreur.filtrer().par(RepertoireProfesseur.get("Chollet", "Antoine")));
        emploiDuTempsProxy.getPlanningOf(PlanningFiltreur.filtrer().par(RepertoireProfesseur.get("Ouherrou", "Nihal")));
        emploiDuTempsProxy
                .getPlanningOf(PlanningFiltreur.filtrer().par(RepertoireProfesseur.get("Rosenfeld", "Matthieu")));
        emploiDuTempsProxy.getPlanningOf(PlanningFiltreur.filtrer().par(RepertoireProfesseur.get("Chirouze", "Anne")));
        emploiDuTempsProxy
                .getPlanningOf(PlanningFiltreur.filtrer().par(RepertoireProfesseur.get("Weber", "Marie-Laure")));
        emploiDuTempsProxy
                .getPlanningOf(PlanningFiltreur.filtrer().par(RepertoireProfesseur.get("Palleja", "Nathalie")));
        emploiDuTempsProxy.getPlanningOf(PlanningFiltreur.filtrer().par(RepertoireProfesseur.get("Molnar", "Miklos")));
        emploiDuTempsProxy
                .getPlanningOf(PlanningFiltreur.filtrer().par(RepertoireProfesseur.get("Delebarre", "Justine")));
        emploiDuTempsProxy.getPlanningOf(PlanningFiltreur.filtrer().par(RepertoireProfesseur.get("Nabitz", "Sophie")));
        emploiDuTempsProxy.getPlanningOf(PlanningFiltreur.filtrer().par(RepertoireProfesseur.get("Poupet", "Victor")));
        emploiDuTempsProxy.getPlanningOf(PlanningFiltreur.filtrer().par(RepertoireProfesseur.get("Coletta", "Rémi")));
        return System.currentTimeMillis() - startTime;
    }

}
