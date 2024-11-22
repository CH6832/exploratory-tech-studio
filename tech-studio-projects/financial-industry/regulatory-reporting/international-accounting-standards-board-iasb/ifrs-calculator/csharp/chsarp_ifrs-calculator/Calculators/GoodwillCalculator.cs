using chsarp_ifrs_calculator.Utils;
using System;

namespace IFRSCalculator.Calculations
{
    /// <summary>
    /// Provides methods for calculating goodwill, which is the difference between the purchase price
    /// and the fair value of the net assets acquired.
    /// </summary>
    public static class GoodwillCalculator
    {
        /// <summary>
        /// Calculates goodwill by subtracting the fair value of net assets from the purchase price.
        /// Goodwill represents the premium paid over the fair value of assets acquired.
        /// </summary>
        /// <param name="purchasePrice">The purchase price paid for the business or assets.</param>
        /// <param name="netAssets">The fair value of the net assets acquired.</param>
        /// <returns>The calculated goodwill value.</returns>
        /// <exception cref="ArgumentOutOfRangeException">
        /// Thrown when the purchase price or net assets is less than 0 (non-negative values are required).
        /// </exception>
        public static double CalculateGoodwill(double purchasePrice, double netAssets)
        {
            // Ensure the input values are valid (non-negative)
            if (purchasePrice < 0 || netAssets < 0)
            {
                throw new ArgumentOutOfRangeException("Both purchase price and net assets must be non-negative.");
            }

            // Calculate and return the goodwill (purchase price minus net assets)
            return purchasePrice - netAssets;
        }

        /// <summary>
        /// The Run method prompts the user for the purchase price and net assets, calculates goodwill,
        /// and prints the result to the console.
        /// </summary>
        public static void Run()
        {
            // Print the calculation start message
            Console.WriteLine("\n=== Goodwill Calculation ===");

            // Prompt user to input the purchase price and fair value of net assets
            double purchasePrice = InputHelper.GetPositiveDoubleInput("Enter purchase price: ");
            double netAssets = InputHelper.GetPositiveDoubleInput("Enter fair value of net assets: ");

            // Calculate goodwill using the provided values
            double goodwill = CalculateGoodwill(purchasePrice, netAssets);

            // Print the calculated goodwill to the console
            Console.WriteLine($"\nGoodwill: {goodwill:C}");
        }
    }
}
