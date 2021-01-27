package fr.umontp.edt;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class RepertoireProfesseurTest {

    @Test
    void test_getProfesseurDepuisDescription_DoitTrouverTousLesProfesseurs() {
        String description = "\n\nA2-Semestre-3\nBELMECHERI   NASSIM\nHAETTEL   THOMAS\nLA   XUAN HOANG\nCHIROUZE   ANNE\nA valider\n(Exporté le:18/01/2021 10:51)\n";
        Professeur[] expected = { new Professeur("BELMECHERI   NASSIM"), new Professeur("HAETTEL   THOMAS"),
                new Professeur("LA   XUAN HOANG"), new Professeur("CHIROUZE   ANNE") };
        assertArrayEquals(expected,
                RepertoireProfesseur.getProfesseurDepuisDescriptionEtAjouterSiNonPresent(description));
    }

    @Test
    void test_get_avecDescription_doitRetournerUnProfesseur() {
        String description = "\n\nA2-Semestre-3\nBELMECHERI   NASSIM\nHAETTEL   THOMAS\nLA   XUAN HOANG\nDUPUIS   JEAN JEAN\nCHIROUZE   ANNE\nA valider\n(Exporté le:18/01/2021 10:51)\n";
        RepertoireProfesseur.getProfesseurDepuisDescriptionEtAjouterSiNonPresent(description);
        assertNotNull(RepertoireProfesseur.get("Dupuis", "JEAN-JEAN"));
    }

    @Test
    void test_get_avecDescription_doiventAvoirLaMemeAdresse() {
        String description = "\n\nA2-Semestre-3\nDUPUIS   JEAN JEAN\nA valider\n(Exporté le:18/01/2021 10:51)\n";
        Professeur excepted = RepertoireProfesseur.getProfesseurDepuisDescriptionEtAjouterSiNonPresent(description)[0];
        assertSame(excepted, RepertoireProfesseur.get("Dupuis", "JEAN-JEAN"));
    }

    @Test
    void test_get_avecDescriptionContenantDesCaracteresSpeciaux() {
        String description = "\n\nA2-Semestre-3\nDUPÛIS   JEAN-JEAN\nA valider\n(Exporté le:18/01/2021 10:51)\n";
        RepertoireProfesseur.getProfesseurDepuisDescriptionEtAjouterSiNonPresent(description);
        assertNotNull(RepertoireProfesseur.get("Dupûis", "JEAN-JEAN"));
    }
}
