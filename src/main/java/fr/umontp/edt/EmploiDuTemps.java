package fr.umontp.edt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;

/**
 * <b>EmploisDuTemps est la classe représentant tout l'emploi du temps
 * l'unviersité.</b>
 * <p>
 * De plus, l'EmploiDuTemps est un singleton, il ne peut y avoir donc qu'une
 * instance de celle-ci.
 * </p>
 * 
 * @deprecated Pour l'utilisation de la class EmploiDuTemps, il est préférable
 *             de passer par son proxy : {@link EmploiDuTempsProxy} afin d'avoir
 *             de meilleurs temps de réponse.
 * 
 * @see InterfaceEmploiDuTemps
 * @see EmploiDuTempsProxy
 * 
 * @author emerick-biron
 * @author MathieuSoysal
 * @version 1.2.0
 */
@Deprecated(forRemoval = false)
public final class EmploiDuTemps implements InterfaceEmploiDuTemps {
    static final ZoneId ZONE_ID = ZoneId.of("Europe/Paris");
    private static EmploiDuTemps singleton = null;
    private final String LIEN_ICAL = "https://proseconsult.umontpellier.fr/jsp/custom/modules/plannings/direct_cal"
            + ".jsp?data"
            + "=58c99062bab31d256bee14356aca3f2423c0f022cb9660eba051b2653be722c431b66c493702208e664667048bc04373dc5c094f7d1a811b903031bde802c7f59b21846d3c6254443d7b6e956d3145c6e0d5bac87b70fdd185b8b86771d71211a02411e8351020815cfb0dcc54c667187353dbcfc377b44753a4f433d4e51f753c2b0fc0eafdcbc1cbb6ef4e715ebea9d495758b595b12cb294e70e715876fbaa3c654023c76f43cd51442775ff171e0a5f21b50c55a5b52d94df3e7977af823a1e78ee86c6497b1cf8732d52143eeffacc27449fc13ec1f0b04d23e09712df15579474e1aa0cd65f50f33a1dd766301,1";
    private Planning planningEmploisDuTemps;

    /**
     * Permet mettre a jour l'emploi du temps
     *
     * @since 1.0
     */
    private EmploiDuTemps() {
        actualiser();
    }

    /**
     * Retourne l'instance de {@link EmploiDuTemps}.
     * 
     * @return {@code EmploiDuTemps}
     * 
     * @deprecated Pour des raisons d'optimisation il est préférable de passer pour
     *             le proxy : {@link EmploiDuTempsProxy}
     * 
     * @see EmploiDuTempsProxy
     */
    @Deprecated
    public static EmploiDuTemps getInstance() {
        EmploiDuTemps localInstance = singleton;
        if (localInstance == null) {
            synchronized (EmploiDuTemps.class) {
                localInstance = singleton;
                if (localInstance == null) {
                    localInstance = new EmploiDuTemps();
                }
            }
        }
        return localInstance;
    }

    /**
     * Permet d'obtenir un objet {@link Calendar} a parti d'un fichier ics
     *
     * @param fichierIcs fichier ics source
     * @return objet calendar corespondant
     * @since 1.0
     */
    private Calendar convertieFichierIcsEnCalendar(File fichierIcs) {
        Calendar calendar = null;
        try (FileInputStream fileICS = new FileInputStream(fichierIcs)) {
            CalendarBuilder builder = new CalendarBuilder();
            calendar = builder.build(fileICS);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    /**
     * Permet d'obtenir un fichier ics correspondant a un lien iCal (ici lien iCal =
     * {@link #LIEN_ICAL}
     *
     * @return fichier ics correspondant
     * @since 1.0
     */
    private File getFichierIcsDepuisLienIcal() {
        File fichierIcs = new File("fichier.ics");
        if (fichierIcs.isFile()) {
            fichierIcs.delete();
        }
        try {
            URL url = new URL(LIEN_ICAL);
            InputStream in = url.openStream();
            Files.copy(in, Paths.get(fichierIcs.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fichierIcs;
    }

    @Override
    public void actualiser() {
        File fichierIcs = getFichierIcsDepuisLienIcal();
        Calendar calendar = convertieFichierIcsEnCalendar(fichierIcs);
        ArrayList<Cours> coursList = new ArrayList<>();
        for (Object vEvent : calendar.getComponents(Component.VEVENT)) {
            coursList.add(new Cours((Component) vEvent));
        }
        planningEmploisDuTemps = new Planning(coursList);
    }

    /**
     * Permet d'obtenir le planning correspondant à une date ou plusieurs dates.
     *
     * @param dates dates pour lesquelles on veut obtenir le planning.
     * @return {@code Planning} correspondant
     * @see Planning
     * @see Planning#getPlanningOf(LocalDate...)
     */
    @Override
    public Planning getPlanningOf(LocalDate... dates) {
        return planningEmploisDuTemps.getPlanningOf(dates);
    }

    /**
     * Permet d'obtenir le planning correspondant à une date et un groupe
     *
     * @param date   date dont on veut obtenir le planning
     * @param groupe groupe dont on veut obtenir le planning
     * @return planning correspondant
     * @since 1.0
     * 
     * @see Groupe
     * @see Planning
     */
    @Override
    public Planning getPlanningOf(LocalDate date, Groupe groupe) {
        return planningEmploisDuTemps.getPlanningOf(date, groupe);
    }

    /**
     * Permet d'obtenir le planning correspondant à un groupe ou plusieurs groupe.
     *
     * @param groupes groupes dont on veut obtenir le planning
     * @return planning correspondant
     * @see Groupe
     * @see Planning
     * @see Planning#getPlanningOf(Groupe...)
     */
    @Override
    public Planning getPlanningOf(Groupe... groupes) {
        return planningEmploisDuTemps.getPlanningOf(groupes);
    }

    /**
     * Permet d'obtenir le planning correspondant à un professeur ou plusieurs
     * professeurs
     *
     * @param professeurs professeurs dont on veut obtenir le planning
     * @return planning correspondant
     * @since 1.1
     * 
     * @see Professeur
     * @see Planning
     * @see Planning#getPlanningOf(Professeur...)
     */
    @Override
    public Planning getPlanningOf(Professeur... professeurs) {
        return planningEmploisDuTemps.getPlanningOf(professeurs);
    }

    /**
     * Permet d'obtenir le planning correspondant au {@link PlanningFiltreur}
     *
     * @param planningFiltreur filtrant le planning
     * @return planning correspondant au filtre
     * @since 1.2.0
     * 
     * @see PlanningFiltreur
     * @see Planning
     */
    @Override
    public Planning getPlanningOf(PlanningFiltreur planningFiltreur) {
        return planningEmploisDuTemps.getPlanningOf(planningFiltreur);
    }
}
