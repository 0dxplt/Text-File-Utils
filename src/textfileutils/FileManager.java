package textfileutils;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;

public class FileManager {

    public static void printDirectory(File directory) {
        if (directory.exists()) {
            System.out.println("[" + directory.getAbsolutePath() + "]");
            Arrays.stream(directory.listFiles())
                    .filter(f -> f.isFile() && f.getName().endsWith(".txt"))
                    .sorted(Comparator.comparing(File::getName))
                    .forEach(f -> System.out.println("[*] " + f.getName()));
        }
    }

    public static File selectFile(File inputFile) {
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
            System.out.println("[" + inputFile.getAbsolutePath() + "]");
            return inputFile;
        }
        return inputFile;
    }

    public static <K,V> void saveToFile(Map<K,V> map) {
        System.out.print("[?] File Name: ");
        //Create a file that prints the output
    }

    public static <K,V> void printMap(Map<K,V> map) {
        System.out.println(map);
    }
}
