using IFRSCalculator.Calculations;
using Xunit;

namespace IFRSCalculator.Tests.RecoverableAmountCalculatorTest
{
    /// <summary>
    /// Unit tests for the RecoverableAmountCalculator class.
    /// This test suite verifies the correct functionality of the CalculateRecoverableAmount method.
    /// It also checks for appropriate handling of invalid inputs.
    /// </summary>
    public class RecoverableAmountCalculatorTests
    {
        /// <summary>
        /// Test method for verifying the correct calculation of the recoverable amount when valid input values are provided.
        /// </summary>
        [Fact]
        public void TestRecoverableAmountCalculation()
        {
            // Arrange: Set up the expected values and inputs
            double fairValue = 400;     // Valid fair value
            double valueInUse = 300;   // Valid value in use

            // Act: Calculate the recoverable amount using the RecoverableAmountCalculator
            double recoverableAmount = RecoverableAmountCalculator.CalculateRecoverableAmount(fairValue, valueInUse);

            // Assert: Ensure the correct value (maximum of fairValue and valueInUse) is returned
            Assert.Equal(400, recoverableAmount, 2);  // Compare the result with a tolerance of two decimal places
        }

        /// <summary>
        /// Test method to validate that an exception is thrown when the provided values are invalid (negative).
        /// </summary>
        [Fact]
        public void TestRecoverableAmountCalculation_InvalidValues()
        {
            // Arrange: Set up invalid values (negative fair value and value in use)
            double fairValue = -400;    // Invalid fair value
            double valueInUse = -300;   // Invalid value in use

            // Act & Assert: Ensure an ArgumentOutOfRangeException is thrown for invalid inputs
            Assert.Throws<ArgumentOutOfRangeException>(() =>
                RecoverableAmountCalculator.CalculateRecoverableAmount(fairValue, valueInUse));
        }
    }
}
