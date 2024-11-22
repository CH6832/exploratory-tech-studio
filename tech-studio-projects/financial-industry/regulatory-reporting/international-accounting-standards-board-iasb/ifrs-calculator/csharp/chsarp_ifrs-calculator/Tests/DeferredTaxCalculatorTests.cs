using IFRSCalculator.Calculations;
using Xunit;

namespace IFRSCalculator.Tests.DeferredTaxCalculatorTest
{
    /// <summary>
    /// Test suite for validating the functionality of the DeferredTaxCalculator class.
    /// Includes tests for correct deferred tax calculation and handling of invalid inputs.
    /// </summary>
    public class DeferredTaxCalculatorTests
    {
        /// <summary>
        /// Test method to validate the deferred tax calculation with valid inputs.
        /// </summary>
        [Fact]
        public void TestDeferredTaxCalculation()
        {
            // Arrange: Set up the input values
            double carryingAmount = 4000;   // The carrying amount of the asset
            double taxBase = 300;           // The tax base of the asset
            double taxRate = 0.15;          // The tax rate to apply

            // Act: Call the CalculateDeferredTax method to get the result
            double deferredTax = DeferredTaxCalculator.CalculateDeferredTax(carryingAmount, taxBase, taxRate);

            // Assert: Check that the deferred tax is correctly calculated to two decimal places
            Assert.Equal(555, deferredTax, 2); // Ensure the result is within a tolerance of 2 decimal places
        }

        /// <summary>
        /// Test method to check that an exception is thrown when the tax base is invalid (negative value).
        /// </summary>
        [Fact]
        public void TestDeferredTaxCalculation_InvalidTaxBase()
        {
            // Arrange: Set up the input values with an invalid tax base
            double carryingAmount = 4000;
            double taxBase = -300;  // Invalid tax base value (negative)
            double taxRate = 0.15;

            // Act & Assert: Ensure that an ArgumentOutOfRangeException is thrown for invalid tax base
            Assert.Throws<ArgumentOutOfRangeException>(() =>
                DeferredTaxCalculator.CalculateDeferredTax(carryingAmount, taxBase, taxRate));
        }
    }
}
