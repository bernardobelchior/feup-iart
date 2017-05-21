CONFIG=$1

java -classpath lib/weka.jar:out Main 10 Training_Dataset.arff $CONFIG 
