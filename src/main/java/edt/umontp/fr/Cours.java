package edt.umontp.fr;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Property;

public class Cours implements Comparable<Integer> {
    private LocalDate date;
    private String[] prof;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private String lieu;
    private int duree;
    private Groupe groupe;
    private String intitule;

    public Cours(LocalDate date, String[] prof, LocalTime heureDebut, LocalTime heureFin, String lieu, int duree,
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

    public Cours(Component vEvent) {
        Property summary = vEvent.getProperty(Property.SUMMARY);
        Property description = vEvent.getProperty(Property.DESCRIPTION);
        Property dtStart = vEvent.getProperty(Property.DTSTART);
        Property dtEnd = vEvent.getProperty(Property.DTEND);
        Property location = vEvent.getProperty(Property.LOCATION);

        DateTime dateDebut = null;
        DateTime dateFin = null;
        try {
            dateDebut = new DateTime(dtStart.getValue());
            dateFin = new DateTime(dtEnd.getValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date = LocalDate.ofInstant(dateDebut.toInstant(), dateDebut.getTimeZone().toZoneId());
        heureDebut = LocalTime.ofInstant(dateDebut.toInstant(), dateDebut.getTimeZone().toZoneId());
        heureFin = LocalTime.ofInstant(dateFin.toInstant(), dateFin.getTimeZone().toZoneId());
        lieu = location.getValue();
        intitule = summary.getValue();
        // TODO spliter la description pour initialiser les autres attributs
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

    public static String[] getProfFromDesc(String desc) {
        ArrayList<String> res = new ArrayList<String>();
        String regex = "(?<=\n)[- A-Z]*   [- A-Z]*(?=\n)";
        Matcher m = Pattern.compile(regex).matcher(desc);
        final List<String> matches = new ArrayList<>();
        while (m.find()) {
            matches.add(m.group(0));
        }


        return res.toArray(new String[0]);
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
