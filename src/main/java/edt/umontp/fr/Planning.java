package edt.umontp.fr;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import edu.emory.mathcs.backport.java.util.TreeSet;

public class Planning implements Iterable<Cours>, Planifiable {
    private SortedSet<Cours> cours;

    public Planning(Collection<Cours> cours) {
        this.cours = new TreeSet(cours);
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
        for (Cours c : cours) {
            res += c.toString() + "\n\n";
        }
        return res;
    }

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

    @Override
    public Planning getPlanningOf(LocalDate date, Groupe groupe) {
        return getPlanningOf(date).getPlanningOf(groupe);
    }

    @Override
    public Planning getPlanningOf(Groupe groupe) {
        return new Planning(
                cours.stream().filter(x -> x.getGroupe().estContenuDans(groupe)).collect(Collectors.toList()));
    }

    /**
     * @return the cours
     */
    Collection<Cours> getCours() {
        return cours;
    }
}
