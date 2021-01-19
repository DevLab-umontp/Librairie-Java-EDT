package edt.umontp.fr;

import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

class EmploiDuTempsTest {

    @Test
    void test_actualiser_verifierRetourneAucuneErreur() {
        EmploiDuTemps emploiDuTemps = EmploiDuTemps.getInstance();
        assertAll(() -> emploiDuTemps.actualiser());
    }
}
