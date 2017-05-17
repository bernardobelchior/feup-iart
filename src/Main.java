import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class Main {
    public static void main(String... args) throws Exception {
        Parser parser = new Parser();
        NeuralNetwork neuralNetwork = new NeuralNetwork();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String answer;

        System.out.println("Neural Network for Phishing Websites Detection");
        System.out.println("This system uses a Neural Network with Backpropagation for result prediction.");

        System.out.println("Neural Network Configuration:");

        if (args.length > 0)
            neuralNetwork.setNumFolds(Integer.parseUnsignedInt(args[0]));

        if (args.length < 2 || !parser.parseDataFromSource(args[1])) {
            do {
                System.out.print("Path to dataset file: ");
                answer = reader.readLine();
            } while (!parser.parseDataFromSource(answer));

        }

        neuralNetwork.setData(parser.getData());

        if (args.length < 3 || !runClassifiers(neuralNetwork, args[2])) {
            readConfigurationFromStdIn(reader, neuralNetwork);
            System.out.println("Starting classifier...");
            neuralNetwork.classify();
            System.out.println("Classifier finished successfully!");
            System.out.println(neuralNetwork.getModelInfo());
        }

        reader.close();
    }

    private static boolean runClassifiers(NeuralNetwork network, String configurationFile) throws Exception {
        ResultCSVFormatter csvFormatter = new ResultCSVFormatter(network);
        ConfigurationReader configReader = new ConfigurationReader(network);

        configReader.readConfigFrom(configurationFile);

        System.out.println("Starting batch neural networks...");

        while (configReader.hasNextConfig()) {
            configReader.nextConfig();
            System.out.println(new Date().toString() + ": Classifying configuration " + configReader.getConfigNumber() + " with options: " + configReader.getCurrentConfig());
            network.classify();

            csvFormatter.exportToCSV(new Date().toString() + ".csv");
        }

        System.out.println("Batch neural networks finished successfully...");


        return true;
    }

    private static void readConfigurationFromStdIn(BufferedReader reader, NeuralNetwork neuralNetwork) throws IOException {
        String answer;

        do {
            System.out.println("Do you want to see a GUI? (yes/no)");
            answer = reader.readLine();

            if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
                neuralNetwork.setGUI(true);
                break;
            } else if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")) {
                neuralNetwork.setGUI(false);
                break;
            }

            System.out.println("Invalid option. Valid options are \"yes\" or \"no\".");
        } while (true);

        System.out.println("Set the number of nodes or wildcards in each of hidden layers separated by commas.");
        System.out.println("Wildcards: 't' = no. of attributes + no. of classes");
        System.out.println("           'a' = (no. of attributes + no. of classes) / 2");
        System.out.println("           'i' = no. of attributes");
        System.out.println("           'o' = no. of classes");
        System.out.print("Answer: ");

        neuralNetwork.setHiddenLayers(reader.readLine());

        System.out.print("Set learning rate ]0, 1]: ");
        neuralNetwork.setLearningRate(Double.parseDouble(reader.readLine()));

        System.out.print("Set momentum ]0, 1]: ");
        neuralNetwork.setMomentum(Double.parseDouble(reader.readLine()));

        System.out.print("Set number of epochs: ");
        neuralNetwork.setEpochs(Integer.parseUnsignedInt(reader.readLine()));

        System.out.print("Set number of folds: ");
        neuralNetwork.setNumFolds(Integer.parseUnsignedInt(reader.readLine()));
    }
}
