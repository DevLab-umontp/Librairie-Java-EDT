package edt.umontp.fr;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Planning implements Iterable<Cours>, Planifiable {
    private ArrayList<Cours> cours;

    public Planning(ArrayList<Cours> cours) {
        this.cours = cours;
    }

    //TODO a coder
    @Override
    public Iterator<Cours> iterator() {
        return null;
    }

    //TODO a coder
    @Override
    public void forEach(Consumer action) {

    }

    //TODO a coder
    @Override
    public Spliterator<Cours> spliterator() {
        return null;
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

















