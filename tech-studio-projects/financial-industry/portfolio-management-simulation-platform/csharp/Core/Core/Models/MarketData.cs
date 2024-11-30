namespace Core.Models
{
    /// <summary>
    /// Represents market data used in trading strategies.
    /// </summary>
    public class MarketData
    {
        public DateTime Timestamp { get; set; }
        public decimal Price { get; set; }
        // Other properties as needed
    }
}
