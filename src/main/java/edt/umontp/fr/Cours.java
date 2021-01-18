package edt.umontp.fr;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Cours implements Comparable<Integer> {
    private final LocalDate date;
    private final String prof;
    private final LocalTime heureDebut;
    private final LocalTime heureFin;
    private final String lieu;
    private final int duree;
    private final Groupe groupe;
    private final String intitule;

    public Cours(LocalDate date, String prof, LocalTime heureDebut, LocalTime heureFin, String lieu, int duree,
                 Groupe groupe, String intitule) {
        this.date = date;
        this.prof = prof;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.lieu = lieu;
        this.duree = duree;
        this.groupe = groupe;
        this.intitule = intitule;
    }

    public static Groupe getGroupeFromDesc(String desc) {
        Groupe res = null;
        for (Groupe g : Groupe.values()) {
            if (desc.contains(g.name())) {
                res = g;
            }
        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cours cours = (Cours) o;
        return duree == cours.duree && date.equals(cours.date) && prof.equals(cours.prof) && heureDebut.equals(cours.heureDebut) && heureFin.equals(cours.heureFin) && lieu.equals(cours.lieu) && groupe == cours.groupe && intitule.equals(cours.intitule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, prof, heureDebut, heureFin, lieu, duree, groupe, intitule);
    }

    @Override
    public int compareTo(Integer o) {
        return 0;
    }
}
