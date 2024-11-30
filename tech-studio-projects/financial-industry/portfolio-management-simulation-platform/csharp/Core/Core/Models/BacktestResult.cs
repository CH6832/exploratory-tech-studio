using System;
using System.Collections.Generic;

namespace Core.Models
{
    /// <summary>
    /// Represents the results of strategy backtesting.
    /// </summary>
    public class BacktestResult
    {
        public Guid StrategyId { get; set; }
        public List<DateTime> BuyOrders { get; } = new();
        public List<DateTime> SellOrders { get; } = new();
        public decimal TotalProfit { get; set; }
        public List<BacktestError> Errors { get; } = new();
    }

    public class BacktestError
    {
        public DateTime Timestamp { get; set; }
        public string ErrorMessage { get; set; }
    }
}
