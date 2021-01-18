package edt.umontp.fr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Property;

public class EmploiDuTemps {
    private final String LIEN_ICAL = "https://proseconsult.umontpellier.fr/jsp/custom/modules/plannings/direct_cal.jsp?data=58c99062bab31d256bee14356aca3f2423c0f022cb9660eba051b2653be722c431b66c493702208e664667048bc04373dc5c094f7d1a811b903031bde802c7f59b21846d3c6254443d7b6e956d3145c6e0d5bac87b70fdd185b8b86771d71211a02411e8351020815cfb0dcc54c667187353dbcfc377b44753a4f433d4e51f753c2b0fc0eafdcbc1cbb6ef4e715ebea9d495758b595b12cb294e70e715876fbaa3c654023c76f43cd51442775ff171e0a5f21b50c55a5b52d94df3e7977af823a1e78ee86c6497b1cf8732d52143eeffacc27449fc13ec1f0b04d23e09712df15579474e1aa0cd65f50f33a1dd766301,1";
    private final EmploiDuTemps singleton = null;
    private Planning planningEmploisDutemps;

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

    public void actualiser() {
        File fichierIcs = getFichierIcsDepuisLienIcal();
        Calendar calendar = convertieFichierIcsEnCalendar(fichierIcs);
        ArrayList<Cours> coursList = new ArrayList<>();
        for (Object vEvent : calendar.getComponents(Component.VEVENT)) {
            coursList.add(new Cours((Component) vEvent));
        }
        planningEmploisDutemps = new Planning(coursList);
    }

}
