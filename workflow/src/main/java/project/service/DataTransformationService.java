package project.service;

import java.util.List;
import java.util.stream.Collectors;
import project.model.UserRecord;

public class DataTransformationService {

    // Filtrer les utilisateurs par âge (ex : >= 18 ans)
    public static List<UserRecord> filterByAge(List<UserRecord> users, int minAge) {
        return users.stream()
                .filter(user -> user.age() >= minAge)
                .collect(Collectors.toList());
    }

    // Filtrer les utilisateurs par ville
    public static List<UserRecord> filterByCity(List<UserRecord> users, String city) {
        return users.stream()
                .filter(user -> user.city().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    // Transformer les noms en majuscules
    public static List<UserRecord> transformNamesToUpperCase(List<UserRecord> users) {
        return users.stream()
                .map(user -> new UserRecord(
                        user.id(),
                        user.firstName().toUpperCase(),
                        user.lastName().toUpperCase(),
                        user.email(),
                        user.gender(),
                        user.age(),
                        user.birthdate(),
                        user.country(),
                        user.city()
                ))
                .collect(Collectors.toList());
    }
}
