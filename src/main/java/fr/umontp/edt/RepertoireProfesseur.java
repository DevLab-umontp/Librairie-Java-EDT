package fr.umontp.edt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <b>RepertoireProfesseur est la classe qui repertorie tous les professeurs de
 * l'emploi du temps.</b>
 * <p>
 * 
 * @see Professeur
 * 
 * @author MathieuSoysal
 * @version 1.0.1
 */
public final class RepertoireProfesseur {
    private static HashMap<String, Professeur> repertoire = new HashMap<>();

    /**
     * La class RepertoireProfesseur est une class utilitaire est ne doit pas être instancié.
     * 
     * @since 1.0.1
     * 
     * @see RepertoireProfesseur#get(String, String)
     */
    private RepertoireProfesseur() {
        throw new IllegalStateException("Class utilitaire");
    }

    /**
     * Permet d'obtenir le(s) professeur(s) d'un VENVENT a partir de la description
     * de ce dernier
     *
     * @param description description du VENVENT
     * @return {@code String[]} le(s) professeur(s) du present dans la description
     * @since 1.0
     */
    static Professeur[] getProfesseurDepuisDescriptionEtAjouterSiNonPresent(String description) {
        String regex = "(?<=\\n).*   .*(?=\\n)";
        Matcher m = Pattern.compile(regex).matcher(description);
        final List<Professeur> matches = new ArrayList<>();
        while (m.find()) {
            String nomPrenomProf = Professeur.formater(m.group(0));
            matches.add(repertoire.computeIfAbsent(nomPrenomProf, k -> new Professeur(nomPrenomProf)));
        }
        return matches.toArray(new Professeur[matches.size()]);
    }

    /**
     * Retourne le professeur correspondant aux variables nom et prénom donné en
     * paramètre.
     * 
     * @param nom    du professeur devant être cherché
     * @param prenom du professeur devant être cherché
     * @return {@code Professeur} correspondant sinon retourne {@code null} si aucun
     *         professeur ne correspond.
     * 
     * @see java.util.HashMap#get(java.lang.Object)
     */
    public static Professeur get(String nom, String prenom) {
        return repertoire.get(Professeur.formater(nom, prenom));
    }

}
