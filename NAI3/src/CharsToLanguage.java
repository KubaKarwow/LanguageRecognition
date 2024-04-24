import java.util.Arrays;

public class CharsToLanguage {
    private double[] characterOccurrences;
    private String language;

    public CharsToLanguage(double[] characterOccurrences, String language) {
        this.characterOccurrences = characterOccurrences;
        this.language = language;
    }

    public double[] getCharacterOccurrences() {
        return characterOccurrences;
    }

    public void setCharacterOccurrences(double[] characterOccurrences) {
        this.characterOccurrences = characterOccurrences;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "CharsToLanguage{" +
                "tab=" + Arrays.toString(characterOccurrences) +
                ", language='" + language + '\'' +
                '}';
    }
}
