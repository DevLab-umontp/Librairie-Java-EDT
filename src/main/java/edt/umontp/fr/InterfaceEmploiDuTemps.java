package edt.umontp.fr;

/**
 * InterfaceEmploisDuTemps est un interface proposant des méthodes pour un emploi du temps
 *
 * @author emerick-biron
 * @author MathieuSoysal
 * @version 1.0
 * @see Planifiable
 */
interface InterfaceEmploiDuTemps extends Planifiable {

    /**
     * Permet d'actualiser l'emploi du temps avec celui de l'ent.
     */
    public void actualiser();
}
