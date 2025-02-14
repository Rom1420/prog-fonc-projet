package project.service;

import project.model.UserRecord;

import java.io.*;
import java.util.*;

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
                while ((line = br.readLine()) != null) {
                    if (isFirstLine) {
                        isFirstLine = false;
                        continue;
                    }
                    String[] data = line.split(",");

                    UserRecord user = new UserRecord(
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
                    users.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}
