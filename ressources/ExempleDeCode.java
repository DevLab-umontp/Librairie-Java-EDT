import java.time.LocalDate;

import fr.umontp.edt.*;

class ExempleDeCode {
    public static void main(String[] args) {

        // Récupérer l'emploi du temps de l'ent
        EmploiDuTempsProxy emploiDuTempsProxy = EmploiDuTempsProxy.getInstance();

        // Récupérer le planning d'aujourd'hui
        Planning planningDate = emploiDuTempsProxy.getPlanningOf(LocalDate.now());

        // Récupérer le planning du groupe S3
        Planning planningGroupe = emploiDuTempsProxy.getPlanningOf(Groupe.S3);

        // Récupérer le planning d'aujourd'hui du groupe S2
        Planning planningDateGroupe = emploiDuTempsProxy.getPlanningOf(LocalDate.now(), Groupe.S2);

        // Parcourir les cours d'un planning
        for (Cours cours : planningGroupe) {
            System.out.println(cours.getIntitule());
        }

        // Actualiser votre emploi du temps local avec celui de l'ent
        emploiDuTempsProxy.actualiser();

        // Récupérer le planning d'un professeur
        Planning planningProfesseur = emploiDuTempsProxy.getPlanningOf(RepertoireProfesseur.get("Dupuis", "Jacques"));

        // Récupérer le planning de plusieurs groupes
        Planning planningGroupes = emploiDuTempsProxy.getPlanningOf(Groupe.S3, Groupe.S4);

        // Récupérer le planning de plusieurs dates
        Planning planningDates = emploiDuTempsProxy.getPlanningOf(LocalDate.now(), LocalDate.now().plusDays(1));

        // Récupérer le planning de plusieurs professeurs
        Professeur prof1 = RepertoireProfesseur.get("Dupuis", "Jacques");
        Professeur prof2 = RepertoireProfesseur.get("Dupuis", "Jacqueline");
        Planning planningProfesseurs = emploiDuTempsProxy.getPlanningOf(prof1, prof2);

        // Récupérer un planning via le PlanningFiltreur pour faire des combinaisons entre professeur, groupe et date
        PlanningFiltreur plusieursGroupesDatesEtProfesseurs = PlanningFiltreur.filtrer()
                .par(Groupe.S2, Groupe.S3)
                .par(LocalDate.now(), LocalDate.now().plusDays(1))
                .par(prof1, prof2);
        Planning planningAvecFiltreur = emploiDuTempsProxy.getPlanningOf(parPlusieursGroupesEtDates);
    }
}
