package edt.umontp.fr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GroupeTest {

    @Test
    void Test_getIntitule() {
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
    void values() {
    }

    @Test
    void valueOf() {
    }
}
