package textfileutils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
        String result = printMap(map);
        System.out.print("[?] Choose a name for the output file: ");
        File file = new File(new Scanner(System.in).next());
        System.out.println("Do you want to [a] append or [o] overwrite?");
        switch (new Scanner(System.in).next()) {
            case "a":
                try (FileWriter fw = new FileWriter(file, true)) {
                    fw.write("[" + new Date() + "]\n");
                    fw.write(result);
                    fw.write("\n-------------------\n");
                }
                catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("[*] Error writing to file");
                }
                break;
            case "o":
            default:
                try (FileWriter fw = new FileWriter(file, false)) {
                    fw.write("[" + new Date() + "]\n");
                    fw.write(result);
                    fw.write("\n-------------------\n");
                }
                catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("[*] Error writing to file");
                }
                break;
        }
    }

    public static <K,V> String printMap(Map<K,V> map) {
        return map.entrySet().stream()
                .map(e -> new StringBuilder(String.valueOf(e.getKey())).append(" ").append(e.getValue()).toString())
                .collect(Collectors.joining("\n"));
    }
}
