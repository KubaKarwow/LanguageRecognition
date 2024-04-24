import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MyGui extends JFrame {
    private JPanel panel1;
    private JComboBox<String> AvailableLanguages;
    private JButton letsGoButton;
    private JTextArea InsertText;
    private JPanel topPanel;
    private JPanel botPanel;
    private JLabel Ages;
    private JLabel Training_Acc;
    private JLabel Detected_lang;
    private JLabel agesLabel;
    private JLabel accLabel;
    private JLabel langLabel;

    private List<TextAndLanguage> training;
    private List<Perceptron> perceptrons;
    private List<String> tests;
    private boolean foundAnswer;

    public MyGui(List<String> languages, List<TextAndLanguage> training, List<Perceptron> perceptrons) throws HeadlessException {
        String[] languagesToShow = new String[languages.size()];
        for (int i = 0; i < languagesToShow.length; i++) {
            languagesToShow[i] = languages.get(i);
        }
        topPanel.add(new JLabel("Available Languages"));
        AvailableLanguages = new JComboBox<>(languagesToShow);
        topPanel.add(AvailableLanguages);
        letsGoButton.setFocusable(false);


        //button listenrer
        letsGoButton.addActionListener(e -> {
            // test run
            String text = InsertText.getText();
            foundAnswer=false;
            for (Perceptron perceptron : perceptrons) {
                if (perceptron.clasify(text)) {
                    langLabel.setText(perceptron.getName());
                    accLabel.setText(perceptron.getAccurancy() * 100 + "%");
                    agesLabel.setText(perceptron.getAgesPassedDuringLearning() + "");
                    foundAnswer=true;
                    break;
                }
            }
            if(!foundAnswer){
                langLabel.setText("Couldn't classify to any language");
                accLabel.setText("");
                agesLabel.setText("");
            }
        });
        this.setPreferredSize(new Dimension(550, 400));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.add(panel1);
        this.pack();
        this.setVisible(true);
    }


}
