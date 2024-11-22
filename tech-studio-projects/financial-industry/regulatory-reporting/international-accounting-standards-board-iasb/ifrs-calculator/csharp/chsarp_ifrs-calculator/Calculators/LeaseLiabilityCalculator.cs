using chsarp_ifrs_calculator.Utils;
using System;

namespace IFRSCalculator.Calculations
{
    /// <summary>
    /// Provides methods for calculating the lease liability, which represents the present value
    /// of future lease payments discounted at a specific rate over the lease term.
    /// </summary>
    public static class LeaseLiabilityCalculator
    {
        /// <summary>
        /// Calculates the lease liability by discounting future lease payments.
        /// The lease liability is the present value of future lease payments over the lease term.
        /// </summary>
        /// <param name="leasePayment">The annual lease payment.</param>
        /// <param name="discountRate">The discount rate applied to future payments.</param>
        /// <param name="leaseTerm">The duration of the lease in years.</param>
        /// <returns>The calculated lease liability as the present value of future lease payments.</returns>
        public static double CalculateLeaseLiability(double leasePayment, double discountRate, int leaseTerm)
        {
            double leaseLiability = 0;

            // Loop through each year of the lease term to calculate the present value of each lease payment
            for (int t = 1; t <= leaseTerm; t++)
            {
                leaseLiability += leasePayment / Math.Pow(1 + discountRate, t);
            }

            // Return the total present value of lease payments
            return leaseLiability;
        }

        /// <summary>
        /// The Run method prompts the user for lease payment details, calculates the lease liability,
        /// and prints the result to the console.
        /// </summary>
        public static void Run()
        {
            // Print the calculation start message
            Console.WriteLine("\n=== Lease Liability Calculation ===");

            // Prompt the user to input the lease payment, discount rate, and lease term
            double leasePayment = InputHelper.GetPositiveDoubleInput("Enter annual lease payment: ");
            double discountRate = InputHelper.GetPositiveDoubleInput("Enter discount rate (%): ") / 100; // Convert to decimal form
            int leaseTerm = InputHelper.GetPositiveIntInput("Enter lease term (years): ");

            // Calculate the total lease liability
            double leaseLiability = CalculateLeaseLiability(leasePayment, discountRate, leaseTerm);

            // Print the calculated lease liability to the console
            Console.WriteLine($"\nLease Liability: {leaseLiability:C}");
        }
    }
}
