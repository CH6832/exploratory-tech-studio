package com.example;

import org.apache.commons.io.FileUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class BeanDiseaseClassifier {

    private static final String TRAIN_DIRECTORY_LOCATION = "/tmp/train";
    private static final String VAL_DIRECTORY_LOCATION = "/tmp/validation";
    private static final String[] CLASS_NAMES = {"healthy", "bean rust", "angular leaf spot"};
    private static final int IMAGE_HEIGHT = 224;
    private static final int IMAGE_WIDTH = 224;

    public static void main(String[] args) {
        try {
            downloadAndExtract("https://storage.googleapis.com/learning-datasets/beans/train.zip", TRAIN_DIRECTORY_LOCATION);
            downloadAndExtract("https://storage.googleapis.com/learning-datasets/beans/validation.zip", VAL_DIRECTORY_LOCATION);

            // Placeholder for model training logic.
            System.out.println("Model training logic not implemented in this version.");

            // For demonstration, we just plot an accuracy graph
            plotAccuracyHistory(Arrays.asList(0.5, 0.7, 0.9), Arrays.asList(0.4, 0.6, 0.8)); // Replace with actual data

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void downloadAndExtract(String url, String destDir) throws IOException {
        // Create destination directory if it doesn't exist
        File dir = new File(destDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Download the zip file
        File zipFile = new File("/tmp/temp.zip");
        try (InputStream in = new URL(url).openStream()) {
            FileUtils.copyInputStreamToFile(in, zipFile);
        }

        // Unzip the file
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry;
            while ((entry = zipIn.getNextEntry()) != null) {
                File newFile = new File(destDir, entry.getName());
                if (entry.isDirectory()) {
                    newFile.mkdirs();
                } else {
                    // Create directories for subdirectories
                    new File(newFile.getParent()).mkdirs();
                    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newFile))) {
                        byte[] bytesIn = new byte[4096];
                        int read;
                        while ((read = zipIn.read(bytesIn)) != -1) {
                            bos.write(bytesIn, 0, read);
                        }
                    }
                }
                zipIn.closeEntry();
            }
        }
        // Clean up zip file
        Files.delete(zipFile.toPath());
    }

    private static void plotAccuracyHistory(List<Double> trainAccuracy, List<Double> valAccuracy) {
        XYSeries seriesTrain = new XYSeries("Training Accuracy");
        XYSeries seriesVal = new XYSeries("Validation Accuracy");
        for (int i = 0; i < trainAccuracy.size(); i++) {
            seriesTrain.add(i + 1, trainAccuracy.get(i));
            seriesVal.add(i + 1, valAccuracy.get(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesTrain);
        dataset.addSeries(seriesVal);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Model Accuracy",
                "Epoch",
                "Accuracy",
                dataset
        );

        JFrame frame = new JFrame("Model Accuracy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.pack();
        frame.setVisible(true);
    }
}
