package edt.umontp.fr;

import java.time.LocalDate;

interface Planifiable {
    public Planning getPlanningOf(LocalDate date);

    public Planning getPlanningOf(LocalDate date, Groupe groupe);

    public Planning getPlanningOf(Groupe groupe);

}
