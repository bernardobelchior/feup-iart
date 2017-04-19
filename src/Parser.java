import weka.core.Instances;

import static weka.core.converters.ConverterUtils.DataSource;

public class Parser {
    Instances data;

    Parser() {
    }

    void parseDataFromSource(String filename) throws Exception {
        DataSource dataSource = new DataSource(filename);
        data = dataSource.getDataSet();

        if (data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1);
        }
    }

    Instances getData() {
        return data;
    }
}
