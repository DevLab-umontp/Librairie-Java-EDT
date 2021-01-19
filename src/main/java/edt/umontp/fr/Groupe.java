package edt.umontp.fr;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Groupe est une enumeration qui permet de recenser les différents groupes d'enseignements pour lesquelles l'API est
 * proposée
 *
 * @author emerick-biron, MathieuSoysal
 * @version 1.0
 */
public enum Groupe {
    NULL("sans groupe"), // Gérer les exceptions
    A1("A1"), // première année dep info
    S1("S1", A1), //
    S2("S2", A1), //
    S3("S3", A1), //
    S4("S4", A1), //
    S5("S5", A1), //
    S6("S6", A1), //
    A2("A2"), // deuxième année dep info
    Q1("Q1", A2), //
    Q2("Q2", A2), //
    Q3("Q3", A2), //
    Q4("Q4", A2), //
    G1("G1", A2), //
    G2("G2", A2), //
    G3("G3", A2), //
    G4("G4", A2);

    private static final String REGEX;

    static {
        Groupe[] groupes = values();
        String[] strings = new String[groupes.length];
        for (int i = 0; i < strings.length; i++)
            strings[i] = groupes[i].getIntitule();
        REGEX = String.format("(%s)", String.join("|", strings));
    }

    private String intitule;
    private Groupe groupeParent;

    /**
     * @param intitule
     */
    private Groupe(String intitule) {
        this.intitule = intitule;
        groupeParent = null;
    }

    /**
     * @param intitule
     * @param groupeParent
     */
    private Groupe(String intitule, Groupe groupeParent) {
        this.intitule = intitule;
        this.groupeParent = groupeParent;
    }

    /**
     * Permet d'obtenir le(s) groupe(s) d'enseignement a partir d'un texte (dans le cadre de cette API a partir de la
     * description d'un VEVENT)
     *
     * @param texte texte source (ici description)
     * @return groupe(s) correspondant
     * @since 1.0
     */
    public static Groupe getGroupeDepuisTexte(String texte) {
        final Matcher m = Pattern.compile(REGEX).matcher(texte);
        if (m.find()) return valueOf(m.group(0));
        else return Groupe.NULL;
    }

    /**
     * @return the intitule
     */
    public String getIntitule() {
        return intitule;
    }

    /**
     * Permet de savoir si ce groupe appartient a un autre groupe
     *
     * @return {@code boolean}
     * @since 1.0
     */
    private boolean possedeGroupeParent() {
        return groupeParent != null;
    }

    /**
     * Permet de savoir si ce groupe est contenu dans un autre groupe
     *
     * @return {@code boolean}
     * @since 1.0
     */
    public boolean estContenuDans(Groupe autreGroupe) {
        return autreGroupe == this || (possedeGroupeParent() && groupeParent.estContenuDans(autreGroupe));
    }

}
