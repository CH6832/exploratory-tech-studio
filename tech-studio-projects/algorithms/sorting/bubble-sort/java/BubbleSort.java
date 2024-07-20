import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * This class represents a JPanel that visualizes the Bubble Sort algorithm.
 */
public class BubbleSort extends JPanel {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final int BAR_WIDTH = 20;
    private static final int DELAY = 50;

    private int[] array;

    /**
     * Constructs a BubbleSort panel with the given array of integers.
     *
     * @param array The array of integers to be sorted and visualized.
     */
    public BubbleSort(int[] array) {
        this.array = array;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
    
        // Clear the panel
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);
    
        // Draw grid lines
        g2d.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < WIDTH; i += BAR_WIDTH) {
            g2d.drawLine(i, 0, i, HEIGHT);
        }
        for (int i = 0; i < HEIGHT; i += BAR_WIDTH) {
            g2d.drawLine(0, i, WIDTH, i);
        }
    
        // Draw bars
        int max = Arrays.stream(array).max().getAsInt();
        for (int i = 0; i < array.length; i++) {
            int x = i * BAR_WIDTH;
            int y = HEIGHT - array[i] * HEIGHT / max;
            Color color = new Color(255 - array[i], 100, array[i]); // Color based on array value
            g2d.setColor(color);
            g2d.fillRect(x, y, BAR_WIDTH, array[i] * HEIGHT / max);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, y, BAR_WIDTH, array[i] * HEIGHT / max);
    
            // Draw value labels
            g2d.setColor(Color.BLACK);
            g2d.drawString(String.valueOf(array[i]), x + 2, y - 5);
        }
    }

    /**
     * Sorts the array of integers using the Bubble Sort algorithm and visualizes the sorting process.
     */
    public void bubbleSort() {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    repaint();
                    try {
                        Thread.sleep(DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
