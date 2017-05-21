import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

import java.util.Random;

public class NeuralNetwork {
    private final MultilayerPerceptron classifier;
    private Evaluation evaluation;
    private int numFolds;
    private Instances data;

    NeuralNetwork() {
        classifier = new MultilayerPerceptron();
    }

    void setHiddenLayers(String hiddenLayers) {
        classifier.setHiddenLayers(hiddenLayers);
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

    void setGUI(boolean gui) {
        classifier.setGUI(gui);
    }

    void setNumFolds(int numFolds) {
        this.numFolds = numFolds;
    }

    void classify() throws Exception {
        evaluation = new Evaluation(data);
        evaluation.crossValidateModel(classifier, data, numFolds, new Random());
    }

    String getModelInfo() {
        return String.join(" ", classifier.getOptions()) + "\n" + evaluation.toSummaryString();
    }

    public void setOptions(String options) throws Exception {
        classifier.setOptions(options.split(" "));
    }

    public void setData(Instances data) {
        this.data = data;
    }

    public double getLearningRate() {
        return classifier.getLearningRate();
    }

    public double getMomentum() {
        return classifier.getMomentum();
    }

    public int getTrainingTime() {
        return classifier.getTrainingTime();
    }

    public String getHiddenLayers() {
        return classifier.getHiddenLayers();
    }

    public double correct() {
        return evaluation.correct();
    }

    public double incorrect() {
        return evaluation.incorrect();
    }

    public double kappa() {
        return evaluation.kappa();
    }

    public double meanAbsoluteError() {
        return evaluation.meanAbsoluteError();
    }

    public double rootMeanSquaredError() {
        return evaluation.rootMeanSquaredError();
    }

    public double relativeAbsoluteError() throws Exception {
        return evaluation.relativeAbsoluteError() / 100f;
    }

    public double rootRelativeSquaredError() {
        return evaluation.rootRelativeSquaredError() / 100f;
    }

    public double numInstances() {
        return evaluation.numInstances();
    }
}
