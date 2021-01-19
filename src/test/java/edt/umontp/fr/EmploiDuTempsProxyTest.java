package edt.umontp.fr;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class EmploiDuTempsProxyTest {

    private EmploiDuTempsProxy emploiDuTempsProxyTest;

    @AfterEach
    void init() {
        emploiDuTempsProxyTest.actualiser();
    }

    @Test
    void test_getInstance_retourneAucuneErreur() {
        assertAll(() -> EmploiDuTempsProxy.getInstance());
    }

    @Test
    void test_getPlanningOf_date_plusRapide() {
        emploiDuTempsProxyTest = EmploiDuTempsProxy.getInstance();

        long tempsExecution1 = rapiditeGetPlanningOfDate();
        long tempsExecution2 = rapiditeGetPlanningOfDate();

        assertTrue(tempsExecution1 > tempsExecution2);
    }

    private long rapiditeGetPlanningOfDate() {
        long startTime = System.currentTimeMillis();
        emploiDuTempsProxyTest.getPlanningOf(LocalDate.now());
        emploiDuTempsProxyTest.getPlanningOf(LocalDate.now().plusDays(1));
        emploiDuTempsProxyTest.getPlanningOf(LocalDate.now().plusDays(2));
        emploiDuTempsProxyTest.getPlanningOf(LocalDate.now().plusDays(3));
        emploiDuTempsProxyTest.getPlanningOf(LocalDate.now().plusDays(4));
        emploiDuTempsProxyTest.getPlanningOf(LocalDate.now().plusDays(5));
        emploiDuTempsProxyTest.getPlanningOf(LocalDate.now().plusDays(6));
        emploiDuTempsProxyTest.getPlanningOf(LocalDate.now().plusDays(7));
        emploiDuTempsProxyTest.getPlanningOf(LocalDate.now().plusDays(8));
        emploiDuTempsProxyTest.getPlanningOf(LocalDate.now().plusDays(9));
        emploiDuTempsProxyTest.getPlanningOf(LocalDate.now().plusDays(10));
        return System.currentTimeMillis() - startTime;
    }

    @Test
    void test_getPlanningOf_Groupe_plusRapide() {
        emploiDuTempsProxyTest = EmploiDuTempsProxy.getInstance();

        long tempsExecution1 = rapiditeGetPlanningOfGroupe();
        long tempsExecution2 = rapiditeGetPlanningOfGroupe();

        assertTrue(tempsExecution1 > tempsExecution2);
    }

    private long rapiditeGetPlanningOfGroupe() {
        long startTime = System.currentTimeMillis();
        emploiDuTempsProxyTest.getPlanningOf(Groupe.A1);
        emploiDuTempsProxyTest.getPlanningOf(Groupe.Q1);
        emploiDuTempsProxyTest.getPlanningOf(Groupe.Q2);
        emploiDuTempsProxyTest.getPlanningOf(Groupe.Q3);
        emploiDuTempsProxyTest.getPlanningOf(Groupe.Q4);
        emploiDuTempsProxyTest.getPlanningOf(Groupe.G1);
        emploiDuTempsProxyTest.getPlanningOf(Groupe.G2);
        emploiDuTempsProxyTest.getPlanningOf(Groupe.G3);
        emploiDuTempsProxyTest.getPlanningOf(Groupe.G4);
        emploiDuTempsProxyTest.getPlanningOf(Groupe.A2);
        return System.currentTimeMillis() - startTime;
    }

    @Test
    void test_getPlanningOf_DateGroupe_plusRapide() {
        emploiDuTempsProxyTest = EmploiDuTempsProxy.getInstance();

        long tempsExecution1 = rapiditeGetPlanningOfDateGroupe();
        long tempsExecution2 = rapiditeGetPlanningOfDateGroupe();

        assertTrue(tempsExecution1 > tempsExecution2);
    }

    private long rapiditeGetPlanningOfDateGroupe() {
        long startTime = System.currentTimeMillis();
        emploiDuTempsProxyTest.getPlanningOf(LocalDate.now().plusDays(1), Groupe.A1);
        emploiDuTempsProxyTest.getPlanningOf(LocalDate.now().plusDays(2), Groupe.Q1);
        emploiDuTempsProxyTest.getPlanningOf(LocalDate.now().plusDays(3), Groupe.Q2);
        emploiDuTempsProxyTest.getPlanningOf(LocalDate.now().plusDays(4), Groupe.Q3);
        emploiDuTempsProxyTest.getPlanningOf(LocalDate.now().plusDays(5), Groupe.Q4);
        emploiDuTempsProxyTest.getPlanningOf(LocalDate.now().plusDays(6), Groupe.G1);
        emploiDuTempsProxyTest.getPlanningOf(LocalDate.now().plusDays(7), Groupe.G2);
        emploiDuTempsProxyTest.getPlanningOf(LocalDate.now().plusDays(8), Groupe.G3);
        emploiDuTempsProxyTest.getPlanningOf(LocalDate.now().plusDays(9), Groupe.G4);
        emploiDuTempsProxyTest.getPlanningOf(LocalDate.now().plusDays(10), Groupe.A2);
        return System.currentTimeMillis() - startTime;
    }

    @Test
    void test_getPlanningOf_DateGroupe_plusRapideInteligent() {
        emploiDuTempsProxyTest = EmploiDuTempsProxy.getInstance();

        long tempsExecution1 = rapiditeGetPlanningOfDate();
        long tempsExecution2 = rapiditeGetPlanningOfDateGroupe();

        assertTrue(tempsExecution1 > tempsExecution2);
    }

}
