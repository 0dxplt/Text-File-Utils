package textfileutils;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Main <fileName>");
            System.exit(1);
        } else if (WordsOccurrences.countWords(new File(args[0])) != null) {
            System.out.println(WordsOccurrences.countWords(new File(args[0])));
        }
    }
}

