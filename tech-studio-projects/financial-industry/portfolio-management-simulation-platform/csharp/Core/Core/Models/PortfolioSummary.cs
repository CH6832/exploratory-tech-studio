using System.Collections.Generic;

namespace Core.Models
{
    public class PortfolioSummary
    {
        public decimal TotalValue { get; set; }
        public decimal ProfitLoss { get; set; }
        public List<Position> Positions { get; set; }
    }
}
