package fr.umontp.edt;

import static java.util.Comparator.comparing;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * <b>PlanningFiltreur est la classe représentant un filtrage pour un
 * Planning.</b>
 * <p>
 * Une instance du planning est peut être caractérisé par les informations
 * suivantes :
 * </p>
 * <ul>
 * <li>Une ou plusieurs date.</li>
 * <li>Un ou plusieurs groupe.</li>
 * <li>Un ou plusieurs professeurs.</li>
 * </ul>
 * <p>
 * Par défauts toutes ces attributs sont à {@code null}.
 * </p>
 * 
 * @see Planning
 * 
 * @author MathieuSoysal
 * @version 1.2.0
 */
public class PlanningFiltreur {
    private LocalDate[] dates;
    private Groupe[] groupes;
    private Professeur[] professeurs;

    private PlanningFiltreur() {
        dates = null;
        groupes = null;
    }

    /**
     * Instanciation de PlanningFiltreur.
     * <p>
     * A la l'instanciation d'un objet PlanningFiltreur, toutes les variables de
     * filtrage sont par défaut à {@code null}.
     * </p>
     * 
     * @since 1.1.0
     * 
     * @see PlanningFiltreur#par(LocalDate...)
     */
    public static PlanningFiltreur filtrer() {
        return new PlanningFiltreur();
    }

    /**
     * Ajoute un filtrage par un ou plusieurs date.
     * 
     * @param dates qui doivent filtrer le planning
     * 
     * @see LocalDate
     */
    public PlanningFiltreur par(LocalDate... dates) {
        this.dates = dates;
        return this;
    }

    /**
     * Ajoute un filtrage par un ou plusieurs groupe.
     * 
     * @param groupes qui doivent filtrer le planning
     * 
     * @see Groupe
     */
    public PlanningFiltreur par(Groupe... groupes) {
        this.groupes = groupes;
        return this;
    }

    /**
     * Ajoute un filtrage par un ou plusieurs Professeur.
     * 
     * @param professeurs qui doivent filtrer le planning
     * 
     * @see Professeur
     */
    public PlanningFiltreur par(Professeur... professeurs) {
        this.professeurs = professeurs;
        return this;
    }

    /**
     * @return {@code LocalDate[]} tableau des dates
     * 
     * @see LocalDate
     */
    public LocalDate[] getDates() {
        return dates;
    }

    /**
     * @return {@code LocalDate[]} tableau des groupes
     * 
     * @see Groupe
     */
    public Groupe[] getGroupes() {
        return groupes;
    }

    /**
     * @return {@code LocalDate[]} tableau des professeurs
     * 
     * @see Professeur
     */
    public Professeur[] getProfesseurs() {
        return professeurs;
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
        result = prime * result + Arrays.hashCode(dates);
        result = prime * result + Arrays.hashCode(groupes);
        if (professeurs != null) {
            List<Professeur> lProfesseurs = Arrays.asList(professeurs);
            lProfesseurs.sort(comparing(Professeur::hashCode));
            result = prime * result + lProfesseurs.hashCode();
        } else
            result = prime * result + Arrays.hashCode(professeurs);
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
        PlanningFiltreur other = (PlanningFiltreur) obj;
        if (!Arrays.equals(dates, other.dates))
            return false;
        if (!memeGroupes(other.groupes))
            return false;
        if (!Arrays.equals(professeurs, other.professeurs)) {
            if (professeurs.length == other.professeurs.length) {
                List<Professeur> listProfesseurs = Arrays.asList(professeurs);
                List<Professeur> listOtherProflistProfesseurs = Arrays.asList(other.professeurs);
                if (!(listProfesseurs.containsAll(listOtherProflistProfesseurs)
                        && listOtherProflistProfesseurs.containsAll(listProfesseurs)))
                    return false;
            } else
                return false;
        }
        return true;
    }

    private boolean memeGroupes(Groupe[] oGroupes) {
        if (!Arrays.equals(groupes, oGroupes)) {
            if (groupes.length == oGroupes.length) {
                List<Groupe> listGroupes = Arrays.asList(groupes);
                List<Groupe> listOtherGroupes = Arrays.asList(oGroupes);
                if (!(listGroupes.containsAll(listOtherGroupes) && listOtherGroupes.containsAll(listGroupes)))
                    return false;
            } else
                return false;
        }
        return true;
    }
}
