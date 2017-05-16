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

    public String getResultsAsCSV(int iteration) {
        StringBuilder sb = new StringBuilder();
        sb.append(iteration);
        sb.append(",");
        sb.append(classifier.getLearningRate());
        sb.append(",");
        sb.append(classifier.getMomentum());
        sb.append(",");
        sb.append(classifier.getTrainingTime());
        sb.append(",");
        sb.append(classifier.getHiddenLayers());
        sb.append(",");
        sb.append(evaluation.correct());
        sb.append(",");
        sb.append(evaluation.incorrect());
        sb.append(",");
        sb.append(evaluation.kappa());
        sb.append(",");
        sb.append(evaluation.meanAbsoluteError());
        sb.append(",");
        sb.append(evaluation.rootMeanSquaredError());
        sb.append(",");
        try {
            sb.append(evaluation.relativeAbsoluteError());
        } catch (Exception e) {
            sb.append("ERROR");
        }
        sb.append(",");
        sb.append(evaluation.rootRelativeSquaredError());
        sb.append(",");
        sb.append(evaluation.numInstances());
        sb.append(",");
        return sb.toString();
    }
}
