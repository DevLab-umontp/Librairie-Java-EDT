package edt.umontp.fr;

import java.time.LocalDate;

/**
 * Planifiable est une interface proposant des méthodes pour gérer un emploi du
 * temps
 *
 * @author emerick-biron
 * @author MathieuSoysal
 * @version 1.0
 */
interface Planifiable {
    /**
     * Permet d'obtenir le planning correspondant à une date
     *
     * @param date date pour laquelle on veut obtenir le planning
     * @return {@code Planning} correspondant
     * @since 1.0
     * 
     * @see Planning
     */
    public Planning getPlanningOf(LocalDate date);

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

    /**
     * Permet d'obtenir le planning correspondant à un groupe
     *
     * @param groupe groupe dont on veut obtenir le planning
     * @return planning correspondant
     * @since 1.0
     * 
     * @see Groupe
     * @see Planning
     */
    public Planning getPlanningOf(Groupe groupe);

}
