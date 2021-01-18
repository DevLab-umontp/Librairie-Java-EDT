package edt.umontp.fr;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CoursTest {

    @Test
    void Test_getGroupeFromDesc_A1Seul_A1() {
        String desc = "\n\nA1\nGOUAICH   ABDELKADER\nLAGUILLAUMIE   FABIEN\nPOUPET VICTOR\nROSENFELD MATTHIEU\nA "
                + "valider\n(Exporté le:18 / 01 / 2 021 10:51)\n";
        assertEquals(Groupe.A1, Cours.getGroupeFromDesc(desc));
    }

    @Test
    void Test_getGroupeFromDesc_A1NonSeul_A1() {
        String desc = "\n\nA1-Semestre-1\nGOUAICH   ABDELKADER\nLAGUILLAUMIE   FABIEN\nPOUPET   VICTOR\nROSENFELD   "
                + "MATTHIEU\nA valider\n(Exporté le:18/01/2 021 10:51)\n";
        assertEquals(Groupe.A1, Cours.getGroupeFromDesc(desc));
    }

    @Test
    void Test_getProfFromDdesc_Desc_NomsDesProfs() {
        String desc = "\n\nA2-Semestre-3\nBELMECHERI   NASSIM\nHAETTEL   THOMAS\nLA   XUAN HOANG\nCHIROUZE   ANNE\nA valider\n(Exporté le:18/01/2021 10:51)\n";
        String[] expected = { "BELMECHERI   NASSIM", "HAETTEL   THOMAS", "LA   XUAN HOANG", "CHIROUZE   ANNE" };
        assertEquals(Arrays.toString(expected), Arrays.toString(Cours.getProfFromDesc(desc)));
    }
}