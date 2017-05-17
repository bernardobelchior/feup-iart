import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ResultCSVFormatter {
    private final static String HEADER = "\"Configuration Number\",\"Options\",,,,\"Classified Instances\",,,,\"Kappa Statistic\",\"Mean Absolute Error\",\"Root Mean Squared Error\",\"Relative Absolute Error\",\"Root Relative Absolute Error\",\"Total Number of Instances\"\n,\"Learning Rate\",\"Momentum\",\"Epochs\",\"Hidden Layers\",\"Correct\",\"Correct %\",\"Incorrect\",\"Incorrect %\",,,,,,\n";
    private NeuralNetwork network;
    private BufferedWriter csvWriter;

    ResultCSVFormatter(NeuralNetwork network) {
        this.network = network;
    }

    public void exportToCSV(String filename) throws IOException {
        if (csvWriter == null) {
            csvWriter = new BufferedWriter(new FileWriter(filename));
            csvWriter.write(HEADER);
        }

        csvWriter.write(getNetworkInfoAsCSV());
        csvWriter.write("\n");
        csvWriter.flush();
    }

    public void close() {
        if (csvWriter != null) {
            try {
                csvWriter.close();
            } catch (IOException ignored) {
            }
        }
    }

    private String getNetworkInfoAsCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(",");
        sb.append(network.getLearningRate());
        sb.append(",");
        sb.append(network.getMomentum());
        sb.append(",");
        sb.append(network.getTrainingTime());
        sb.append(",");
        sb.append(network.getHiddenLayers());
        sb.append(",");
        sb.append(network.correct());
        sb.append(",");
        sb.append(",");
        sb.append(network.incorrect());
        sb.append(",");
        sb.append(",");
        sb.append(network.kappa());
        sb.append(",");
        sb.append(network.meanAbsoluteError());
        sb.append(",");
        sb.append(network.rootMeanSquaredError());
        sb.append(",");

        try {
            sb.append(network.relativeAbsoluteError());
        } catch (Exception e) {
            sb.append("ERROR");
        }

        sb.append(",");
        sb.append(network.rootRelativeSquaredError());
        sb.append(",");
        sb.append(network.numInstances());
        sb.append(",");
        sb.append(",");
        return sb.toString();
    }
}
