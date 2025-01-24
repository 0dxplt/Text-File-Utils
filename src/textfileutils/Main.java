package textfileutils;
import java.io.File;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Main <fileName>");
            System.exit(1);
        } else {
            Map<String, Long> map = WordsOccurrences.calculateOccurrences(new File(args[0]));
            if (map != null) {
                System.out.println(map);
            }
        }
    }
}

