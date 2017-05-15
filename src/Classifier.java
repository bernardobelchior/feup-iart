import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

import java.util.ArrayList;

public class Classifier {
    MultilayerPerceptron classifier;
    private ArrayList<String> hiddenLayers = new ArrayList<>();

    Classifier() {
        classifier = new MultilayerPerceptron();
    }

    void setHiddenLayers(String hiddenLayers) {
        classifier.setHiddenLayers(hiddenLayers);
    }

    void addHiddenLayer(String hiddenLayer) throws Exception {
        if (!(hiddenLayer.equals("a") || hiddenLayer.equals("i") || hiddenLayer.equals("t") || hiddenLayer.equals("o")))
            throw new Exception("Invalid option in hidden layer: \"" + hiddenLayer + "\".");

        try {
            Integer.parseUnsignedInt(hiddenLayer);
        } catch (NumberFormatException e) {
            throw new Exception("Invalid option in hidden layer: \"" + hiddenLayer + "\".");
        }

        hiddenLayers.add(hiddenLayer);
    }

    void setLearningRate(double learningRate) {
        classifier.setLearningRate(learningRate);
    }

    public void setMomentum(double momentum) {
        classifier.setMomentum(momentum);
    }

    public void setEpochs(int epochs) {
        classifier.setTrainingTime(epochs);
    }

    public void setValidationSetSize(int validationSetSize) {
        classifier.setValidationSetSize(validationSetSize);
    }

    void setGUI(boolean gui) {
        classifier.setGUI(gui);
    }

    void start(Instances data) throws Exception {
        if (hiddenLayers.size() > 0)
            classifier.setHiddenLayers(String.join(",", hiddenLayers));

        classifier.buildClassifier(data);
    }
}
