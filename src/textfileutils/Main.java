package textfileutils;
import java.io.File;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Main <filePath/dirPath>");
            System.exit(1);
        } else {
            File file = new File(args[0]);
            file = FileManager.selectFile(file);
            System.out.println("[1] Every Word Occurrences");
            System.out.println("[2] Single Word Occurrences");
            System.out.print("[?] Choice: ");
            Map<String, Long> map;
            switch (new Scanner(System.in).nextInt()) {
                case 2:
                    System.out.print("[?] Word: ");
                    String word = new Scanner(System.in).next();
                    map = WordsOccurrences.singleWordOccurences(file, word);
                    break;
                case 1:
                default:
                    map = WordsOccurrences.calculateOccurrences(file);
                    break;
            }
            if (map != null) {
                System.out.println("Do you want to save the output? (y/n)");
                switch (new Scanner(System.in).next()) {
                    case "n":
                        System.out.println(FileManager.printMap(map));
                        System.out.println("Thank you for using this program!");
                        break;
                    case "y":
                    default:
                        FileManager.saveToFile(map);
                        break;
                }
            } else {
                System.out.println("A problem occured. Please try again.");
            }
        }
    }
}

