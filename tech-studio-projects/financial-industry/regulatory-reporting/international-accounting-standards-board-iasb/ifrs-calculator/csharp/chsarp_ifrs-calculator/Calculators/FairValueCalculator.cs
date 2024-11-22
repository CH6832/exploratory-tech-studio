using System;
using chsarp_ifrs_calculator.Utils;

namespace chsarp_ifrs_calculator.Calculations
{
    /// <summary>
    /// A static class responsible for calculating the fair value of a bond using the discounted cash flow method.
    /// It calculates the present value of future cash flows by discounting them at a given discount rate.
    /// </summary>
    public static class FairValueCalculator
    {
        /// <summary>
        /// Runs the Fair Value calculation process by prompting the user for the cash flow, discount rate, and number of periods.
        /// It calculates the fair value of a bond based on these inputs using the discounted cash flow method.
        /// </summary>
        public static void Run()
        {
            try
            {
                Console.WriteLine("\n=== Fair Value Calculation ===");

                // Get inputs from the user
                double cashFlow = InputHelper.GetPositiveDoubleInput("Enter cash flow amount: ");
                double discountRate = InputHelper.GetPositiveDoubleInput("Enter discount rate (%): ") / 100;
                int periods = InputHelper.GetPositiveIntInput("Enter number of periods: ");

                // Calculate fair value using discounted cash flow method
                double fairValue = 0;
                for (int t = 1; t <= periods; t++)
                {
                    fairValue += cashFlow / Math.Pow(1 + discountRate, t);
                }

                // Output the result
                Console.WriteLine($"\nFair Value of the bond: {fairValue:C}");

                // Log the calculation result
                Logger.LogInfo($"Fair Value calculated: Cash Flow = {cashFlow}, Discount Rate = {discountRate * 100}%, Periods = {periods}, Fair Value = {fairValue}");
            }
            catch (Exception ex)
            {
                // Handle and log any exceptions that occur during the calculation
                Console.WriteLine("\nAn error occurred during the fair value calculation. Please try again.");
                Logger.LogError("Error calculating fair value", ex);
            }
        }

        internal static double Run(double cashFlow, double discountRate, int periods)
        {
            throw new NotImplementedException();
        }
    }
}
