package CreditScoringAlgo.RCA;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CsvUtils {

    public static List<List<Double>> readCsv(String filePath) throws IOException, CsvException {
        List<List<Double>> data = new ArrayList<>();
        try (Reader reader = new FileReader(filePath);
             CSVReader csvReader = new CSVReader(reader)) {
            
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                List<Double> row = new ArrayList<>();
                for (String value : nextLine) {
                    row.add(Double.parseDouble(value));
                }
                data.add(row);
            }
        }
        return data;
    }

    public static List<Integer> readCsvWithLabels(String filePath) throws IOException, CsvException {
        List<Integer> labels = new ArrayList<>();
        List<List<Double>> data = readCsv(filePath);
        for (List<Double> row : data) {
            labels.add(row.remove(row.size() - 1).intValue()); // Remove last element as label
        }
        return labels;
    }

    public static List<List<Double>> readCsvNoLabels(String filePath) throws IOException, CsvException {
        return readCsv(filePath); // Labels are not included, so just read CSV normally.
    }
}
