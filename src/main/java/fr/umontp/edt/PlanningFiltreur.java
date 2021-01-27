package fr.umontp.edt;

import java.time.LocalDate;

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

}
