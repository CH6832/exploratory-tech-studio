using System;
using chsarp_ifrs_calculator.Calculations;
using IFRSCalculator.Calculations;
using Xunit;

namespace IFRSCalculator.Tests.FairValueCalculatorTest
{
    /// <summary>
    /// Test suite for validating the functionality of the FairValueCalculator class.
    /// Includes tests for correct fair value calculation and handling of invalid inputs.
    /// </summary>
    public class FairValueCalculatorTests
    {
        /// <summary>
        /// Test method to validate the calculation of fair value with valid inputs.
        /// </summary>
        [Fact]
        public void TestFairValueCalculation()
        {
            // Arrange: Set up the input values for the test
            double cashFlow = 3000;       // The expected cash flow per period
            double discountRate = 0.20;   // The discount rate to apply
            int periods = 4;              // The number of periods for the cash flow

            // Act: Call the Run method of FairValueCalculator to calculate the fair value
            double fairValue = FairValueCalculator.Run(cashFlow, discountRate, periods);

            // Assert: Check that the calculated fair value is correct within two decimal places tolerance
            Assert.Equal(7766.20, fairValue, 2); // Ensure the result matches the expected value
        }

        /// <summary>
        /// Test method to check that an exception is thrown when the cash flow is invalid (negative value).
        /// </summary>
        [Fact]
        public void TestFairValueCalculation_InvalidCashFlow()
        {
            // Arrange: Set up the input values with an invalid (negative) cash flow
            double cashFlow = -3000;     // Invalid cash flow (negative value)
            double discountRate = 0.20;
            int periods = 4;

            // Act & Assert: Ensure that an ArgumentOutOfRangeException is thrown for invalid cash flow
            Assert.Throws<ArgumentOutOfRangeException>(() =>
                FairValueCalculator.Run(cashFlow, discountRate, periods));
        }
    }
}
