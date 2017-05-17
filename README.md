# Neural Networks applied to Phishing Websites Detection
Implementation of a simple Neural Network using Backpropagation.

## Running the Program

The program is able to run a single console-configured neural network training, and it is run trivially.
It also allows to run several sequential neural network training sessions, and log its evalution results to a .txt with the start date of classification. It is run as follows:
```
java Main <num-folds-for-cross-validation> <training-dataset-filename> <network-configuration-filename>
```

## Network Configuration

The network configuration file consists of a line for every training session, with the following pattern:
```
-A 1 -B 1,2,3
```

The options (passed as `-A` and `-B` above) are available [here](http://weka.sourceforge.net/doc.dev/weka/classifiers/functions/MultilayerPerceptron.html). When multiple values need to be given to a corresponding flag they must be comma separated without space inbetween.
An example could be:
```
-L 0.35 -M 0.5 -N 500 -H 10
```
Where the learning rate is equal to 0.35, the momentum to 0.5, the number of epochs to 500 and there is only one hidden layer with 10 neurons.
