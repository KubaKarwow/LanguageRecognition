import java.util.Arrays;
import java.util.List;

public class Perceptron {

    private String name;
    private double[] weights = new double[27];

    private int agesPassedDuringLearning;
    private double accurancy;
    private final int MAX_AGES=1000_0;


    public Perceptron(String name) {
        this.name = name;
    }

    public Boolean clasify(String text) {
       return processAVector(convertStringsToCharacterOccurrences(text));
    }

    public void learn(List<TextAndLanguage> training) {
        double failedOnes=0;
        for (int i = 0; i < MAX_AGES; i++) {
            failedOnes=0;
            boolean everythingWorks = true;
            for (TextAndLanguage textAndLanguage : training) {
                CharsToLanguage charsToLanguage = new CharsToLanguage(
                        convertStringsToCharacterOccurrences(textAndLanguage.getText()),
                        textAndLanguage.getLanguage());
                int programOutput = processAVector(charsToLanguage.getCharacterOccurrences()) ? 1 : 0;
                int expectedOutput = this.name.equals(textAndLanguage.getLanguage()) ? 1 : 0;
                if (programOutput != expectedOutput) {
                    everythingWorks = false;
                    failedOnes++;
                }
                correctWeights(programOutput, expectedOutput, charsToLanguage.getCharacterOccurrences());
            }
            if (everythingWorks) {

                this.accurancy=1;
                this.agesPassedDuringLearning=i;
                return;
            }
        }

        agesPassedDuringLearning=MAX_AGES;
        accurancy=(MAX_AGES-failedOnes)/MAX_AGES;

    }

    private void correctWeights(int programOutput, int expectedOutput, double[] xVector) {
        for (int i = 0; i < weights.length; i++) {
            weights[i] += (expectedOutput - programOutput) * xVector[i];
        }
    }

    public boolean processAVector(double[] characterOccurrences) {
        int programOutput = 0;
        for (int i = 0; i < characterOccurrences.length; i++) {
            programOutput += characterOccurrences[i] * weights[i];
        }
        return programOutput >= 0;
    }


    private double[] convertStringsToCharacterOccurrences(String text) {
        double[] result = new double[27];
        String lowerCasedText = text.toLowerCase();
        // System.out.println(lowerCasedLine);
        // System.out.println(line);
        int count = 0;
        for (int i = 0; i < lowerCasedText.length(); i++) {
            //debug
            if ((int) lowerCasedText.charAt(i) >= 97 &&
                    (int) lowerCasedText.charAt(i) <= 122) {
                result[(int) lowerCasedText.charAt(i) - 97]++;
                count++;
            }
        }
        for (int i = 0; i < result.length; i++) {
            result[i] /= count;
        }
        result[26] = -1;
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    public int getAgesPassedDuringLearning() {
        return agesPassedDuringLearning;
    }

    public void setAgesPassedDuringLearning(int agesPassedDuringLearning) {
        this.agesPassedDuringLearning = agesPassedDuringLearning;
    }

    public double getAccurancy() {
        return accurancy;
    }

    public void setAccurancy(double accurancy) {
        this.accurancy = accurancy;
    }

    @Override
    public String toString() {
        return "Perceptron{" +
                "name='" + name + '\'' +
                ", weights=" + Arrays.toString(weights) +
                '}';
    }
}
