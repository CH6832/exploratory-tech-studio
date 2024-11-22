using IFRSCalculator.Calculations;
using Xunit;

namespace IFRSCalculator.Tests.GoodwillCalculatorTest
{
    /// <summary>
    /// Test suite for validating the functionality of the GoodwillCalculator class.
    /// Includes tests for correct goodwill calculation and handling of invalid inputs.
    /// </summary>
    public class GoodwillCalculatorTests
    {
        /// <summary>
        /// Test method to validate the calculation of goodwill with valid inputs.
        /// </summary>
        [Fact]
        public void TestGoodwillCalculation()
        {
            // Arrange: Set up the input values for the test
            double purchasePrice = 30000;  // The purchase price of the acquired company
            double netAssets = 15;         // The fair value of the net assets acquired

            // Act: Call the CalculateGoodwill method to calculate the goodwill
            double goodwill = GoodwillCalculator.CalculateGoodwill(purchasePrice, netAssets);

            // Assert: Check that the calculated goodwill is correct within two decimal places tolerance
            Assert.Equal(29985, goodwill, 2); // Ensure the result matches the expected value of 29985 with two decimals
        }

        /// <summary>
        /// Test method to check that an exception is thrown when the purchase price is invalid (negative value).
        /// </summary>
        [Fact]
        public void TestGoodwillCalculation_InvalidPurchasePrice()
        {
            // Arrange: Set up the input values with an invalid (negative) purchase price
            double purchasePrice = -30000;  // Invalid purchase price (negative value)
            double netAssets = 15;

            // Act & Assert: Ensure that an ArgumentOutOfRangeException is thrown for invalid purchase price
            Assert.Throws<ArgumentOutOfRangeException>(() =>
                GoodwillCalculator.CalculateGoodwill(purchasePrice, netAssets));
        }

        /// <summary>
        /// Test method to check that an exception is thrown when the net assets value is invalid (negative value).
        /// </summary>
        [Fact]
        public void TestGoodwillCalculation_InvalidNetAssets()
        {
            // Arrange: Set up the input values with an invalid (negative) net assets value
            double purchasePrice = 30000;
            double netAssets = -15;  // Invalid net assets value (negative value)

            // Act & Assert: Ensure that an ArgumentOutOfRangeException is thrown for invalid net assets
            Assert.Throws<ArgumentOutOfRangeException>(() =>
                GoodwillCalculator.CalculateGoodwill(purchasePrice, netAssets));
        }
    }
}
