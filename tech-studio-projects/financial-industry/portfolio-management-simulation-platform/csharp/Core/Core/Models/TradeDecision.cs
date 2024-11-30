namespace Core.Models
{
    /// <summary>
    /// Represents a trade decision made by a strategy.
    /// </summary>
    public class TradeDecision
    {
        public TradeAction Action { get; set; }
        public decimal ProfitOrLoss { get; set; }
    }

    public enum TradeAction
    {
        Buy,
        Sell,
        Hold
    }
}
