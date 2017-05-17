import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ConfigurationReader {
    private NeuralNetwork network;
    private final LinkedList<String> configurations = new LinkedList<>();
    private String currentConfig;
    private int configNumber = 0;

    ConfigurationReader(NeuralNetwork network) {
        this.network = network;
    }

    public void readConfigFrom(String configFilename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(configFilename));

        String line;
        while ((line = reader.readLine()) != null)
            configurations.add(line);
        reader.close();
    }

    public void nextConfig() throws Exception {
        configNumber++;
        currentConfig = configurations.poll();
        network.setOptions(currentConfig);

    }

    public String getCurrentConfig() {
        return currentConfig;
    }

    public boolean hasNextConfig() {
        return !configurations.isEmpty();
    }

    public int getConfigNumber() {
        return configNumber;
    }
}
