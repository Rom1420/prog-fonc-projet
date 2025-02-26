package project.service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import project.model.UserRecord;

public class DataTransformationService {

    // Filtrer les utilisateurs par âge
    public static List<UserRecord> filterByAge(List<UserRecord> users, int minAge) {
        return users.parallelStream()
                .filter(user -> user.age() >= minAge)
                .collect(Collectors.toList());
    }

    // Filtrer les utilisateurs par ville
    public static List<UserRecord> filterByCity(List<UserRecord> users, String city) {
        return users.parallelStream()
                .filter(user -> user.city().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    // Transformer les noms en majuscules
    public static List<UserRecord> transformNamesToUpperCase(List<UserRecord> users) {
        return users.parallelStream()
                .map(user -> new UserRecord(
                        user.id(),
                        user.firstName().toUpperCase(),
                        user.lastName().toUpperCase(),
                        user.email(),
                        user.gender(),
                        user.age(),
                        user.country(),
                        user.city()
                ))
                .collect(Collectors.toList());
    }
    // Filtrer les utilisateurs par genre
    public static List<UserRecord> filterByGender(List<UserRecord> users, String gender) {
        return users.parallelStream()
                .filter(user -> user.gender().equalsIgnoreCase(gender))
                .collect(Collectors.toList());
    }

    // Filtrer les utilisateurs par pays
    public static List<UserRecord> filterByCountry(List<UserRecord> users, String country) {
        return users.parallelStream()
                .filter(user -> user.country().equalsIgnoreCase(country))
                .collect(Collectors.toList());
    }

    // Filtrer par domaine d'email
    public static List<UserRecord> filterByEmailDomain(List<UserRecord> users, String domain) {
        return users.parallelStream()
                .filter(user -> user.email().endsWith(domain))
                .collect(Collectors.toList());
    }

    // Calculer la moyenne d'âge
    public static double calculateAverageAge(List<UserRecord> users) {
        return users.parallelStream()
                .mapToInt(UserRecord::age)
                .average()
                .orElse(0.0);
    }

    // Compter le nombre d’utilisateurs par ville
    public static Map<String, Long> countUsersByCity(List<UserRecord> users) {
        return users.parallelStream()
                .collect(Collectors.groupingBy(UserRecord::city, Collectors.counting()));
    }

    // Fusionner deux flux et calculer la moyenne d'âge combinée
    public static double mergeAndCalculateAverageAge(List<UserRecord> users1, List<UserRecord> users2) {
        List<UserRecord> mergedList = Stream.concat(users1.parallelStream(), users2.parallelStream())
                .collect(Collectors.toList());
        return calculateAverageAge(mergedList);
    }
}
