package edt.umontp.fr;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import edu.emory.mathcs.backport.java.util.Collections;
import edu.emory.mathcs.backport.java.util.TreeSet;

public class Planning implements Iterable<Cours>, Planifiable {
    private SortedSet<Cours> cours;

    public Planning(Collection<Cours> cours) {
        this.cours = new TreeSet(cours);
    }

    //TODO a coder
    @Override
    public Iterator<Cours> iterator() {
        return cours.iterator();
    }

    @Override
    public void forEach(Consumer action) {
        cours.forEach(action);
    }

    //TODO a coder
    @Override
    public Spliterator<Cours> spliterator() {
        return cours.spliterator();
    }

    //TODO a coder
    @Override
    public Planning getPlanningOf(LocalDate date) {
        ArrayList<Cours> result = new ArrayList<>();
        LocalDate lastDate = ((Cours) Collections.max(cours)).getDate();
        if (!lastDate.isBefore(date)) {
            Queue<Cours> coursTrie = new LinkedList<>(cours);
            while (coursTrie.peek().getDate().isBefore(date))
                coursTrie.poll();
            while (!coursTrie.isEmpty() && coursTrie.peek().getDate().isEqual(date))
                result.add(coursTrie.poll());
        }
        return new Planning(result);
    }

    @Override
    public Planning getPlanningOf(LocalDate date, Groupe groupe) {
        return null;
    }

    //TODO a coder
    @Override
    public Planning getPlanningOf(Groupe groupe) {
        return null;
    }
}

















