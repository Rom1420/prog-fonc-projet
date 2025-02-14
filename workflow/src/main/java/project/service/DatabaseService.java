package project.service;

import java.util.ArrayList;
import java.util.List;
import project.model.UserRecord;

public class DatabaseService {

    private final List<UserRecord> database = new ArrayList<>();

    public void saveUsers(List<UserRecord> users) {
        database.addAll(users);
        System.out.println(users.size() + " utilisateurs sauvegardés dans la base de données.");
    }

    public List<UserRecord> getAllUsers() {
        return new ArrayList<>(database);
    }
}
