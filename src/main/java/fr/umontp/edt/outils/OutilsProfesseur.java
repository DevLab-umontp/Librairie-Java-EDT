package fr.umontp.edt.outils;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

import fr.umontp.edt.Professeur;

/**
 * <b>ProfesseurOutils est la classe utilitaire pour le fonctionnement interne
 * de la class {@link Professeur}.</b>
 * 
 * @see Professeur
 * 
 * @author MathieuSoysal
 * @version 1.0.0
 */
public class OutilsProfesseur {
	private OutilsProfesseur() {
		throw new IllegalStateException("Class utilitaire");
	}

	/**
	 * @param str {@code String} auquel l'on doit enlever l'accentuation
	 * 
	 * @return {@code String} sans accentuation.
	 * 
	 * @see Normalizer
	 * 
	 * @since 1.0.0
	 */
	public static String supprimerAccentuation(String str) {
		String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(nfdNormalizedString).replaceAll("");
	}

	/**
	 * Concatène les deux variables {@code nom} et {@code prenom}, et renvoie une
	 * variable formaté pour la class {@link Professeur}.
	 * 
	 * @param nom du professeur
	 * @param prenom du professeur
	 * 
	 * @return {@code String} formaté pour la class {@link Professeur}.
	 * 
	 * @see String#toUpperCase(Locale)
	 * @see OutilsProfesseur#supprimerAccentuation(String)
	 * 
	 * @since 1.0.0
	 */
	public static String formater(String nom, String prenom) {
		return OutilsProfesseur.formater(nom + "   " + prenom);
	}

	/**
	 * @param nomPrenom non et prénom du professeur séparé par 3 espaces.
	 * 
	 * @return la variable {@code nomPrenom} formaté pour la class
	 *         {@link Professeur}.
	 * 
	 * @see String#toUpperCase(Locale)
	 * @see OutilsProfesseur#supprimerAccentuation(String)
	 * 
	 * @since 1.0.0
	 */
	public static String formater(String nomPrenom) {
		nomPrenom = supprimerAccentuation(nomPrenom);
		return nomPrenom.toUpperCase(Locale.FRANCE).replaceAll("[^A-Z ]|(?<=\\S)\\s(?=\\S)", "");
	}

}
