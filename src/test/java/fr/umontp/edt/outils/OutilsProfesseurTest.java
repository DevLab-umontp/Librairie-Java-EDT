package fr.umontp.edt.outils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class OutilsProfesseurTest {

    @ParameterizedTest(name = "la chaine de caractères {0} une fois son accentuation supprimé doit être égale à {1}")
    @MethodSource("genererArgumentsPourtest_supprimerAccentuation")
    void test_supprimerAccentuation(String input, String excepted) {
        assertEquals(excepted, OutilsProfesseur.supprimerAccentuation(input));
    }

    private static Stream<Arguments> genererArgumentsPourtest_supprimerAccentuation() {
        return Stream.of(//
                Arguments.of("É", "E"), //
                Arguments.of("1234567890", "1234567890"), //
                Arguments.of("ô", "o"), //
                Arguments.of("à", "a"), //
                Arguments.of("è", "e"), //
                Arguments.of("ç", "c"), //
                Arguments.of("'", "'"), //
                Arguments.of("-", "-"), //
                Arguments.of(" ", " "), //
                Arguments.of("é", "e"));
    }

    @ParameterizedTest(name = "la chaine de caractères {0} une fois formaté doit être égale à {1}")
    @MethodSource("genererArgumentsPourtest_formater_1parametre")
    void test_formater_1parametre(String input, String excepted) {
        assertEquals(excepted, OutilsProfesseur.formater(input));
    }

    private static Stream<Arguments> genererArgumentsPourtest_formater_1parametre() {
        return Stream.of(//
                Arguments.of("", ""), //
                Arguments.of("test", "TEST"), //
                Arguments.of("TeEsT", "TEEST"), //
                Arguments.of("Téèçà", "TEECA"), //
                Arguments.of("ÉÈÇÀ", "EECA"), //
                Arguments.of("test   t@°0245est", "TEST   T      EST"));
    }

    @ParameterizedTest(name = "la chiane de caractères {0} une fois formaté doit être égale à {1}")
    @MethodSource("genererArgumentsPourtest_formater_2parametres")
    void test_formater_2parametres(String input1, String input2, String excepted) {
        assertEquals(excepted, OutilsProfesseur.formater(input1, input2));
    }

    private static Stream<Arguments> genererArgumentsPourtest_formater_2parametres() {
        return Stream.of(//
                Arguments.of("", "", "   "), //
                Arguments.of("test", "TEST", "TEST   TEST"), //
                Arguments.of("TeEsT", "TeEsT", "TEEST   TEEST"), //
                Arguments.of("Téèçà", "Téèçà", "TEECA   TEECA"), //
                Arguments.of("ÉÈÇÀ", "ÉÈÇÀ", "EECA   EECA"), //
                Arguments.of("te93~('st", "t@°0245est", "TE     ST   T      EST"));
    }
}
