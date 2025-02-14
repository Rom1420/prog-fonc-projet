package project.service;

import project.model.UserRecord;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergeNodeService {

    // Fusionner deux flux de données
    public static List<UserRecord> mergeUserStreams(List<UserRecord> stream1, List<UserRecord> stream2) {
        return Stream.concat(stream1.stream(), stream2.stream())
                .distinct()
                .collect(Collectors.toList());
    }
}
