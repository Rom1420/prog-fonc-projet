package project.workflow;

import project.model.UserRecord;
import project.service.*;
import java.util.List;

public class DataWorkflow {
    public static void main(String[] args){
        // Charger les utilisateurs depuis le fichier CSV
        CsvReaderService csvReader = new CsvReaderService();
        String fileName = "users.csv";
        String outputCsvPath = "output.csv";
        List<UserRecord> users = csvReader.readCsv(fileName);

        if (users.isEmpty()) {
            System.out.println("Aucun utilisateur trouvé dans le fichier CSV.");
            return;
        }

        // Appliquer les transformations et filtres
        List<UserRecord> adults = DataTransformationService.filterByAge(users, 18);
        List<UserRecord> parisUsers = DataTransformationService.filterByCity(users, "Nice");
        List<UserRecord> usersUpperCase = DataTransformationService.transformNamesToUpperCase(users);

        // Affichage des résultats
        System.out.println("\nListe des utilisateurs adultes :");
        adults.stream().limit(5).forEach(System.out::println);

        System.out.println("\nListe des utilisateurs de Nice :");
        parisUsers.stream().limit(5).forEach(System.out::println);

        System.out.println("\nListe des utilisateurs avec noms en majuscules :");
        usersUpperCase.stream().limit(5).forEach(System.out::println);

        double averageAge = DataTransformationService.calculateAverageAge(parisUsers);
        System.out.println("Moyenne d'âge des Parisiens : " + averageAge);

        // Fusionner deux flux (adultes + utilisateurs Gmail)
        List<UserRecord> gmailUsers = DataTransformationService.filterByEmailDomain(users, "@gmail.com");
        List<UserRecord> mergedUsers = MergeNodeService.mergeUserStreams(adults, gmailUsers);
        System.out.println("Fusion adultes + Gmail : " + mergedUsers.size());

        // Sauvegarde des utilisateurs filtrés et transformés dans la base de données
        DatabaseService databaseService = new DatabaseService();

        databaseService.saveUsers(adults);
        databaseService.saveUsers(parisUsers);
        databaseService.saveUsers(usersUpperCase);

        CsvSaverService.saveUsersToCsv(mergedUsers, outputCsvPath);
    }
}