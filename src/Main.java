import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String... args) throws Exception {
        Parser parser = new Parser();
        Classifier classifier = new Classifier();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String answer;

        System.out.println("Neural Network for Phishing Websites Detection");
        System.out.println("This system uses a Neural Network with Backpropagation for result prediction.");

        System.out.println("Neural Network Configuration:");

        do {
            System.out.print("Path to dataset file: ");
            answer = reader.readLine();

            try {
                parser.parseDataFromSource(answer);
                break;
            } catch (Exception e) {
                System.err.println("Error reading from dataset.");
            }
        } while (true);


        do {
            System.out.println("Do you want to see a GUI? (yes/no)");
            answer = reader.readLine();

            if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
                classifier.setGUI(true);
                break;
            } else if (answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("n")) {
                classifier.setGUI(false);
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

        classifier.setHiddenLayers(reader.readLine());

        System.out.print("Set learning rate ]0, 1]: ");
        classifier.setLearningRate(Double.parseDouble(reader.readLine()));

        System.out.print("Set momentum ]0, 1]: ");
        classifier.setMomentum(Double.parseDouble(reader.readLine()));

        System.out.print("Set number of epochs: ");
        classifier.setEpochs(Integer.parseUnsignedInt(reader.readLine()));

        System.out.print("Set the percentage of the validation set to use [0, 100]: ");
        classifier.setValidationSetSize(Integer.parseUnsignedInt(reader.readLine()));

        System.out.println("Starting classifier...");

        try {
            classifier.start(parser.getData());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Invalid options given to classifier.");
            return;
        }
    }
}
