package textfileutils;
import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.Comparator;

public class WordsOccurrences {

    public static void printDirectory(File directory) {
        if (directory.exists()) {
            System.out.println("Current directory: " + directory.getAbsolutePath());
            Arrays.stream(directory.listFiles())
                    .filter(File::isDirectory)
                    .sorted(Comparator.comparing(File::getName))
                    .forEach(f -> System.out.println(f.getName() + "\t[Directory]"));
            Arrays.stream(directory.listFiles())
                    .filter(File::isFile)
                    .sorted(Comparator.comparing(File::getName))
                    .forEach(f -> System.out.println(f.getName() + "\t[File]"));
        }
    }

    public static Map<String, Long> countWords(File inputFile) {
        try {
            System.out.println("Reading words from " + inputFile.getAbsolutePath());
            if (!inputFile.exists()) {
                System.out.println("File not found");
                return null;
            }
            if (inputFile.isFile() && inputFile.length() == 0) {
                System.out.println("File is empty");
                return null;
            }
            while (inputFile.isDirectory() && inputFile.list() != null) {
                if (inputFile.listFiles().length == 0) {
                    System.out.println("Folder is empty");
                    return null;
                }
                printDirectory(inputFile);
                System.out.print("[>] ");
                inputFile = new File(new StringBuilder(inputFile.getAbsolutePath()).append("/").append(new Scanner(System.in).next()).toString());
            }
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
}
