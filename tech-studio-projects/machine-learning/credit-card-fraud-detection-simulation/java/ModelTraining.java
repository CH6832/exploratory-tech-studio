import weka.classifiers.trees.RandomForest;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Standardize;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class ModelTraining {

    public static void main(String[] args) {
        try {
            // Load the dataset
            DataSource source = new DataSource("data/creditcard.arff"); // Use ARFF file format for Weka
            Instances data = source.getDataSet();

            // Ensure class attribute is the last column
            if (data.classIndex() == -1) {
                data.setClassIndex(data.numAttributes() - 1);
            }

            // Split features and labels
            Instances features = new Instances(data);
            features.deleteAttributeAt(data.classIndex());  // Remove the class attribute

            // Standardize the features
            Standardize standardize = new Standardize();
            standardize.setInputFormat(features);
            Instances standardizedFeatures = Filter.useFilter(features, standardize);

            // Add the class attribute back to the standardized dataset
            standardizedFeatures.insertAttributeAt(data.classAttribute(), standardizedFeatures.numAttributes());

            // Split the dataset into training (80%) and test (20%) sets
            int trainSize = (int) Math.round(data.numInstances() * 0.8);
            int testSize = data.numInstances() - trainSize;
            Instances trainData = new Instances(standardizedFeatures, 0, trainSize);
            Instances testData = new Instances(standardizedFeatures, trainSize, testSize);

            // Train a Random Forest Classifier
            RandomForest rf = new RandomForest();
            rf.setNumTrees(100);
            rf.buildClassifier(trainData);

            // Evaluate the model
            double accuracy = evaluateModel(rf, testData);
            System.out.println("Model Accuracy: " + accuracy);

            // Save the model and scaler
            saveModel(rf, "models/fraud_detection_model.model");
            saveStandardizer(standardize, "models/scaler.model");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double evaluateModel(RandomForest model, Instances testData) throws Exception {
        int correct = 0;
        for (int i = 0; i < testData.numInstances(); i++) {
            double actualClass = testData.instance(i).classValue();
            double predictedClass = model.classifyInstance(testData.instance(i));
            if (actualClass == predictedClass) {
                correct++;
            }
        }
        return (double) correct / testData.numInstances();
    }

    public static void saveModel(RandomForest model, String modelPath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(modelPath))) {
            oos.writeObject(model);
            System.out.println("Model saved to " + modelPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveStandardizer(Standardize standardize, String scalerPath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(scalerPath))) {
            oos.writeObject(standardize);
            System.out.println("Scaler saved to " + scalerPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
