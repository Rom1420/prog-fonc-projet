package project.service;

import project.model.UserRecord;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service de lecture et de transformation des données CSV en objets UserRecord.
 * <p>
 * Cette classe permet de lire un fichier CSV contenant des informations sur des utilisateurs,
 * de convertir chaque ligne en un objet UserRecord, et de renvoyer une liste de ces objets.
 * Elle utilise le parallélisme avec parallelStream() pour améliorer les performances de
 * traitement des données en lisant et transformant les lignes du fichier CSV de manière parallèle.
 *
 **/
public class CsvReaderService {
    public List<UserRecord> readCsv(String resourcePath) {
        List<UserRecord> users = new ArrayList<>();

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
            if (inputStream == null) {
                throw new FileNotFoundException("Fichier non trouvé : " + resourcePath);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                boolean isFirstLine = true;
                List<String> lines = new ArrayList<>();

                while ((line = br.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }
                    lines.add(line);
                }

                // Utiliser parallelStream pour traiter les lignes en parallèle
                users = lines.parallelStream()
                        .map(this::convertToUserRecord)
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    // Méthode de conversion d'une ligne CSV en un UserRecord
    private UserRecord convertToUserRecord(String line) {
        String[] data = line.split(",");
        return new UserRecord(
                Integer.parseInt(data[0]), // id
                data[1],  // first_name
                data[2],  // last_name
                data[3],  // email
                data[4],  // gender
                Integer.parseInt(data[5]), // age
                data[6],  // birthdate
                data[7],  // country
                data[8]   // city
        );
    }
}
