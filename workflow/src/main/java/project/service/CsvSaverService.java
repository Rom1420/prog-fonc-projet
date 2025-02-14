package project.service;

import project.model.UserRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class CsvSaverService {

    public static void saveUsersToCsv(List<UserRecord> users, String filePath) {
        String outputDirectory = "src/main/resources/output/";
        String fullPath = Paths.get(outputDirectory, filePath).toString();
        System.out.println(fullPath);
        try (FileWriter writer = new FileWriter(fullPath)) {
            writer.append("id,first_name,last_name,email,gender,age,birthdate,country,city\n");

            // Écrire chaque utilisateur
            for (UserRecord user : users) {
                writer.append(user.id() + ",")
                        .append(user.firstName() + ",")
                        .append(user.lastName() + ",")
                        .append(user.email() + ",")
                        .append(user.gender() + ",")
                        .append(user.age() + ",")
                        .append(user.country() + ",")
                        .append(user.city() + "\n");
            }

            System.out.println("Fichier exporté avec succès : " + fullPath);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'exportation du CSV : " + e.getMessage());
        }
    }
}
