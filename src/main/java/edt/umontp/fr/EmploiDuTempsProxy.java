package edt.umontp.fr;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.HashMap;

// FIXME à tester !

/**
 * EmploiDuTempsProxy est un classe qui permet de proposer un proxy pour l'emploi du temps
 *
 * @author emerick-biron, MathieuSoysal
 * @version 1.0
 * @see InterfaceEmploiDuTemps
 */
public class EmploiDuTempsProxy implements InterfaceEmploiDuTemps {
    private static EmploiDuTempsProxy singleton = null;
    private EmploiDuTemps emploiDuTemps;
    private EnumMap<Groupe, Planning> cacheGroupe;
    private HashMap<LocalDate, Planning> cacheDate;
    private HashMap<MultiKey<LocalDate, Groupe>, Planning> cacheDateGroupe;

    private EmploiDuTempsProxy() {
        emploiDuTemps = EmploiDuTemps.getInstance();
        actualiser();
    }

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

    @Override
    public Planning getPlanningOf(LocalDate date) {
        return cacheDate.computeIfAbsent(date, k -> emploiDuTemps.getPlanningOf(k));
    }

    @Override
    public Planning getPlanningOf(LocalDate date, Groupe groupe) {
        MultiKey<LocalDate, Groupe> multiKey = new MultiKey<>(date, groupe);
        return cacheDateGroupe.computeIfAbsent(multiKey, k -> emploiDuTemps.getPlanningOf(k.key1, k.key2));
    }

    @Override
    public Planning getPlanningOf(Groupe groupe) {
        return cacheGroupe.computeIfAbsent(groupe, k -> emploiDuTemps.getPlanningOf(k));
    }

    @Override
    public void actualiser() {
        cacheDate = new HashMap<>();
        cacheGroupe = new EnumMap<>(Groupe.class);
        cacheDateGroupe = new HashMap<>();
    }

    class MultiKey<K1, K2> {
        private K1 key1;
        private K2 key2;

        public MultiKey(K1 key1, K2 key2) {
            this.key1 = key1;
            this.key2 = key2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MultiKey key = (MultiKey) o;

            if (key1 != null ? !key1.equals(key.key1) : key.key1 != null) return false;
            if (key2 != null ? !key2.equals(key.key2) : key.key2 != null) return false;

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
