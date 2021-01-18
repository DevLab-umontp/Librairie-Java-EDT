package edt.umontp.fr;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Property;

public class Cours implements Comparable<Cours> {
    private LocalDate date;
    private String[] prof;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private String lieu;
    private int duree;
    private Groupe groupe;
    private String intitule;

    public Cours(LocalDate date, String[] prof, LocalTime heureDebut, LocalTime heureFin, String lieu, Groupe groupe,
            String intitule) {
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
        prof = getProfFromDesc(description.getValue());
        groupe = Groupe.getGroupeDepuisTexte(description.getValue());
        Duration duration = Duration.between(heureDebut, heureFin);
        duree = (int) duration.toMinutes();
    }

    public static String[] getProfFromDesc(String desc) {
        ArrayList<String> res = new ArrayList<String>();
        String regex = "(?<=\\n)[- A-Z]*   [- A-Z]*(?=\\n)";
        Matcher m = Pattern.compile(regex).matcher(desc);
        final List<String> matches = new ArrayList<>();
        while (m.find()) {
            matches.add(m.group(0));
        }
        return matches.toArray(new String[matches.size()]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Cours cours = (Cours) o;
        return duree == cours.duree && date.equals(cours.date) && prof.equals(cours.prof)
                && heureDebut.equals(cours.heureDebut) && heureFin.equals(cours.heureFin) && lieu.equals(cours.lieu)
                && groupe == cours.groupe && intitule.equals(cours.intitule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, prof, heureDebut, heureFin, lieu, duree, groupe, intitule);
    }

    @Override
    public int compareTo(Cours o) {
        int compareDate = date.compareTo(o.date);
        return compareDate != 0 ? compareDate : heureDebut.compareTo(o.heureDebut);
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @return the prof
     */
    public String[] getProf() {
        return prof;
    }

    /**
     * @return the heureDebut
     */
    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    /**
     * @return the heureFin
     */
    public LocalTime getHeureFin() {
        return heureFin;
    }

    /**
     * @return the lieu
     */
    public String getLieu() {
        return lieu;
    }

    /**
     * @return the duree
     */
    public int getDuree() {
        return duree;
    }

    /**
     * @return the groupe
     */
    public Groupe getGroupe() {
        return groupe;
    }

    /**
     * @return the intitule
     */
    public String getIntitule() {
        return intitule;
    }
}
