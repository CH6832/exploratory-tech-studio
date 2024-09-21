// Activity Selection Problem.
 
// The following implementation assumes that the activities
// are already sorted according to their finish time

// More on Greedy ALgorithms
// https://www.geeksforgeeks.org/greedy-algorithms/#standardgreedy

import java.io.*;
import java.lang.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        int s[] = {1, 3, 0, 5, 8, 5};
        int f[] = {2, 4, 6, 7, 9, 9};
        int n = s.length;

        // Function call
        ActivitySelection.printMaxActivities(s, f, n);
    }
}