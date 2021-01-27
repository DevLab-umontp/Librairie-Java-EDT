package fr.umontp.edt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * <b>Planning est la classe représentant un planning de {@link Cours} de
 * l'empois du temps de l'université.</b>
 * <p>
 * Une instance de Planning est caractérisé par une liste de {@link Cours}
 * </p>
 *
 * @author emerick-biron
 * @author MathieuSoysal
 * @version 1.3.0
 * @see Iterable
 * @see Planifiable
 * @see Cours
 */
public class Planning implements Iterable<Cours>, Planifiable {
    private SortedSet<Cours> cours;

    /**
     * Permet de créer un objet Planning a partir d'une collection de cours
     *
     * @param cours collection de cours
     * @see Cours
     * @since 1.0
     */
    public Planning(Collection<Cours> cours) {
        this.cours = new TreeSet<>(cours);
    }

    private Planning() {
        cours = new TreeSet<>();
    }

    /**
     * Permet d'obtenir le planning correspondant à une date ou plusieurs dates.
     *
     * @param dates dates pour lesquelles on veut obtenir le planning.
     * @return {@code Planning} correspondant
     * @see Planning
     * @since 1.3.0
     */
    @Override
    public Planning getPlanningOf(LocalDate... dates) {
        SortedSet<LocalDate> sortedDates = new TreeSet<LocalDate>(Arrays.asList(dates));
        ArrayList<Cours> result = new ArrayList<>();
        LocalDate lastDate = cours.last().getDate();
        if (!lastDate.isBefore(sortedDates.first())) {
            Iterator<Cours> iteratorCours = cours.iterator();
            for (LocalDate date : sortedDates) {
                result.addAll(getCoursOf(date, iteratorCours));
            }
        }
        return new Planning(result);
    }

    /**
     * Permet d'obtenir le planning correspondant à une date et un groupe
     *
     * @param date   date dont on veut obtenir le planning
     * @param groupe groupe dont on veut obtenir le planning
     * @return planning correspondant
     * @see Groupe
     * @see Planning
     * @since 1.0
     */
    @Override
    public Planning getPlanningOf(LocalDate date, Groupe groupe) {
        return getPlanningOf(date).getPlanningOf(groupe);
    }

    /**
     * Permet d'obtenir le planning correspondant à un groupe ou plusieurs groupe.
     *
     * @param groupes groupes dont on veut obtenir le planning
     * @return planning correspondant
     * @see Groupe
     * @see Planning
     * @since 1.2.0
     */
    @Override
    public Planning getPlanningOf(Groupe... groupes) {
        Planning result = new Planning();
        for (Groupe groupe : groupes)
            result.cours.addAll(
                    this.cours.stream().filter(c -> groupe.estContenuDans(c.getGroupes())).collect(Collectors.toSet()));
        return result;
    }

    /**
     * Permet d'obtenir le planning correspondant à un professeur ou plusieurs
     * professeurs
     *
     * @param professeurs professeurs dont on veut obtenir le planning
     * @return planning correspondant
     * @since 1.2
     * 
     * @see Professeur
     * @see Planning
     */
    @Override
    public Planning getPlanningOf(Professeur... professeurs) {
        Planning result = new Planning();
        for (Professeur professeur : professeurs)
            result.cours
                    .addAll(this.cours.stream().filter(c -> c.estEnseignePar(professeur)).collect(Collectors.toSet()));
        return result;
    }

    public Planning getPlanningOf(PlanningFiltreur planningFiltreur) {
        Planning result = new Planning(cours);
        if (planningFiltreur.getDates() != null)
            result = result.getPlanningOf(planningFiltreur.getDates());
        if (planningFiltreur.getGroupes() != null)
            result = result.getPlanningOf(planningFiltreur.getGroupes());
        if (planningFiltreur.getProfesseurs() != null)
            result = result.getPlanningOf(planningFiltreur.getProfesseurs());
        return result;
    }

    @Override
    public Iterator<Cours> iterator() {
        return cours.iterator();
    }

    @Override
    public void forEach(Consumer action) {
        cours.forEach(action);
    }

    @Override
    public Spliterator<Cours> spliterator() {
        return cours.spliterator();
    }

    @Override
    public String toString() {
        String res = "EDT :\n\n";
        if (cours.isEmpty()) {
            res += "Aucun cours";
        } else {
            for (Cours c : cours) {
                res += c.toString() + "\n\n";
            }
        }
        return res;
    }

    /**
     * @return the cours
     */
    Collection<Cours> getCours() {
        return cours;
    }

    /**
     * Retourne les cours d'{@code iteratorCours} correspondant à la {@code date}
     * donnée en paramètre.
     * 
     * @return {@code Collection<Cours>} correspondant
     * 
     * @param date          au quelle les cours doivent corespondre
     * @param iteratorCours est liste au quelles les cours doivent être cherchés
     * 
     * @see Cours
     */
    private Collection<Cours> getCoursOf(final LocalDate date, Iterator<Cours> iteratorCours) {
        final LocalDate lastDate = cours.last().getDate();
        Collection<Cours> result = new ArrayList<>();
        if (!lastDate.isBefore(date) && iteratorCours.hasNext()) {
            Cours c;
            do {
                c = iteratorCours.next();
                if (c.getDate().isEqual(date))
                    result.add(c);
            } while (iteratorCours.hasNext() && !c.getDate().isAfter(date));
        }
        return result;
    }

}
