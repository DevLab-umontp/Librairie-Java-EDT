package fr.umontp.edt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <b>Groupe est une enumeration qui permet de recenser les différents groupes
 * d'élèves de l'université</b>
 * <p>
 * Une instance de groupe est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un intitulé.</li>
 * <li>Un groupe parent.</li>
 * </ul>
 * <p>
 * De plus, un {@link Groupe} ne pas être instancié, il faut donc passer par
 * l'enumération.
 * </p>
 * 
 * 
 * @author emerick-biron
 * @author MathieuSoysal
 * @version 1.2.0
 */
public enum Groupe {
    /**
     * {@code A1} est l'objet représentant les classes de première année du
     * département informatique de l'IUT de Montpellier.
     * 
     * Cet objet n'est pas modifiable.
     * 
     * @see Groupe
     */
    A1("A1"),

    /**
     * {@code S1} est l'objet représentant le premier groupe des étudiants de
     * première année du département informatique de l'IUT de Montpellier.
     * 
     * Cet objet n'est pas modifiable.
     * 
     * @see Groupe
     */
    S1("S1", A1),
    /**
     * {@code S2} est l'objet représentant le deuxième groupe des étudiants de
     * première année du département informatique de l'IUT de Montpellier.
     * 
     * Cet objet n'est pas modifiable.
     * 
     * @see Groupe
     */
    S2("S2", A1),
    /**
     * {@code S3} est l'objet représentant le troisième groupe des étudiants de
     * première année du département informatique de l'IUT de Montpellier.
     * 
     * Cet objet n'est pas modifiable.
     * 
     * @see Groupe
     */
    S3("S3", A1),
    /**
     * {@code S4} est l'objet représentant le quatrième groupe des étudiants de
     * première année du département informatique de l'IUT de Montpellier.
     * 
     * Cet objet n'est pas modifiable.
     * 
     * @see Groupe
     */
    S4("S4", A1),
    /**
     * {@code S5} est l'objet représentant le cinquième groupe des étudiants de
     * première année du département informatique de l'IUT de Montpellier.
     * 
     * Cet objet n'est pas modifiable.
     * 
     * @see Groupe
     */
    S5("S5", A1),
    /**
     * {@code S6} est l'objet représentant le sixième groupe des étudiants de
     * première année du département informatique de l'IUT de Montpellier.
     * 
     * Cet objet n'est pas modifiable.
     * 
     * @see Groupe
     */
    S6("S6", A1),
    /**
     * {@code A2} est l'objet représentant les classes de deuxième année du
     * département informatique de l'IUT de Montpellier.
     * 
     * Cet objet n'est pas modifiable.
     * 
     * @see Groupe
     */
    A2("A2"),
    /**
     * {@code Q1} est l'objet représentant le premier groupe des étudiants de
     * deuxième année de troisième semestre du département informatique de l'IUT de
     * Montpellier.
     * 
     * Cet objet n'est pas modifiable.
     * 
     * @see Groupe
     */
    Q1("Q1", A2),
    /**
     * {@code Q2} est l'objet représentant le deuxième groupe des étudiants de
     * deuxième année de troisième semestre du département informatique de l'IUT de
     * Montpellier.
     * 
     * Cet objet n'est pas modifiable.
     * 
     * @see Groupe
     */
    Q2("Q2", A2),
    /**
     * {@code Q3} est l'objet représentant le troisième groupe des étudiants de
     * deuxième année de troisième semestre du département informatique de l'IUT de
     * Montpellier.
     * 
     * Cet objet n'est pas modifiable.
     * 
     * @see Groupe
     */
    Q3("Q3", A2),
    /**
     * {@code Q4} est l'objet représentant le quatrième groupe des étudiants de
     * deuxième année de troisième semestre du département informatique de l'IUT de
     * Montpellier.
     * 
     * Cet objet n'est pas modifiable.
     * 
     * @see Groupe
     */
    Q4("Q4", A2),
    /**
     * {@code G1} est l'objet représentant le premier groupe des étudiants de
     * deuxième année de quatrième semestre du département informatique de l'IUT de
     * Montpellier.
     * 
     * Cet objet n'est pas modifiable.
     * 
     * @see Groupe
     */
    G1("G1", A2),
    /**
     * {@code G2} est l'objet représentant le deuxième groupe des étudiants de
     * deuxième année de quatrième semestre du département informatique de l'IUT de
     * Montpellier.
     * 
     * Cet objet n'est pas modifiable.
     * 
     * @see Groupe
     */
    G2("G2", A2),
    /**
     * {@code G3} est l'objet représentant le troisième groupe des étudiants de
     * deuxième année de quatrième semestre du département informatique de l'IUT de
     * Montpellier.
     * 
     * Cet objet n'est pas modifiable.
     * 
     * @see Groupe
     */
    G3("G3", A2),
    /**
     * {@code G4} est l'objet représentant le quatrième groupe des étudiants de
     * deuxième année de quatrième semestre du département informatique de l'IUT de
     * Montpellier.
     * 
     * Cet objet n'est pas modifiable.
     * 
     * @see Groupe
     */
    G4("G4", A2);

