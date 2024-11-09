package com.system.browser;

// AppLogger for logging.
import com.system.utils.AppLogger;
// AppProfiler for profiling.
import com.system.utils.AppProfiler;
// Worker class to manage background tasks.
import javafx.concurrent.Worker;
// EventHandler to handle events.
import javafx.event.EventHandler;
// FXML to enable annotation-based access to UI elements.
import javafx.fxml.FXML;
// Alert for showing error dialogs.
import javafx.scene.control.Alert;
// Tab to represent individual tabs in the TabPane.
import javafx.scene.control.Tab;
// TabPane to manage multiple tabs.
import javafx.scene.control.TabPane;
// TextField for user input fields
import javafx.scene.control.TextField;
// KeyCode for key events.
import javafx.scene.input.KeyCode;
// KeyEvent to handle keyboard events.
import javafx.scene.input.KeyEvent;
// WebEngine to handle web content.
import javafx.scene.web.WebEngine;
// WebView to display web pages.
import javafx.scene.web.WebView;
// IOException for handling input/output errors.
import java.io.IOException;
// Handling Uniform Resource Identifiers.
import java.net.URI;
// URISyntaxException for URI parsing errors.
import java.net.URISyntaxException;
// List for using collections.
import java.util.List;

public class BrowserController {

    // List of keywords that, if found in a URL, will trigger opening in an external browser.
    private static final List<String> EXTERNAL_BROWSER_URLS = List.of("chatgpt", "gemini", "llama");

    @FXML
    private TextField searchField;

    @FXML
    public TabPane tabPane;

    @FXML
    private TextField addressBar;

    // Profiling instance to monitor and log performance of critical operations
    private final AppProfiler profiler = new AppProfiler();

    /**
     * Handles key pressed events in the TabPane.
     * If Ctrl + F is pressed, the search field becomes visible.
     *
     * @param event The KeyEvent containing information about the key pressed.
     */
    private void handleKeyPressed(KeyEvent event) {
        // Check if Ctrl + F is pressed
        if (event.isControlDown() && event.getCode() == KeyCode.F) {
            // Show the search field
            searchField.setVisible(true);
            searchField.requestFocus();
        }
    }

    /**
     * Searches for the specified text within the currently displayed web page.
     * This method is invoked when a search is performed in the search field.
     */
    @FXML
    private void searchInPage() {
        WebView webView = (WebView) tabPane.getSelectionModel().getSelectedItem().getContent();
        WebEngine webEngine = webView.getEngine();
        String searchText = searchField.getText();
        webEngine.executeScript("window.find('" + searchText + "')");
        // Begin profiling the search operation by marking the start time.
        profiler.start("Search text in page");
        // Execute the command for an in-page search on the current web page content.
        webEngine.executeScript("window.find('" + searchText + "')");
        // End the profiling for this search operation by marking the stop time.
        profiler.stop();
        // Log the profiling result with the elapsed time for the "Search text in page" operation.
        profiler.logProfilingResult("Search text in page");}

    /**
     * Determines if a URL should be opened in an external browser.
     *
     * @param url The URL to check.
     * @return true if the URL contains keywords that require external browser opening; otherwise false.
     */
    private boolean shouldOpenInExternalBrowser(String url) {
        return EXTERNAL_BROWSER_URLS.stream().anyMatch(url::contains);
    }

    /**
     * Opens a specified URL in the default external browser based on the operating system.
     *
     * @param url The URL to open.
     * @return true if the URL was successfully opened, false otherwise.
     */
    boolean openInExternalBrowser(String url) {
        String osName = System.getProperty("os.name").toLowerCase();
        String[] command;

        if (osName.contains("win")) {
            command = new String[]{"C:\\Program Files\\Mozilla Firefox\\firefox.exe", "-new-tab", url};
        } else if (osName.contains("nix") || osName.contains("nux")) {
            command = new String[]{"firefox", "-new-tab", url};
        } else if (osName.contains("mac")) {
            command = new String[]{"open", "-a", "Safari", url};
        } else {
            showErrorDialog("Unsupported OS", "This feature is not supported on your operating system.");
            AppLogger.error("Attempted to open URL on unsupported OS: {}", osName);
            return false;
        }

        try {
            new ProcessBuilder(command).start();
            AppLogger.info("Opening URL in external browser: {}", url);
        } catch (IOException e) {
            showErrorDialog("Error", "Failed to open the URL in the external browser.");
            AppLogger.error("Failed to open URL in external browser: {}", url);
        }
        return false;
    }

