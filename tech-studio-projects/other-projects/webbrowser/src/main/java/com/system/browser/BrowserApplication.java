package com.system.browser;

// Application class from JavaFX to create a JavaFX application.
import javafx.application.Application;
// FXMLLoader to load FXML files,which define the UI structure.
import javafx.fxml.FXMLLoader;
// Base for the scene graph.
import javafx.scene.Parent;
// Scene class to create a scene containing the UI elements.
import javafx.scene.Scene;
// Stage class which represents a window in the JavaFX application.
import javafx.stage.Stage;
// Objects utility class for null checks.
import java.util.Objects;

public class BrowserApplication extends Application {
    /**
     * The entry point for the JavaFX application.
     * This method is called when the application is launched.
     *
     * @param primaryStage The primary stage for this application, onto which the application scene can be set.
     * @throws Exception If the FXML file cannot be loaded or if there are other initialization issues.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file that defines the layout of the browser.
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/system/browser/browser-view.fxml")));
        // Set the title for the primary stage.
        primaryStage.setTitle("JavaFX Browser");
        // Create the scene for the browser and the size of it.
        Scene scene = new Scene(root, 1200, 700);
        // Load and apply CSS styles.
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/system/browser/browser-view.css")).toExternalForm());
        // Set the created scene.
        primaryStage.setScene(scene);
        // Display the scene.
        primaryStage.show();
    }
}