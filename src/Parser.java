import weka.core.Instances;

import static weka.core.converters.ConverterUtils.DataSource;

public class Parser {
    Instances data;

    Parser() {
    }

    boolean parseDataFromSource(String filename) {
        try {
            DataSource dataSource = new DataSource(filename);
            data = dataSource.getDataSet();
        } catch (Exception e) {
            return false;
        }

        if (data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1);
        }

        return true;
    }

    Instances getData() {
        return data;
    }
}
