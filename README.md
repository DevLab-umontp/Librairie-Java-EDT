[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=DevLab-umontp_API-JAVA-EDT&metric=alert_status)](https://sonarcloud.io/dashboard?id=DevLab-umontp_API-JAVA-EDT)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=DevLab-umontp_API-JAVA-EDT&metric=coverage)](https://sonarcloud.io/dashboard?id=DevLab-umontp_API-JAVA-EDT)
![GitHub Actions](https://github.com/DevLab-umontp/API-JAVA-EDT/workflows/Java%20CI%20with%20Maven/badge.svg)
[![Javadoc](https://img.shields.io/badge/JavaDoc-Online-green)](https://mathieusoysal.github.io/stats/api-java-edt)
[![Gitmoji.dev](https://img.shields.io/badge/gitmoji-%20😜%20😍-FFDD67.svg?style=flat-square)](https://gitmoji.dev)
# ![](ressources/devicon.png) Librairie Java : EDT IUT Montpellier

### IUT Montpellier-Sète – Département Informatique
* **Colaborateurs:** [étudiants du département informatique](https://iut-montpellier-sete.edu.umontpellier.fr/dut-informatique/)


## Intégrer la Librairie Java EDT IUT Montpellier dans votre code

### Maven 

Si vous avez Maven, utilisez le code suivant :

```xml
<dependency>
  <groupId>com.github.devlab-umontp</groupId>
  <artifactId>edt-iut-umontp</artifactId>
  <version>1.0.0</version>
</dependency>
```
>*Voir un exemple de fichier [pom.xml](https://github.com/DevLab-umontp/Librarie-Java-EDT/blob/main/ressources/pom.xml#L20L24) avec la librairie EDT IUT Montpellier*
### Gradle

Si vous utilisez Gradle, ajoutez ce qui suit aux dépendances de votre fichier `build.gradle`:

```
    implementation 'com.github.devlab-umontp:edt-iut-umontp:1.0.0'
```

## Exemple de code d'utilisation de la Librairie Java EDT IUT Montpellier

```java
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
    }
}
```
