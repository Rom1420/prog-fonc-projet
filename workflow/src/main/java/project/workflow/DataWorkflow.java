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
        List<UserRecord> niceUsers = DataTransformationService.filterByCity(users, "Nice");
        List<UserRecord> usersUpperCase = DataTransformationService.transformNamesToUpperCase(users);

        // Affichage des résultats
        System.out.println("\nListe des utilisateurs adultes :");
        adults.stream().limit(5).forEach(System.out::println);

        System.out.println("\nListe des utilisateurs de Nice :");
        niceUsers.stream().limit(5).forEach(System.out::println);

        double averageAge = DataTransformationService.calculateAverageAge(niceUsers);
        System.out.println("Moyenne d'âge des Niçois : " + averageAge);

        // Fusionner deux flux (adultes + utilisateurs Gmail)
        List<UserRecord> gmailUsers = DataTransformationService.filterByEmailDomain(users, "@gmail.com");
        List<UserRecord> mergedUsers = MergeNodeService.mergeUserStreams(adults, niceUsers);
        System.out.println("Fusion adultes + Nice : " + mergedUsers.size());

        // Sauvegarde des utilisateurs filtrés et transformés dans la base de données
        DatabaseService databaseService = new DatabaseService();

        databaseService.saveUsers(adults,"adults.csv");
        databaseService.saveUsers(niceUsers, "nice-users.csv");
        databaseService.saveUsers(mergedUsers,"adults-and-nice.csv");

        // Calculer la proportion d'adultes ayant plus de 40 ans
        long adultsOver40Count = mergedUsers.stream()
                .filter(user -> user.age() > 40)
                .count();
        double proportionAdultsOver40 = (double) adultsOver40Count / adults.size() * 100;
        System.out.println("Part des adultes ayant plus de 40 ans : " + String.format("%.2f", proportionAdultsOver40) + "%");

        // Calculer la proportion des utilisateurs de Nice ayant plus de 40 ans
        long niceUsersOver40Count = mergedUsers.stream()
                .filter(user -> niceUsers.contains(user) && user.age() > 40)
                .count();
        double proportionNiceUsersOver40 = (double) niceUsersOver40Count / niceUsers.size() * 100;
        System.out.println("Part des utilisateurs de Nice ayant plus de 40 ans : " + String.format("%.2f", proportionNiceUsersOver40) + "%");

        // afficher la part des utilisateurs nicois ayannt plus de 40 ans par rapports aux users de 40ans
    }
}