    /**
     * Adds a new tab to the tab pane with a specified URL.
     *
     * @param url The URL to be loaded in the new tab.
     */
    @FXML
    private void addNewTab(String url) {
        String completeUrl = URLAutoComplete.suggestCompletion(url);
        AppLogger.info("Adding new tab with URL: {}", completeUrl);

        if (shouldOpenInExternalBrowser(completeUrl)) {
            openInExternalBrowser(completeUrl);
            return;
        }

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.FAILED) {
                showErrorDialog("Failed to load page", "The URL you entered could not be loaded.");
                AppLogger.error("Failed to load page: {}", completeUrl);
            }
            else if (newValue == Worker.State.SUCCEEDED) {
                profiler.stop(); // Stop profiling when load is complete
                profiler.logProfilingResult("Load URL in new tab: " + completeUrl); // Log profiling result
                AppLogger.info("Successfully loaded page in new tab: {}", completeUrl);
            }
        });

        String websiteName = extractWebsiteName(completeUrl);
        Tab newTab = new Tab(websiteName);
        newTab.setContent(webView);
        tabPane.getTabs().add(newTab);
        webEngine.load(completeUrl);
    }

    /**
     * Opens the URL entered in the address bar in the default external browser (Firefox).
     */
    @FXML
    private void openInFirefox() {
        String url = addressBar.getText();
        String completeUrl = URLAutoComplete.suggestCompletion(url);
        openInExternalBrowser(completeUrl);
        AppLogger.info("Opening URL in Firefox from address bar: {}", completeUrl);
    }

    /**
     * Initializes the controller by setting up event handlers and default behaviors.
     */
    @FXML
    private void initialize() {
        // Add key pressed event handler to the root node
        tabPane.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPressed);
        // Add listener to close tabs with Ctrl + W
        tabPane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.isControlDown() && event.getCode() == KeyCode.W) {
                    Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
                    if (selectedTab != null) {
                        tabPane.getTabs().remove(selectedTab);
                        AppLogger.info("Closed tab: {}", selectedTab.getText());
                    }
                }
            }
        });
        // Add an initial tab when the application starts
        addNewTab("https://www.google.com");
        // Add listener to the address bar to handle URL changes
        addressBar.setOnAction(event -> openUrlInNewTab(addressBar.getText()));
    }

    /**
     * Shows an error dialog with a specified title and message.
     *
     * @param title   The title of the error dialog.
     * @param message The message to be displayed in the dialog.
     */
    @FXML
    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        AppLogger.error("Error dialog shown: {} - {}", title, message);
    }

    /**
     * Adds a new tab with a default URL when called.
     */
    @FXML
    private void addNewTabWithDefaultUrl() {
        addNewTab("https://www.google.com"); // or any other default URL you prefer
        AppLogger.info("Added new tab with default URL: https://www.google.com");
    }

    /**
     * Extracts the website name from a given URL.
     *
     * @param url The URL from which to extract the website name.
     * @return The name of the website or "New Tab" if extraction fails.
     */
    @FXML
    private String extractWebsiteName(String url) {
        try {
            URI uri = new URI(url);
            String domain = uri.getHost();
            if (domain != null) {
                return domain.startsWith("www.") ? domain.substring(4) : domain;
            }
        } catch (URISyntaxException e) {
            AppLogger.error("URI syntax error for URL: {}", url);
        }
        return "New Tab";
    }

    /**
     * Opens a specified URL in a new tab.
     *
     * @param url The URL to open.
     */
    @FXML
    private void openUrlInNewTab(String url) {
        // Use auto-completion to suggest completing the URL
        String completedUrl = URLAutoComplete.suggestCompletion(url);

        // Create a new WebView and WebEngine
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Add a listener to handle page load errors
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.FAILED) {
                showErrorDialog("Failed to load page", "The URL you entered could not be loaded.");
                AppLogger.error("Failed to load URL: {}", completedUrl);
            }
        });
        // Create a new tab and set its content to the WebView
        Tab newTab = new Tab();
        newTab.setContent(webView);

        // Set the title of the tab to the website name
        String websiteName = extractWebsiteName(completedUrl);
        newTab.setText(websiteName);

        // Add the new tab to the tab pane
        tabPane.getTabs().add(newTab);

        // Load the completed URL into the WebView
        webEngine.load(completedUrl);
        AppLogger.info(completedUrl);
    }

    /**
     * Navigates back in the history of the currently selected tab's WebView.
     */
    @FXML
    private void navigateBack() {
        // Get the selected tab
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

        // Get the WebEngine associated with the WebView in the selected tab
        WebView webView = (WebView) selectedTab.getContent();
        WebEngine webEngine = webView.getEngine();

        // Navigate back
        if (webEngine.getHistory().getCurrentIndex() > 0) {
            webEngine.getHistory().go(-1);
            AppLogger.info("Navigated back in history.", selectedTab.getText());
        }
    }

    /**
     * Navigates forward in the history of the currently selected tab's WebView.
     */
    @FXML
    private void navigateForward() {
        // Get the selected tab
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

        // Get the WebEngine associated with the WebView in the selected tab
        WebView webView = (WebView) selectedTab.getContent();
        WebEngine webEngine = webView.getEngine();

        // Navigate forward
        if (webEngine.getHistory().getCurrentIndex() < webEngine.getHistory().getEntries().size() - 1) {
            webEngine.getHistory().go(1);
            AppLogger.info("Navigated forward in history.", selectedTab.getText());
        }
    }

    /**
     * Refreshes the currently displayed web page in the selected tab.
     */
    @FXML
    private void refreshPage() {
        // Get the selected tab
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

        // Get the WebEngine associated with the WebView in the selected tab
        WebView webView = (WebView) selectedTab.getContent();
        WebEngine webEngine = webView.getEngine();

        // Start profiling for refreshing the page, noting the tab name in the profiling label.
        // This records the current time as the profiling start time, allowing us to calculate
        // the total time taken for the refresh operation once it completes.
        profiler.start("Refresh page: " + selectedTab.getText());
        // Refresh the page
        webEngine.reload();
        profiler.stop();
        // Log the profiling results to track how long the refresh initiation took.
        // Calling logProfilingResult() records the time between start and stop, providing a
        // detailed log message that includes the elapsed time for initiating the page refresh.
        // This can be useful to evaluate the responsiveness of the refresh command initiation
        // and see if there are any delays in kicking off the reload operation itself.
        profiler.logProfilingResult("Refresh page: " + selectedTab.getText());

        AppLogger.info("Page refreshed for tab: {}", selectedTab.getText());
    }
}
