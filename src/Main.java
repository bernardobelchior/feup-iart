import java.io.*;
import java.util.ArrayList;
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
            readConfiguration(reader, neuralNetwork);
            System.out.println("Starting classifier...");
            neuralNetwork.classify();
            System.out.println("Classifier finished successfully!");

            System.out.println(neuralNetwork.getModelInfo());
        }

        reader.close();
    }

    private static boolean runClassifiers(NeuralNetwork network, String configurationFile) throws Exception {
        BufferedReader reader;
        BufferedWriter log;
        ArrayList<String> configurations = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(configurationFile));
            log = new BufferedWriter(new FileWriter(new Date().toString() + ".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }


        System.out.println("Reading configuration file...");
        String line;
        while ((line = reader.readLine()) != null)
            configurations.add(line);
        reader.close();
        System.out.println("Finished reading configuration file.");


        System.out.println("Starting batch neural networks...");
        int iteration = 0;
        for (String config : configurations) {
            iteration++;
            network.setOptions(config);
            String startDate = new Date().toString();
            System.out.println(startDate + ": Classifying configuration " + iteration + "..");
            network.classify();
            log.write("=================================== Testing configuration " + iteration + " ===================================\n");
            log.write("Started: " + startDate);
            log.write("Finished: " + new Date().toString());
            log.write(network.getModelInfo());
            log.write("=================================== End of configuration " + iteration + " ===================================\n\n");
            log.flush();
        }
        System.out.println("Batch neural networks finished successfully...");

        log.close();

        return true;
    }

    private static void readConfiguration(BufferedReader reader, NeuralNetwork neuralNetwork) throws IOException {
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
