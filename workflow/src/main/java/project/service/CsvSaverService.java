package project.service;

import project.model.UserRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvSaverService {

    public static void saveUsersToCsv(List<UserRecord> users, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Écrire l'en-tête du fichier CSV
            writer.append("id,first_name,last_name,email,gender,age,birthdate,country,city\n");

            // Écrire chaque utilisateur
            for (UserRecord user : users) {
                writer.append(user.id() + ",")
                        .append(user.firstName() + ",")
                        .append(user.lastName() + ",")
                        .append(user.email() + ",")
                        .append(user.gender() + ",")
                        .append(user.age() + ",")
                        .append(user.birthdate().toString() + ",")
                        .append(user.country() + ",")
                        .append(user.city() + "\n");
            }

            System.out.println("Fichier exporté avec succès : " + filePath);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'exportation du CSV : " + e.getMessage());
        }
    }
}
