package edt.umontp.fr;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Groupe {
    NULL("sans groupe"), // Gérer les exceptions TODO à voir si toujours util
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
     * @return the intitule
     */
    public String getIntitule() {
        return intitule;
    }

    public static Groupe[] getGroupeDepuisTexte(String texte) {
        final Matcher m = Pattern.compile(REGEX).matcher(texte);
        Collection<Groupe> result = new ArrayList<>();
        while (m.find())
            result.add(valueOf(m.group(0)));
        return result.toArray(Groupe[]::new);
    }

    private boolean possedeGroupeParent() {
        return groupeParent != null;
    }

    public boolean estContenuDans(Groupe autreGroupe) {
        return autreGroupe == this || (possedeGroupeParent() && groupeParent.estContenuDans(autreGroupe));
    }

}
