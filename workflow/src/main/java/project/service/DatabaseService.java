package project.service;

import java.util.ArrayList;
import java.util.List;
import project.model.UserRecord;

public class DatabaseService {

    public void saveUsers(List<UserRecord> users, String filePath) {
        CsvSaverService csvSaverService = new CsvSaverService();
        csvSaverService.saveUsersToCsv(users, filePath);
        System.out.println(users.size() + " utilisateurs sauvegardés dans la base de données.");
    }
}
