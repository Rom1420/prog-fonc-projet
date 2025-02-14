package project.workflow;

import project.model.UserRecord;
import project.service.*;
import java.util.List;

public class DataWorkflow {
    public static void main(String[] args){
        // 1- Charger les utilisateurs depuis le fichier CSV
        CsvReaderService csvReader = new CsvReaderService();
        String fileName = "users.csv";
        List<UserRecord> users = csvReader.readCsv(fileName);

        if (users.isEmpty()) {
            System.out.println("Aucun utilisateur trouvé dans le fichier CSV.");
            return;
        }

        // 2 - Appliquer les transformations et filtres
        List<UserRecord> adults = DataTransformationService.filterByAge(users, 18);
        List<UserRecord> parisUsers = DataTransformationService.filterByCity(users, "Nice");
        List<UserRecord> usersUpperCase = DataTransformationService.transformNamesToUpperCase(users);

        // 3 - Affichage des résultats
        System.out.println("\nListe des utilisateurs adultes :");
        adults.stream().limit(5).forEach(System.out::println);

        System.out.println("\nListe des utilisateurs de Nice :");
        parisUsers.stream().limit(5).forEach(System.out::println);

        System.out.println("\nListe des utilisateurs avec noms en majuscules :");
        usersUpperCase.stream().limit(5).forEach(System.out::println);

        // 4 - Sauvegarde des utilisateurs filtrés et transformés dans la base de données
        DatabaseService databaseService = new DatabaseService();

        databaseService.saveUsers(adults);
        databaseService.saveUsers(parisUsers);
        databaseService.saveUsers(usersUpperCase);
    }
}