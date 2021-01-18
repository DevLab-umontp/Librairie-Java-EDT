package edt.umontp.fr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoursTest {

    @Test
    void Test_getGroupeFromDesc_A1Seul_A1() {
        String desc = "\n\nA1\nGOUAICH   ABDELKADER\nLAGUILLAUMIE   FABIE " + "N\nPOUPET VICTOR\nROSENFELD " +
                "MATTHIEU\nA valider\n(Exporté le:18 / 01 / 2 021 10:51)\n";
        assertEquals(Groupe.A1, Cours.getGroupeFromDesc(desc));
    }

    @Test
    void Test_getGroupeFromDesc_A1NonSeul_A1() {
        String desc = "\n\nA1-Semestre-1\nGOUAICH   ABDELKADER\nLAGUILLAUMIE   FABIE        N\nPOUPET   " +
                "VICTOR\nROSENFELD   MATTHIEU\nA valider\n(Exporté le:18/01/2 021 10:51)\n";
        assertEquals(Groupe.A1, Cours.getGroupeFromDesc(desc));
    }
}