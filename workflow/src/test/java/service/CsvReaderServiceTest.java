package service;
import org.junit.jupiter.api.Test;
import project.model.UserRecord;
import project.service.CsvReaderService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class CsvReaderServiceTest {

    @Test
    void testCsvReader() {
        CsvReaderService csvReader = new CsvReaderService();
        List<UserRecord> users = csvReader.readCsv("users.csv");

        // Vérifier que la liste n'est pas vide
        assertNotNull(users, "La liste d'utilisateurs ne doit pas être null");
        assertFalse(users.isEmpty(), "La liste d'utilisateurs ne doit pas être vide");
    }
}

