/**
 * The Knapsack problem.
 *
 * What is the 0/1 Knapsack Problem? We are given N items where each
 * item has some weight and profit associated with it. We are also given
 * a bag with capacity W, [i.e., the bag can hold at most W weight in it].
 * The target is to put the items into the bag such that the sum of
 * profits associated with them is the maximum possible.
 *
 * Note: The constraint here is we can either put an item completely into
 * the bag or cannot put it at all [It is not possible to put a part of
 * an item into the bag].
 *
 * Examples:
 * Input: N = 3, W = 4, profit[] = {1, 2, 3}, weight[] = {4, 5, 1}
 * Output: 3
 * Explanation: There are two items which have weight less than or equal
 * to 4. If we select the item with weight 4, the possible profit is 1. And
 * if we select the item with weight 1, the possible profit is 3. So the
 * maximum possible profit is 3. Note that we cannot put both the items
 * with weight 4 and 1 together as the capacity of the bag is 4.
 *
 * Input: N = 3, W = 3, profit[] = {1, 2, 3}, weight[] = {4, 5, 6}
 * Output: 0
 */

#include <iostream>
#include <algorithm>

// A naive recursive implementation of 0-1 Knapsack problem.
int knapSack(int W, int wt[], int val[], int n) {
    // Base Case
    if (n == 0 || W == 0) {
        return 0;
    }
    
    // If weight of the nth item is more than Knapsack capacity W, then
    // this item cannot be included in the optimal solution
    if (wt[n - 1] > W) {
        return knapSack(W, wt, val, n - 1);
    } else {
        // Return the maximum of two cases:
        // (1) nth item included
        // (2) not included
        return std::max(
            val[n - 1] + knapSack(W - wt[n - 1], wt, val, n - 1),
            knapSack(W, wt, val, n - 1)
        );
    }
}

int main() {
    int val[] = {1, 2, 3};
    int wt[] = {4, 5, 1};
    int W = 4;
    int n = sizeof(val) / sizeof(val[0]);
    std::cout << knapSack(W, wt, val, n) << std::endl;  // Output: 3
    return 0;
}
