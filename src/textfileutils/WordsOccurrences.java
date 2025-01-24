package textfileutils;

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.Comparator;

public class WordsOccurrences {

    public static Map<String, Long> calculateOccurrences(File inputFile) {
        try {
            return new Scanner(inputFile)
                    .useDelimiter("[ \n;,':?!\"\\-.()\\[\\]{}<>/$#&%~`”“’]+")
                    .tokens()
                    .filter(s-> !(s.isEmpty()))
                    .map(String::toLowerCase)
                    .collect(Collectors.groupingBy(
                            s->s,
                            Collectors.counting()
                    ))
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (s1, s2) -> s1,
                            LinkedHashMap::new
                    ));
        }
        catch (IOException | NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static Map<String, Long> singleWordOccurences(File inputFile, String word) {
        try {
            return new Scanner(inputFile)
                    .useDelimiter("[ \n;,':?!\"\\-.()\\[\\]{}<>/$#&%~`”“’]+")
                    .tokens()
                    .map(String::toLowerCase)
                    .filter(s-> !(s.isEmpty()) && s.equalsIgnoreCase(word))
                    .collect(Collectors.groupingBy(
                            s->s,
                            Collectors.counting()
                    ));
        }
        catch (IOException | NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
