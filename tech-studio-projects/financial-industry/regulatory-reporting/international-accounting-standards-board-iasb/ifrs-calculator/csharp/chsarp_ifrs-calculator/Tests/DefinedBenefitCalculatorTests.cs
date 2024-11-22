using IFRSCalculator.Calculations;
using Xunit;

namespace IFRSCalculator.Tests.DefinedBenefitCalculatorTest
{
    /// <summary>
    /// Test suite for validating the functionality of the DefinedBenefitCalculator class.
    /// Includes tests for correct defined benefit obligation calculation and handling of invalid inputs.
    /// </summary>
    public class DefinedBenefitCalculatorTests
    {
        /// <summary>
        /// Test method to validate the calculation of the defined benefit obligation with valid inputs.
        /// </summary>
        [Fact]
        public void TestDefinedBenefitObligationCalculation()
        {
            // Arrange: Set up the input values for the test
            double benefitPayment = 120000;   // The annual benefit payment
            double discountRate = 0.07;       // The discount rate to apply
            int duration = 4;                 // The duration of the benefit (in years)

            // Act: Call the CalculateDefinedBenefitObligation method to get the result
            double obligation = DefinedBenefitCalculator.CalculateDefinedBenefitObligation(benefitPayment, discountRate, duration);

            // Assert: Check that the obligation is correctly calculated to two decimal places
            Assert.Equal(406465.35, obligation, 2); // Ensure the result is within a tolerance of 2 decimal places
        }

        /// <summary>
        /// Test method to check that an exception is thrown when the benefit payment is invalid (negative value).
        /// </summary>
        [Fact]
        public void TestDefinedBenefitObligation_InvalidBenefitPayment()
        {
            // Arrange: Set up the input values with an invalid (negative) benefit payment
            double benefitPayment = -120000;  // Invalid benefit payment value (negative)
            double discountRate = 0.07;
            int duration = 4;

            // Act & Assert: Ensure that an ArgumentOutOfRangeException is thrown for invalid benefit payment
            Assert.Throws<ArgumentOutOfRangeException>(() =>
                DefinedBenefitCalculator.CalculateDefinedBenefitObligation(benefitPayment, discountRate, duration));
        }
    }
}
