using System;
using System.Collections.Generic;

namespace Core.Models
{
    public class Portfolio
    {
        public Guid Id { get; set; }
        public string Name { get; set; }
        public decimal Balance { get; set; }
        public DateTime CreatedAt { get; set; }
        public List<Position> Positions { get; set; }
        public List<PortfolioHistory> History { get; set; }
    }
}
