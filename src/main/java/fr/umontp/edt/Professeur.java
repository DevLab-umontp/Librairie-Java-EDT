package fr.umontp.edt;

import fr.umontp.edt.outils.OutilsProfesseur;

/**
 * <b>Professeur est la classe représentant un professeur au sein de l'emploi du
 * temps.</b> Un professeur est caractérisé par les informations suivantes :
 * <ul>
 * <li>Une dénomination (dénomination utilisé dans la description d'un
 * cours).</li>
 * <li>Un nom.</li>
 * <li>Un pénom.</li>
 * </ul>
 * <p>
 * Pour pouvoir instancier une Professeur il est conseillé de passé par la class
 * {@link RepertoireProfesseur}.
 * </p>
 * 
 * @see RepertoireProfesseur
 * @see OutilsProfesseur
 * 
 * @author MathieuSoysal
 * @version 1.0.1
 */
public class Professeur {
    private String denomination;
    private String nom;
    private String prenom;

    /**
     * Ce constructeur est essentiellement utilisé pour instancier un professeur
     * depuis un evenement de l'emploi du temps.
     * 
     * @param nomPrenom {@code String} contenant le nom et le prenom du professeur.
     */
    Professeur(String nomPrenom) {
        String[] infosProf = nomPrenom.split("   ");
        nom = infosProf[0];
        prenom = infosProf[1];
        denomination = OutilsProfesseur.formater(nom, prenom);
    }

    /**
     * @return le nom du professeur
     */
    public String getNom() {
        return nom;
    }

    /**
     * @return le prenom du professeur
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @return la denomination
     */
    String getDenomination() {
        return denomination;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((denomination == null) ? 0 : denomination.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Professeur other = (Professeur) obj;
        if (denomination == null) {
            if (other.denomination != null)
                return false;
        } else if (!denomination.equals(other.denomination))
            return false;
        return true;
    }
}
