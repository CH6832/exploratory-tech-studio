using chsarp_ifrs_calculator.Utils;
using System;

namespace IFRSCalculator.Calculations
{
    /// <summary>
    /// Provides methods for calculating the recoverable amount, which is the higher of the fair value
    /// less costs to sell and the value in use, as per IFRS requirements for asset impairment testing.
    /// </summary>
    public static class RecoverableAmountCalculator
    {
        /// <summary>
        /// Calculates the recoverable amount based on the fair value and value in use.
        /// The recoverable amount is the higher of the fair value less costs to sell and the value in use.
        /// </summary>
        /// <param name="fairValue">The fair value of the asset less costs to sell.</param>
        /// <param name="valueInUse">The value in use, representing the present value of future cash flows.</param>
        /// <returns>The calculated recoverable amount as the maximum of fair value and value in use.</returns>
        /// <exception cref="ArgumentOutOfRangeException">Thrown if either fair value or value in use is negative.</exception>
        public static double CalculateRecoverableAmount(double fairValue, double valueInUse)
        {
            // Check if either value is negative, as both values must be non-negative
            if (fairValue < 0 || valueInUse < 0)
                throw new ArgumentOutOfRangeException("Both fair value and value in use must be non-negative.");

            // Return the maximum of fair value and value in use
            return Math.Max(fairValue, valueInUse);
        }

        /// <summary>
        /// The Run method prompts the user for input values, calculates the recoverable amount,
        /// and prints the result to the console.
        /// </summary>
        public static void Run()
        {
            // Print the calculation start message
            Console.WriteLine("\n=== Recoverable Amount Calculation ===");

            // Prompt the user for the fair value and value in use
            double fairValue = InputHelper.GetPositiveDoubleInput("Enter fair value less costs to sell: ");
            double valueInUse = InputHelper.GetPositiveDoubleInput("Enter value in use: ");

            // Calculate the recoverable amount as the maximum of fair value and value in use
            double recoverableAmount = CalculateRecoverableAmount(fairValue, valueInUse);

            // Print the calculated recoverable amount to the console
            Console.WriteLine($"\nRecoverable Amount: {recoverableAmount:C}");
        }
    }
}
