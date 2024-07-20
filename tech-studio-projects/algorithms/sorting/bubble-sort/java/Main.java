import javax.swing.*;

/**
 * This class serves as the entry point of the program and demonstrates the Bubble Sort algorithm by visualizing the sorting process.
 */
public class Main {
    /**
     * The main method initializes an array of integers, creates a JFrame for displaying the sorting visualization,
     * adds a BubbleSort panel to the frame, sets up the frame's properties, makes the frame visible, and starts the sorting process.
     *
     * @param args The command line arguments (not used).
     */
    public static void main(String[] args) {
        // Initialize the array to be sorted
        int[] array = {64, 34, 25, 12, 22, 11, 90};
        // Create a JFrame for displaying the sorting visualization
        JFrame frame = new JFrame("Bubble Sort Visualization");
        // Create a BubbleSort panel with the given array
        BubbleSort panel = new BubbleSort(array);
        // Set the close operation of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Add the panel to the frame
        frame.add(panel);
        // Set the size of the frame
        frame.setSize(BubbleSort.WIDTH, BubbleSort.HEIGHT);
        // Center the frame on the screen
        frame.setLocationRelativeTo(null);        
        // Make the frame visible
        frame.setVisible(true);
        // Start the bubble sort algorithm
        Thread sortingThread = new Thread(panel::bubbleSort);
        sortingThread.start();
    }
}
