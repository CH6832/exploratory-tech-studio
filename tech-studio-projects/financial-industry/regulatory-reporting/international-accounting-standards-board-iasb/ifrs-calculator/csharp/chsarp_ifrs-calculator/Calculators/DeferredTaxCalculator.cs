using chsarp_ifrs_calculator.Utils;
using System;

namespace IFRSCalculator.Calculations
{
    /// <summary>
    /// Provides methods to calculate the deferred tax based on the carrying amount, tax base, and tax rate.
    /// </summary>
    public static class DeferredTaxCalculator
    {
        /// <summary>
        /// Calculates the deferred tax amount based on the difference between the carrying amount and the tax base,
        /// multiplied by the applicable tax rate.
        /// </summary>
        /// <param name="carryingAmount">The carrying amount of the asset or liability.</param>
        /// <param name="taxBase">The tax base of the asset or liability.</param>
        /// <param name="taxRate">The applicable tax rate (as a decimal, e.g., 0.15 for 15%).</param>
        /// <returns>The calculated deferred tax amount.</returns>
        /// <exception cref="ArgumentOutOfRangeException">
        /// Thrown when either the carrying amount or the tax base is less than zero.
        /// </exception>
        public static double CalculateDeferredTax(double carryingAmount, double taxBase, double taxRate)
        {
            // Ensure the input values are valid (non-negative)
            if (carryingAmount < 0 || taxBase < 0)
            {
                throw new ArgumentOutOfRangeException("Both carrying amount and tax base must be non-negative.");
            }

            // Calculate the deferred tax by multiplying the difference between carrying amount and tax base by the tax rate
            double deferredTax = (carryingAmount - taxBase) * taxRate;

            // Return the calculated deferred tax amount
            return deferredTax;
        }

        /// <summary>
        /// The Run method prompts the user for the necessary inputs and calculates the deferred tax.
        /// It then prints the result to the console.
        /// </summary>
        public static void Run()
        {
            // Print the calculation start message
            Console.WriteLine("\n=== Deferred Tax Calculation ===");

            // Prompt user to input the carrying amount, tax base, and tax rate
            double carryingAmount = InputHelper.GetPositiveDoubleInput("Enter carrying amount: ");
            double taxBase = InputHelper.GetPositiveDoubleInput("Enter tax base: ");
            double taxRate = InputHelper.GetPositiveDoubleInput("Enter tax rate (%): ") / 100;

            // Calculate the deferred tax using the provided values
            double deferredTax = CalculateDeferredTax(carryingAmount, taxBase, taxRate);

            // Print the calculated deferred tax to the console
            Console.WriteLine($"\nDeferred Tax: {deferredTax:C}");
        }
    }
}
