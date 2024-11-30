using System;

namespace Core.Models
{
    public class PortfolioHistory
    {
        public DateTime Date { get; set; }
        public string Action { get; set; }
        public string Asset { get; set; }
        public int QuantityChange { get; set; }
        public decimal ValueChange { get; set; }
    }
}
