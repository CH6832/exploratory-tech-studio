using System;

namespace Core.Models
{
    /// <summary>
    /// Represents a trading strategy.
    /// </summary>
    public class Strategy
    {
        public Guid Id { get; set; }
        public string Name { get; set; }
        public Func<MarketData, TradeDecision>? ExecuteLogic { get; set; }
        public DateTime CreatedAt { get; set; }
    }
}
