package CreditScoringAlgo.RCA;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class App {

    public static void main(String[] args) throws ParseException {
        String trainFile = null;
        String testFile = null;
        double learningRate = 0.01;
        int iterations = 10000;

        // Create options for command line parsing
        Options options = new Options();
        options.addOption("h", "help", false, "produce help message");
        options.addOption("t", "train", true, "training data file (required)");
        options.addOption("e", "test", true, "testing data file (required)");
        options.addOption("l", "learning-rate", true, "learning rate (default is 0.01)");
        options.addOption("i", "iterations", true, "number of iterations (default is 10000)");

        CommandLineParser parser = new DefaultParser();
        try {
            // Parse the command line arguments
            CommandLine cmd = null;
			try {
				cmd = parser.parse(options, args);
			} catch (org.apache.commons.cli.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            // Display help if needed
            if (cmd.hasOption("h")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("App", options);
                return;
            }

            // Get the training file and test file
            if (cmd.hasOption("t")) {
                trainFile = cmd.getOptionValue("t");
            } else {
                System.err.println("Training file is required.");
                return;
            }

            if (cmd.hasOption("e")) {
                testFile = cmd.getOptionValue("e");
            } else {
                System.err.println("Testing file is required.");
                return;
            }

            // Get optional parameters
            if (cmd.hasOption("l")) {
                learningRate = Double.parseDouble(cmd.getOptionValue("l"));
            }
            if (cmd.hasOption("i")) {
                iterations = Integer.parseInt(cmd.getOptionValue("i"));
            }
        } catch (NumberFormatException e) {
            System.err.println("Error parsing numeric values: " + e.getMessage());
            return;
        }

        try {
            // Read training data and labels
            List<List<Double>> trainData = CsvUtils.readCsv(trainFile);
            List<Integer> trainLabels = CsvUtils.readCsvWithLabels(trainFile);

            // Train the logistic regression model
            LogisticRegression model = new LogisticRegression(learningRate, iterations);
            model.fit(trainData, trainLabels);

            // Read test data and make predictions
            List<List<Double>> testData = CsvUtils.readCsvNoLabels(testFile);
            List<Integer> predictions = model.predict(testData);

            // Print predictions
            System.out.println("Predictions:");
            for (int prediction : predictions) {
                System.out.println(prediction);
            }
        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
