using Core.Models;
using System;
using System.Collections.Generic;
using System.Linq;

namespace Core.Services
{
    /// <summary>
    /// Service for managing portfolios.
    /// </summary>
    public class PortfolioService
    {
        private readonly List<Portfolio> _portfolios = new();

        /// <summary>
        /// Creates a new portfolio.
        /// </summary>
        /// <param name="name">Portfolio name</param>
        /// <param name="initialBalance">Initial balance</param>
        /// <returns>Created Portfolio</returns>
        public Portfolio CreatePortfolio(string name, decimal initialBalance)
        {
            if (string.IsNullOrEmpty(name))
                throw new ArgumentException("Portfolio name cannot be null or empty.");

            if (initialBalance < 0)
                throw new ArgumentException("Initial balance must be positive.");

            var portfolio = new Portfolio
            {
                Id = Guid.NewGuid(),
                Name = name,
                Balance = initialBalance,
                CreatedAt = DateTime.UtcNow,
                Positions = new List<Position>(),
                History = new List<PortfolioHistory>()
            };

            _portfolios.Add(portfolio);
            return portfolio;
        }

        /// <summary>
        /// Retrieves all portfolios.
        /// </summary>
        /// <returns>List of portfolios</returns>
        public IEnumerable<Portfolio> GetPortfolios()
        {
            return _portfolios;
        }

        /// <summary>
        /// Retrieves the portfolio summary for a specific portfolio.
        /// </summary>
        /// <returns>Portfolio summary object</returns>
        public PortfolioSummary GetPortfolioSummary(Guid portfolioId)
        {
            var portfolio = GetPortfolioById(portfolioId);
            if (portfolio == null)
                throw new KeyNotFoundException($"Portfolio with ID {portfolioId} not found.");

            var totalValue = portfolio.Positions.Sum(p => p.Quantity * p.CurrentPrice);
            var profitLoss = totalValue - portfolio.Balance;

            return new PortfolioSummary
            {
                TotalValue = totalValue,
                ProfitLoss = profitLoss,
                Positions = portfolio.Positions
            };
        }

        /// <summary>
        /// Retrieves the history of a specific portfolio.
        /// </summary>
        /// <returns>Portfolio history entries</returns>
        public IEnumerable<PortfolioHistory> GetPortfolioHistory(Guid portfolioId)
        {
            var portfolio = GetPortfolioById(portfolioId);
            if (portfolio == null)
                throw new KeyNotFoundException($"Portfolio with ID {portfolioId} not found.");

            return portfolio.History;
        }

        /// <summary>
        /// Retrieves detailed information for a specific asset in a portfolio.
        /// </summary>
        /// <param name="portfolioId">Portfolio ID</param>
        /// <param name="assetName">Asset name</param>
        /// <returns>Asset details</returns>
        public Position GetAssetDetails(Guid portfolioId, string assetName)
        {
            if (string.IsNullOrWhiteSpace(assetName))
                throw new ArgumentException("Asset name cannot be null or empty.");

            var portfolio = GetPortfolioById(portfolioId);
            if (portfolio == null)
                throw new KeyNotFoundException($"Portfolio with ID {portfolioId} not found.");

            var position = portfolio.Positions.FirstOrDefault(p => p.Asset.Equals(assetName, StringComparison.OrdinalIgnoreCase));
            if (position == null)
                throw new KeyNotFoundException($"Asset '{assetName}' not found in portfolio.");

            return position;
        }

        /// <summary>
        /// Helper method to retrieve a portfolio by its ID.
        /// </summary>
        /// <param name="portfolioId">Portfolio ID</param>
        /// <returns>Portfolio object</returns>
        private Portfolio GetPortfolioById(Guid portfolioId)
        {
            return _portfolios.FirstOrDefault(p => p.Id == portfolioId);
        }

        internal object GetAssetDetails(string assetName)
        {
            throw new NotImplementedException();
        }

        internal IEnumerable<PortfolioHistory?> GetPortfolioHistory()
        {
            throw new NotImplementedException();
        }

        internal object GetPortfolioSummary()
        {
            throw new NotImplementedException();
        }
    }
}
