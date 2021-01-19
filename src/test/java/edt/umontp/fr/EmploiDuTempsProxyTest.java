package edt.umontp.fr;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class EmploiDuTempsProxyTest {

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

}
