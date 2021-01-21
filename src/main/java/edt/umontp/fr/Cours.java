package edt.umontp.fr;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Property;

/**
 * <b>Cours est la classe représentant un cours au sein du planning.</b>
 * <p>
 * Un objet Cours est caractérisé par les informations suivantes :
 * <ul>
 * <li>Une date.</li>
 * <li>D'une liste de professeurs.</li>
 * <li>D'une heure de début.</li>
 * <li>D'une heure de fin.</li>
 * <li>D'une durée.</li>
 * <li>D'une liste de {@link Groupe}.</li>
 * <li>D'un intitule.</li>
 * </ul>
 * 
 * @see Comparable
 * 
 * @author emerick-biron
 * @author MathieuSoysal
 * @version 1.0.0
 */
public class Cours implements Comparable<Cours> {
    private LocalDate date;
    private String[] prof;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private String lieu;
    private int duree;
    private Groupe[] groupes;
    private String intitule;

    public Cours(LocalDate date, String[] prof, LocalTime heureDebut, LocalTime heureFin, String lieu, Groupe[] groupe,
            String intitule) {
        this.date = date;
        this.prof = prof;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.lieu = lieu;
        this.groupes = groupe;
        this.intitule = intitule;
        duree = (int) Duration.between(heureDebut, heureFin).toMinutes();
    }

    /**
     * Constructeur Cours.
     * <p>
     * Le constructeur est chargé de trouver toutes attributs de class {@link Cours}
     * au sein du {@link Component}.
     * </p>
     * 
     * @param vEvent l'évenement du calendrier qui représente un cours
     * 
     * @see Component
     */
    @SuppressWarnings("deprecation")
    Cours(Component vEvent) {
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

        date = LocalDate.of(dateDebut.getYear() + 1900, dateDebut.getMonth() + 1, dateDebut.getDate());
        heureDebut = LocalTime.of(dateDebut.getHours(), dateDebut.getMinutes());
        heureFin = LocalTime.of(dateFin.getHours(), dateFin.getMinutes());
        lieu = location.getValue();
        intitule = summary.getValue();
        prof = getProfFromDesc(description.getValue());
        groupes = Groupe.getGroupeDepuisTexte(description.getValue());
        duree = (int) Duration.between(heureDebut, heureFin).toMinutes();
    }

    /**
     * Permet d'obtenir le(s) professeur(s) d'un VENVENT a partir de la description
     * de ce dernier
     *
     * @param desc description du VENVENT
     * @return {@code String[]} le(s) professeur(s) du present dans la description
     * @since 1.0
     */
    static String[] getProfFromDesc(String desc) {
        String regex = "(?<=\\n)[- A-Z]*   [- A-Z]*(?=\\n)";
        Matcher m = Pattern.compile(regex).matcher(desc);
        final List<String> matches = new ArrayList<>();
        while (m.find()) {
            matches.add(m.group(0));
        }
        return matches.toArray(new String[matches.size()]);
    }

    @Override
    public int compareTo(Cours o) {
        int result = date.compareTo(o.date);
        if (result != 0)
            return result;
        result = heureDebut.compareTo(o.heureDebut);
        if (result != 0)
            return result;
        result = Integer.compare(duree, o.duree);
        if (result != 0)
            return result;
        for (Groupe groupe : groupes)
            for (Groupe autreGroupe : o.groupes) {
                result = groupe.getIntitule().compareTo(autreGroupe.getIntitule());
                if (result != 0)
                    return result;
            }
        return intitule.compareTo(o.intitule);
    }

    /**
     * @return {@code LocalDate} la date de début du cours
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @return {@code String[]} les professeurs organisant le cours
     */
    public String[] getProf() {
        return prof;
    }

    /**
     * @return {@code LocalTime} l'heure de début du cours
     */
    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    /**
     * @return {@code LocalTime} l'heure de fin du cours
     */
    public LocalTime getHeureFin() {
        return heureFin;
    }

    /**
     * @return {@code String} le lieu
     */
    public String getLieu() {
        return lieu;
    }

    /**
     * @return {@code int} la durée du cours
     */
    public int getDuree() {
        return duree;
    }

    /**
     * @return {@code Groupe[]} les groupes assignés au cours
     * 
     * @see Groupe
     */
    public Groupe[] getGroupes() {
        return groupes;
    }

    /**
     * @return {@code String} l'intitulé du cours
     */
    public String getIntitule() {
        return intitule;
    }

    @Override
    public String toString() {
        return intitule.toUpperCase(Locale.ROOT) + " :\n Date : " + date + "\n Commence à " + heureDebut + ", finit à "
                + heureFin + "\n Enseignant :" + Arrays.toString(prof) + " \n Localisation : " + lieu + " \n Groupe : "
                + Arrays.toString(groupes);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        for (Groupe groupe : groupes)
            result = prime * result + ((groupe == null) ? 0 : groupe.hashCode());
        result = prime * result + ((heureDebut == null) ? 0 : heureDebut.hashCode());
        result = prime * result + ((lieu == null) ? 0 : lieu.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cours other = (Cours) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        for (Groupe groupe : groupes)
            for (Groupe oGroupe : other.groupes)
                if (groupe != oGroupe)
                    return false;
        if (heureDebut == null) {
            if (other.heureDebut != null)
                return false;
        } else if (!heureDebut.equals(other.heureDebut))
            return false;
        if (lieu == null) {
            if (other.lieu != null)
                return false;
        } else if (!lieu.equals(other.lieu))
            return false;
        return true;
    }

}
