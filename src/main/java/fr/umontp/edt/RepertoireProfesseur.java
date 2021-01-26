package fr.umontp.edt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RepertoireProfesseur {
    private static HashMap<String, Professeur> repertoire = new HashMap<>();

    /**
     * Permet d'obtenir le(s) professeur(s) d'un VENVENT a partir de la description
     * de ce dernier
     *
     * @param description description du VENVENT
     * @return {@code String[]} le(s) professeur(s) du present dans la description
     * @since 1.0
     */
    static Professeur[] getProfesseurDepuisDescriptionEtAjouterSiNonPresent(String description) {
        String regex = "(?<=\\n)[- A-Z]*   [- A-Z]*(?=\\n)";
        Matcher m = Pattern.compile(regex).matcher(description);
        final List<Professeur> matches = new ArrayList<>();
        while (m.find()) {
            String nomPrenomProf = Professeur.formater(m.group(0));
            matches.add(repertoire.computeIfAbsent(nomPrenomProf, k -> new Professeur(nomPrenomProf)));
        }
        return matches.toArray(new Professeur[matches.size()]);
    }

    /**
     * @param key
     * @return
     * @see java.util.HashMap#get(java.lang.Object)
     */

    public static Professeur get(String nom, String prenom) {
        return repertoire.get(Professeur.formater(nom, prenom));
    }

}
