using chsarp_ifrs_calculator.Utils;
using System;

namespace IFRSCalculator.Calculations
{
    /// <summary>
    /// Provides methods to calculate the defined benefit obligation based on the annual benefit payment, 
    /// discount rate, and the benefit duration (in years).
    /// </summary>
    public static class DefinedBenefitCalculator
    {
        /// <summary>
        /// Calculates the defined benefit obligation based on the present value of future benefit payments.
        /// The obligation is calculated by discounting each benefit payment to its present value using the given discount rate.
        /// </summary>
        /// <param name="benefitPayment">The annual benefit payment amount.</param>
        /// <param name="discountRate">The discount rate (as a decimal, e.g., 0.05 for 5%).</param>
        /// <param name="duration">The number of years for which the benefits will be paid.</param>
        /// <returns>The calculated defined benefit obligation.</returns>
        /// <exception cref="ArgumentOutOfRangeException">
        /// Thrown when the benefit payment, discount rate, or duration is less than or equal to zero.
        /// </exception>
        public static double CalculateDefinedBenefitObligation(double benefitPayment, double discountRate, int duration)
        {
            // Ensure the input values are valid (non-negative for benefit payment and non-zero for duration)
            if (benefitPayment <= 0 || discountRate <= 0 || duration <= 0)
            {
                throw new ArgumentOutOfRangeException("Benefit payment, discount rate, and duration must be greater than zero.");
            }

            // Initialize the obligation to 0
            double obligation = 0;

            // Calculate the obligation by discounting each future payment to present value
            for (int t = 1; t <= duration; t++)
            {
                obligation += benefitPayment / Math.Pow(1 + discountRate, t);
            }

            // Return the total present value of the defined benefit obligation
            return obligation;
        }

        /// <summary>
        /// The Run method prompts the user for the necessary inputs and calculates the defined benefit obligation.
        /// It then prints the result to the console.
        /// </summary>
        public static void Run()
        {
            // Print the calculation start message
            Console.WriteLine("\n=== Defined Benefit Obligation ===");

            // Prompt user to input the annual benefit payment, discount rate, and duration
            double benefitPayment = InputHelper.GetPositiveDoubleInput("Enter annual benefit payment: ");
            double discountRate = InputHelper.GetPositiveDoubleInput("Enter discount rate (%): ") / 100;
            int duration = InputHelper.GetPositiveIntInput("Enter benefit duration (years): ");

            // Calculate the defined benefit obligation using the provided values
            double obligation = CalculateDefinedBenefitObligation(benefitPayment, discountRate, duration);

            // Print the calculated defined benefit obligation to the console
            Console.WriteLine($"\nDefined Benefit Obligation: {obligation:C}");
        }
    }
}
