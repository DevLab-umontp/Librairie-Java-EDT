package edt.umontp.fr;

import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

public class EmploiDuTempsTest {
    

    @Test
    void test_actualiser_verifierRetourneAucuneErreur(){
        EmploiDuTemps emploiDuTemps = new EmploiDuTemps();
        assertAll(() -> emploiDuTemps.actualiser());
    }
}
