package fr.umontp.edt;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.IdentityHashMap;

/**
 * <b>EmploiDuTempsProxy est la classe représentant le proxy de la class
 * {@link EmploiDuTemps}.</b>
 * <p>
 * L'instance d'EmploiDuTempsProxy est caractérisé par une liste de cache.
 * 
 * @author emerick-biron
 * @author MathieuSoysal
 * @version 1.3.0
 * @see EmploiDuTemps
 * @see InterfaceEmploiDuTemps
 */
public final class EmploiDuTempsProxy implements InterfaceEmploiDuTemps {
    private static EmploiDuTempsProxy singleton = null;
    @SuppressWarnings("deprecation")
    private EmploiDuTemps emploiDuTemps;
    private EnumMap<Groupe, Planning> cacheGroupe;
    private IdentityHashMap<Professeur, Planning> cacheProfesseur;
    private HashMap<LocalDate, Planning> cacheDate;
    private HashMap<MultiKey<LocalDate, Groupe>, Planning> cacheDateGroupe;

    @SuppressWarnings("deprecation")
    private EmploiDuTempsProxy() {
        emploiDuTemps = EmploiDuTemps.getInstance();
        actualiser();
    }

    /**
     * @return l'instance de {@link EmploiDuTempsProxy}.
     * 
     */
    public static EmploiDuTempsProxy getInstance() {
        EmploiDuTempsProxy localInstance = singleton;
        if (localInstance == null) {
            synchronized (EmploiDuTempsProxy.class) {
                localInstance = singleton;
                if (localInstance == null) {
                    localInstance = new EmploiDuTempsProxy();
                }
            }
        }
        return localInstance;
    }

    /**
     * Permet d'obtenir le planning correspondant à une date ou plusieurs dates.
     *
     * @param dates dates pour lesquelles on veut obtenir le planning.
     * @return {@code Planning} correspondant
     * @see Planning
     * @since 1.3.0
     */
    @SuppressWarnings("deprecation")
    @Override
    public Planning getPlanningOf(LocalDate... dates) {
        Collection<Cours> cours = new ArrayList<>();
        for (LocalDate date : dates)
            cours.addAll(cacheDate.computeIfAbsent(date, k -> emploiDuTemps.getPlanningOf(k)).getCours());
        return new Planning(cours);
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
        MultiKey<LocalDate, Groupe> multiKey = new MultiKey<>(date, groupe);
        return cacheDateGroupe.computeIfAbsent(multiKey, k -> getPlanningOf(k.key1).getPlanningOf(k.key2));
    }

    /**
     * Permet d'obtenir le planning correspondant à un groupe ou plusieurs groupe.
     *
     * @param groupes groupes dont on veut obtenir le planning
     * @return planning correspondant
     * @see Groupe
     * @see Planning
     * @since 1.2.0
     */
    @SuppressWarnings("deprecation")
    @Override
    public Planning getPlanningOf(Groupe... groupes) {
        Collection<Cours> cours = new ArrayList<>();
        for (Groupe groupe : groupes)
            cours.addAll(cacheGroupe.computeIfAbsent(groupe, k -> emploiDuTemps.getPlanningOf(k)).getCours());
        return new Planning(cours);
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
     */
    @SuppressWarnings("deprecation")
    @Override
    public Planning getPlanningOf(Professeur... professeurs) {
        Collection<Cours> cours = new ArrayList<>();
        for (Professeur professeur : professeurs)
            cours.addAll(cacheProfesseur.computeIfAbsent(professeur, k -> emploiDuTemps.getPlanningOf(k)).getCours());
        return new Planning(cours);
    }

    /**
     * Permet d'actualiser l'emploi du temps avec celui de l'ent.
     */
    @SuppressWarnings("deprecation")
    @Override
    public void actualiser() {
        cacheDate = new HashMap<>();
        cacheProfesseur = new IdentityHashMap<>();
        cacheGroupe = new EnumMap<>(Groupe.class);
        cacheDateGroupe = new HashMap<>();
        emploiDuTemps.actualiser();
    }

    /**
     * Cette classe représente une combinaison de clefs
     */
    private class MultiKey<K1, K2> {
        private K1 key1;
        private K2 key2;

        public MultiKey(K1 key1, K2 key2) {
            this.key1 = key1;
            this.key2 = key2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            MultiKey<K1, K2> key = (MultiKey) o;

            if (key1 != null ? !key1.equals(key.key1) : key.key1 != null)
                return false;
            if (key2 != null ? !key2.equals(key.key2) : key.key2 != null)
                return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = key1 != null ? key1.hashCode() : 0;
            result = 31 * result + (key2 != null ? key2.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "[" + key1 + ", " + key2 + "]";
        }
    }

}
