package project.model;

public record UserRecord(
        int id,
        String firstName,
        String lastName,
        String email,
        String gender,
        int age,
        String birthdate,
        String country,
        String city
) {}

