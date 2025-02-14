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
        List<UserRecord> users = csvReader.readCsv("C:\\Users\\abbon\\OneDrive\\Documents\\SI4\\prog fonc\\prog-fonc-projet\\workflow\\src\\resources\\users.csv");

        // Vérifier que la liste n'est pas vide
        assertNotNull(users, "La liste d'utilisateurs ne doit pas être null");
        assertFalse(users.isEmpty(), "La liste d'utilisateurs ne doit pas être vide");
        // Vérifier le premier utilisateur
        UserRecord firstUser = users.get(0);
        assertEquals(1, firstUser.id());
        assertEquals("Britteny", firstUser.firstName());
        assertEquals("Laxton", firstUser.lastName());
        assertEquals("blaxton0@who.int", firstUser.email());
        assertEquals("Non-binary", firstUser.gender());
        assertEquals(76, firstUser.age());
        assertEquals("01/11/1956", firstUser.birthdate());
        assertEquals("China", firstUser.country());
        assertEquals("Puning", firstUser.city());
    }
}

