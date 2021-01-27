package fr.umontp.edt;

import java.time.LocalDate;

/**
 * Planifiable est une interface proposant des méthodes pour gérer un emploi du
 * temps
 *
 * @author emerick-biron
 * @author MathieuSoysal
 * @version 1.3.0
 */
interface Planifiable {

    /**
     * Permet d'obtenir le planning correspondant à une date ou plusieurs dates.
     *
     * @param dates dates pour lesquelles on veut obtenir le planning.
     * @return {@code Planning} correspondant
     * @see Planning
     * @since 1.3.0
     */
    public Planning getPlanningOf(LocalDate... dates);

    /**
     * Permet d'obtenir le planning correspondant à un groupe ou plusieurs groupe.
     *
     * @param groupes groupes dont on veut obtenir le planning
     * @return planning correspondant
     * @see Groupe
     * @see Planning
     * @since 1.2.0
     */
    public Planning getPlanningOf(Groupe... groupes);

    /**
     * Permet d'obtenir le planning correspondant à un professeur
     *
     * @param professeur professeur dont on veut obtenir le planning
     * @return planning correspondant
     * @since 1.1.0
     * 
     * @see Groupe
     * @see Planning
     */
    public Planning getPlanningOf(Professeur... professeurs);

    /**
     * Permet d'obtenir le planning correspondant à une date et un groupe
     *
     * @param date   date dont on veut obtenir le planning
     * @param groupe groupe dont on veut obtenir le planning
     * @return planning correspondant
     * @since 1.0
     * 
     * @see Groupe
     * @see Planning
     */
    public Planning getPlanningOf(LocalDate date, Groupe groupe);

}
