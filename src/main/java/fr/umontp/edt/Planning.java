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

/**
 * <b>Planning est la classe représentant un planning de {@link Cours} de
 * l'empois du temps de l'université.</b>
 * <p>
 * Une instance de Planning est caractérisé par une liste de {@link Cours}
 * </p>
 *
 * @author emerick-biron
 * @author MathieuSoysal
 * @version 1.0.0
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
     * Permet d'obtenir le planning correspondant à une date
     *
     * @param date date pour laquelle on veut obtenir le planning
     * @return {@code Planning} correspondant
     * @see Planning
     * @since 1.0
     */
    @Override
    public Planning getPlanningOf(LocalDate date) {
        ArrayList<Cours> result = new ArrayList<>();
        LocalDate lastDate = cours.last().getDate();
        if (!lastDate.isBefore(date)) {
            for (Cours c : cours) {
                int compare = c.getDate().compareTo(date);
                if (compare < 0)
                    continue;
                if (compare == 0)
                    result.add(c);
                else
                    return new Planning(result);
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
     * Permet d'obtenir le planning correspondant à un groupe
     *
     * @param groupe groupe dont on veut obtenir le planning
     * @return planning correspondant
     * @see Groupe
     * @see Planning
     * @since 1.0
     */
    @Override
    public Planning getPlanningOf(Groupe groupe) {
        return new Planning(
                cours.stream().filter(c -> groupe.estContenuDans(c.getGroupes())).collect(Collectors.toList()));
    }

    /**
     * Permet d'obtenir le planning correspondant à un professeur
     *
     * @param professeur professeur dont on veut obtenir le planning
     * @return planning correspondant
     * @since 1.1
     * 
     * @see Groupe
     * @see Planning
     */
    @Override
    public Planning getPlanningOf(Professeur... professeurs) {
        Planning result = new Planning();
        for (Professeur professeur : professeurs)
            result.cours.addAll(this.cours.stream().filter(c -> c.estEnseignePar(professeur)).collect(Collectors.toSet()));
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
}
