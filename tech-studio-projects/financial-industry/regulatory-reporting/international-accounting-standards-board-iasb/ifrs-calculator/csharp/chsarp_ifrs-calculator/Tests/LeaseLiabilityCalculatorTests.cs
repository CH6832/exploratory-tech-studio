using IFRSCalculator.Calculations;
using Xunit;

namespace IFRSCalculator.Tests.LeaseLiabilityCalculatorTest
{
    /// <summary>
    /// Test suite for validating the functionality of the LeaseLiabilityCalculator class.
    /// Includes tests for correct lease liability calculation and handling of invalid inputs.
    /// </summary>
    public class LeaseLiabilityCalculatorTests
    {
        /// <summary>
        /// Test method to validate the calculation of lease liability with valid inputs.
        /// </summary>
        [Fact]
        public void TestLeaseLiabilityCalculation()
        {
            // Arrange: Set up the input values for the test
            double leasePayment = 50000;  // Annual lease payment amount
            double discountRate = 0.05;   // Discount rate applied to the lease payments
            int leaseTerm = 4;            // Lease term in years

            // Act: Call the CalculateLeaseLiability method to calculate the lease liability
            double leaseLiability = LeaseLiabilityCalculator.CalculateLeaseLiability(leasePayment, discountRate, leaseTerm);

            // Assert: Check that the calculated lease liability is correct within two decimal places tolerance
            Assert.Equal(177297.53, leaseLiability, 2); // Ensure the result matches the expected value with two decimals
        }

        /// <summary>
        /// Test method to check that an exception is thrown when the lease payment is invalid (negative value).
        /// </summary>
        [Fact]
        public void TestLeaseLiabilityCalculation_InvalidLeasePayment()
        {
            // Arrange: Set up the input values with an invalid (negative) lease payment
            double leasePayment = -50000;  // Invalid lease payment (negative value)
            double discountRate = 0.05;
            int leaseTerm = 4;

            // Act & Assert: Ensure that an ArgumentOutOfRangeException is thrown for invalid lease payment
            Assert.Throws<ArgumentOutOfRangeException>(() =>
                LeaseLiabilityCalculator.CalculateLeaseLiability(leasePayment, discountRate, leaseTerm));
        }
    }
}
