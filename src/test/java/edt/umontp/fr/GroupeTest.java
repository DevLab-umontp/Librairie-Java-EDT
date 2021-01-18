package edt.umontp.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GroupeTest {

    @Test
    void test_getIntitule() {
        assertEquals("A1", Groupe.A1.getIntitule());
    }

    @ParameterizedTest(name = "Le groupe {0} doit retourner {2} lorsqu'il verifie s'il est contenu dans {1}")
    @MethodSource("genererArgumentsPourtestestContenuDans")
    void test_estContenuDans(Groupe g1, Groupe g2, boolean result) {
        assertEquals(result, g1.estContenuDans(g2));
    }

    private static Stream<Arguments> genererArgumentsPourtestestContenuDans() {
        return Stream.of(//
                Arguments.of(Groupe.S1, Groupe.S3, false), //
                Arguments.of(Groupe.S1, Groupe.A1, true), //
                Arguments.of(Groupe.S1, Groupe.S1, true), //
                Arguments.of(Groupe.A2, Groupe.A1, false), //
                Arguments.of(Groupe.A1, Groupe.S3, false));
    }

    @Test
    void valueOf() {
        assertEquals(Groupe.A1, Groupe.valueOf("A1"));
    }

    @Test
    void test_getGroupeDepuisTexte_A1Seul_A1() {
        String desc = "\n\nA1\nGOUAICH   ABDELKADER\nLAGUILLAUMIE   FABIEN\nPOUPET VICTOR\nROSENFELD MATTHIEU\nA "
                + "valider\n(Exporté le:18 / 01 / 2 021 10:51)\n";
        assertEquals(Groupe.A1, Groupe.getGroupeDepuisTexte(desc));
    }

    @Test
    void test_getGroupeDepuisTexte_A1NonSeul_A1() {
        String desc = "\n\nA1-Semestre-1\nGOUAICH   ABDELKADER\nLAGUILLAUMIE   FABIEN\nPOUPET   VICTOR\nROSENFELD   "
                + "MATTHIEU\nA valider\n(Exporté le:18/01/2 021 10:51)\n";
        assertEquals(Groupe.A1, Groupe.getGroupeDepuisTexte(desc));
    }

    @Test
    void test_getGroupeDepuisTexte_aucunGroupe_GroupeNULL() {
        String desc = "\n\nSemestre-1\nGOUAICH   ABDELKADER\nLAGUILLAUMIE   FABIEN\nPOUPET   VICTOR\nROSENFELD   "
                + "MATTHIEU\nA valider\n(Exporté le:18/01/2 021 10:51)\n";
        assertEquals(Groupe.NULL, Groupe.getGroupeDepuisTexte(desc));
    }
}
