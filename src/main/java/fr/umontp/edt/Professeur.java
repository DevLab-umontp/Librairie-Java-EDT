package fr.umontp.edt;

import java.util.Locale;

public class Professeur {
    private String denomination;
    private String nom;
    private String prenom;

    /**
     * @param nom
     * @param prenom
     */
    public Professeur(String nom, String prenom) {
        init(nom, prenom);
    }

    /**
     * Ce constructeur est essentiellement utilisé pour récupérer un professeur
     * depuis un evenement de l'enmploi du temps
     * 
     * @param nomPrenom {@code String} contenant le prenom et le nom du professeur
     */
    Professeur(String nomPrenom) {
        String[] infosProf = nomPrenom.split("   ");
        init(infosProf[0], infosProf[1]);
    }

    private void init(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
        denomination = formater(nom, prenom);
    }

    static String formater(String nom, String prenom) {
        return (nom.toLowerCase(Locale.FRANCE) + "   " + prenom.toUpperCase(Locale.FRANCE)).replaceAll("[^A-Z ]", " ");
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
