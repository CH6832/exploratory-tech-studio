using System;
using Core.Services;
using Core.Utils;

namespace Core.Commands.Portfolio
{
    /// <summary>
    /// Provides commands to view and analyze portfolio data via CLI.
    /// </summary>
    public class ViewPortfolioCommands
    {
        private readonly PortfolioService _portfolioService;

        /// <summary>
        /// Initializes the ViewPortfolioCommands with required services.
        /// </summary>
        /// <param name="portfolioService">Instance of PortfolioService</param>
        public ViewPortfolioCommands(PortfolioService portfolioService)
        {
            _portfolioService = portfolioService ?? throw new ArgumentNullException(nameof(portfolioService));
        }

        /// <summary>
        /// Displays the history of portfolio changes.
        /// </summary>
        public void ShowPortfolioHistory()
        {
            try
            {
                Console.WriteLine("Fetching portfolio history...\n");

                // Assuming GetPortfolioHistory returns a collection of PortfolioHistoryRecord objects
                var history = _portfolioService.GetPortfolioHistory();

                foreach (var record in history)
                {
                    Console.WriteLine($"Date: {record.Date}, Action: {record.Action}, Asset: {record.Asset}, Change: {record.QuantityChange}, Value: {record.ValueChange:C}");
                }

                Console.WriteLine("\nPortfolio history displayed successfully.");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error displaying portfolio history: {ex.Message}");
                Logger.LogError($"Error in ShowPortfolioHistory: {ex}");
            }
        }

        /// <summary>
        /// Displays detailed information for a specific asset in the portfolio.
        /// </summary>
        /// <param name="assetName">Name of the asset</param>
        public void ShowAssetDetails(Guid portfolioId, string assetName)
        {
            if (string.IsNullOrWhiteSpace(assetName))
            {
                Console.WriteLine("Error: Asset name cannot be empty.");
                return;
            }

            try
            {
                Console.WriteLine($"Fetching details for asset '{assetName}'...\n");

                // Fetch asset details using the PortfolioService
                var assetDetails = _portfolioService.GetAssetDetails(portfolioId, assetName);

                Console.WriteLine($"Asset: {assetDetails.Asset}");
                Console.WriteLine($"Quantity: {assetDetails.Quantity}");
                Console.WriteLine($"Current Price: {assetDetails.CurrentPrice:C}");
                Console.WriteLine($"Total Value: {assetDetails.TotalValue:C}");
                Console.WriteLine($"Profit/Loss: {assetDetails.ProfitLoss:C}");
                Console.WriteLine("\nAsset details displayed successfully.");
            }
            catch (KeyNotFoundException ex)
            {
                Console.WriteLine(ex.Message);
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error displaying asset details: {ex.Message}");
                Logger.LogError($"Error in ShowAssetDetails for asset '{assetName}': {ex}");
            }
        }
    }
}
