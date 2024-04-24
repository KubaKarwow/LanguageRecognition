import java.util.List;

public class TextAndLanguage {
    private String text;
    private String language;

    public TextAndLanguage(String text, String language) {
        this.text = text;
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "TextAndLanguage{" +
                "text='" + text + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
