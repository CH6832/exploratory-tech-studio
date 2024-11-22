using chsarp_ifrs_calculator.Utils;
using System.Collections.Generic;

namespace IFRSCalculator.Calculations
{
    /// <summary>
    /// A static class that allocates revenue based on standalone selling prices and the total transaction price.
    /// The method allocates revenue proportionally to each performance obligation.
    /// </summary>
    public static class RevenueAllocator
    {
        /// <summary>
        /// Prompts the user for the total transaction price and number of performance obligations,
        /// then allocates the revenue to each obligation based on their standalone prices.
        /// </summary>
        public static void Run(double transactionPrice, List<double> standalonePrices)
        {
            try
            {
                Console.WriteLine("\n=== Revenue Allocation ===");

                // Get user inputs
                transactionPrice = InputHelper.GetPositiveDoubleInput("Enter total transaction price: ");
                int obligations = InputHelper.GetPositiveIntInput("Enter number of performance obligations: ");

                standalonePrices = new List<double>();
                double totalStandalonePrice = 0;

                // Gather standalone selling prices for each obligation
                for (int i = 1; i <= obligations; i++)
                {
                    double price = InputHelper.GetPositiveDoubleInput($"Enter standalone selling price for obligation {i}: ");
                    standalonePrices.Add(price);
                    totalStandalonePrice += price;
                }

                // Calculate and display the revenue allocation
                Console.WriteLine("\nRevenue Allocation:");
                for (int i = 0; i < obligations; i++)
                {
                    double allocatedRevenue = (standalonePrices[i] / totalStandalonePrice) * transactionPrice;
                    Console.WriteLine($" - Obligation {i + 1}: {allocatedRevenue:C}");
                }

                // Log the calculation
                Logger.LogInfo($"Revenue allocated: Transaction Price = {transactionPrice}, Obligations = {obligations}, Total Standalone Price = {totalStandalonePrice}");
            }
            catch (Exception ex)
            {
                // Handle any errors in the process
                Console.WriteLine("\nAn error occurred during the revenue allocation. Please try again.");
                Logger.LogError("Error in revenue allocation calculation", ex);
            }
        }

        internal static object CalculateRevenueAllocation(double transactionPrice, double[] standalonePrices)
        {
            throw new NotImplementedException();
        }

        internal static void Run()
        {
            throw new NotImplementedException();
        }
    }
}
