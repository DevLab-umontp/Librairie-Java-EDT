package edt.umontp.fr;

import java.time.LocalDate;
import java.util.Date;

interface Planifiable {
    public Planning getPlanningOf(LocalDate date);

    public Planning getPlanningOf(LocalDate date, Groupe groupe);

    public Planning getPlanningOf(Groupe groupe);

}
