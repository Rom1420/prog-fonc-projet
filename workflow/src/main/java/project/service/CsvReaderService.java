package project.service;

import project.model.UserRecord;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CsvReaderService {
    public List<UserRecord> readCsv(String filePath) {
        List<UserRecord> users = new ArrayList<>();

        try {
            Path path = Paths.get(filePath).toAbsolutePath();

            try (BufferedReader br = Files.newBufferedReader(path)) {
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

