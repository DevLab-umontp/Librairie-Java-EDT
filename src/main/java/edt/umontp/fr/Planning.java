package edt.umontp.fr;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Planning implements Iterable<Cours>, Planifiable {
    private Queue<Cours> cours;

    public Planning(Collection<Cours> cours) {
        this.cours = new LinkedList<>(cours);
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
        return null;
    }

    //TODO a coder
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

















