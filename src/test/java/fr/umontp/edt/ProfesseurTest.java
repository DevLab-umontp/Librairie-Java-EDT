package fr.umontp.edt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ProfesseurTest {

    @Test
    void testConstructeur_UnStrings_prenomNomCombine() {
        Professeur professeur = new Professeur("Dupuis   Jean");
        assertEquals("Dupuis", professeur.getNom());
        assertEquals("Jean", professeur.getPrenom());
    }

    @ParameterizedTest(name = "Le professeur {0} doit être égal avec {1}")
    @MethodSource("genererArgumentsPourtest_equals")
    void test_equals(Professeur prof1, Professeur prof2) {
        assertEquals(prof1, prof2);
    }

    private static Stream<Arguments> genererArgumentsPourtest_equals() {
        return Stream.of(//
                Arguments.of(new Professeur("DUpuis   JeAN"), new Professeur("DupUis   JEan")), //
                Arguments.of(new Professeur("prés-dupuis   Jean"), new Professeur("pres-dupuis   Jean")), //
                Arguments.of(new Professeur("D'Ôrge   Jean"), new Professeur("DÔrge   Jean")), //
                Arguments.of(new Professeur("D'Ôrge   Jean"), new Professeur("D Ôrge   Jean")));
    }

}
