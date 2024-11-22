using IFRSCalculator.Calculations;
using Xunit;

namespace IFRSCalculator.Tests.RevenueAllocatorTest
{
    /// <summary>
    /// Test suite for the RevenueAllocator class.
    /// Includes tests for calculating revenue allocation based on transaction price and standalone prices,
    /// and handling invalid transaction price values.
    /// </summary>
    public class RevenueAllocatorTests
    {
        /// <summary>
        /// Test method to validate the revenue allocation calculation with valid inputs.
        /// </summary>
        [Fact]
        public void TestRevenueAllocation()
        {
            // Arrange: Set up the input values for the test
            double transactionPrice = 350;                     // Total transaction price
            var standalonePrices = new double[] { 23, 300, 200, 123 }; // Standalone prices for individual obligations

            // Act: Call the CalculateRevenueAllocation method to compute the allocations
            List<double> allocations = (List<double>)RevenueAllocator.CalculateRevenueAllocation(transactionPrice, standalonePrices);

            // Assert: Verify that the calculated allocations match the expected values
            Assert.Equal(12.46, allocations[0], 2);  // Obligation 1 allocation with 2 decimal places tolerance
            Assert.Equal(162.54, allocations[1], 2); // Obligation 2 allocation with 2 decimal places tolerance
            Assert.Equal(108.36, allocations[2], 2); // Obligation 3 allocation with 2 decimal places tolerance
            Assert.Equal(66.64, allocations[3], 2);  // Obligation 4 allocation with 2 decimal places tolerance
        }

        /// <summary>
        /// Test method to verify that an exception is thrown when the transaction price is invalid (negative).
        /// </summary>
        [Fact]
        public void TestRevenueAllocation_InvalidTransactionPrice()
        {
            // Arrange: Set up invalid input values (negative transaction price)
            double transactionPrice = -350;  // Invalid value (negative)
            var standalonePrices = new double[] { 23, 300, 200, 123 };

            // Act & Assert: Ensure that an ArgumentOutOfRangeException is thrown when calculating the revenue allocation
            Assert.Throws<ArgumentOutOfRangeException>(() =>
                RevenueAllocator.CalculateRevenueAllocation(transactionPrice, standalonePrices));
        }
    }
}
