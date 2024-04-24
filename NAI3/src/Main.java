import javax.swing.*;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Perceptron> perceptrons = getPerceptrons("Jezyki");
        List<String> languages = perceptrons.stream().map(Perceptron::getName).toList();
        List<TextAndLanguage> training = new ArrayList<>();

        for (String language : languages) {
            List<String> texts = getDataFromFiles("Jezyki//" + language);
            texts.forEach(text -> training.add(new TextAndLanguage(text, language)));

        }
        for (Perceptron perceptron : perceptrons) {
            perceptron.learn(training);
        }
        for (Perceptron perceptron : perceptrons) {
            System.out.println(perceptron);
        }
        SwingUtilities.invokeLater(() -> new MyGui(languages,training,perceptrons));

        // test run
        List<String> tests = getDataFromFiles("Testowe");
        for (String text : tests) {
            System.out.println(text.substring(0, 200));
            boolean foundLanguage=false;
            for (Perceptron perceptron : perceptrons) {
                if (perceptron.clasify(text)) {
                    System.out.println("detected language:"+perceptron.getName());
                    foundLanguage=true;
                    break;
                }
            }
            if(!foundLanguage){
                System.out.println("couldnt clasify that");
            }
        }

    }

    public static List<String> getDataFromFiles(String fileName) throws IOException {
        List<String> result = new ArrayList<>();
        Files.walkFileTree(Path.of(fileName), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                result.add(Files.readAllLines(file)
                        .stream()
                        .collect(Collectors.joining()));
                return super.visitFile(file, attrs);
            }
        });
        return result;
    }

    public static List<Perceptron> getPerceptrons(String fileName) throws IOException {
        List<Perceptron> result = new ArrayList<>();
        Files.walkFileTree(Path.of(fileName), new SimpleFileVisitor<>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                if (!dir.getFileName().toString().equals("Jezyki")) {
                    result.add(new Perceptron(dir.getFileName().toString()));
                }
                return super.preVisitDirectory(dir, attrs);
            }

        });
        return result;
    }


}