    /**
     * Ce regex permet de trouver quelles groupe ont étés référencé au sein d'un
     * texte.
     * 
     * @see Groupe#getGroupeDepuisTexte(String)
     */
    private static final String REGEX;

    static {
        Groupe[] groupes = values();
        String[] strings = new String[groupes.length];
        for (int i = 0; i < strings.length; i++)
            strings[i] = groupes[i].getIntitule();
        REGEX = String.format("(%s)", String.join("|", strings));
    }

    /**
     * Intitule du groupe, l'intitule est utilisé pour retrouver quelles sont les
     * groupes qui ont étés référencé au sein d'un texte.
     * 
     * @see Groupe#REGEX
     */
    private String intitule;

    /**
     * Cet attribut permet de représenter le groupe englobant le groupe actuel
     * (this)
     * 
     * Par exemple : Le groupe parent de {@code S1} est {@code A1} car {@code A1}
     * contient {@code S1}
     * 
     * @see Groupe#S1
     * @see Groupe#A1
     * @see Groupe#estContenuDans(Groupe)
     */
    private Groupe groupeParent;

    /**
     * @param intitule
     * 
     * @see Groupe#intitule
     */
    private Groupe(String intitule) {
        this.intitule = intitule;
        groupeParent = null;
    }

    /**
     * @param intitule
     * @param groupeParent
     * 
     * @see Groupe#intitule
     * @see Groupe#groupeParent
     */
    private Groupe(String intitule, Groupe groupeParent) {
        this.intitule = intitule;
        this.groupeParent = groupeParent;
    }

    /**
     * Permet d'obtenir le(s) groupe(s) d'enseignement a partir d'un texte (dans le
     * cadre de cette API a partir de la description d'un VEVENT)
     *
     * @param texte texte source (ici description)
     * @return {@code Groupe[]} groupe(s) correspondant
     * @since 1.0
     */
    public static Groupe[] getGroupeDepuisTexte(String texte) {
        final Matcher m = Pattern.compile(REGEX).matcher(texte);
        Collection<Groupe> result = new ArrayList<>();
        while (m.find())
            result.add(valueOf(m.group(0)));
        return result.toArray(Groupe[]::new);
    }

    /**
     * @return {@code String}l'intitule du groupe
     * 
     * @see Groupe#intitule
     */
    public String getIntitule() {
        return intitule;
    }

    /**
     * Permet de savoir si ce groupe appartient a un autre groupe
     *
     * @return {@code boolean}
     * @since 1.0
     * 
     * @see Groupe#groupeParent
     */
    private boolean possedeGroupeParent() {
        return groupeParent != null;
    }

    /**
     * Permet de savoir si ce groupe est contenu dans {@code autreGroupe}
     *
     * @param autreGroupes Les {@link Groupe} auquel on vérifie s'ils contiennent le groupe
     *                    actuel (this)
     * 
     * @return {@code boolean}
     * @since 1.0
     * 
     * @see Groupe#groupeParent
     * @see Groupe#possedeGroupeParent()
     */
    public boolean estContenuDans(Groupe... autreGroupes) {
        for (Groupe aGroupe : autreGroupes)
            if (aGroupe == this || (possedeGroupeParent() && groupeParent.estContenuDans(aGroupe)))
                return true;
        return false;
    }
}
