using Core.Models;
using Core.Utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Core.Services
{
    /// <summary>
    /// Service responsible for managing trading strategies, including registration, testing, and execution.
    /// </summary>
    public class StrategyService
    {
        private readonly List<Strategy> _strategies = new();
        private readonly object _lock = new();

        /// <summary>
        /// Registers a new trading strategy.
        /// </summary>
        /// <param name="name">The name of the strategy.</param>
        /// <param name="logic">Delegate containing the strategy's trading logic.</param>
        /// <returns>The registered strategy.</returns>
        /// <exception cref="ArgumentException">Thrown if the strategy name is null or empty.</exception>
        /// <exception cref="ArgumentNullException">Thrown if the strategy logic is null.</exception>
        /// <exception cref="InvalidOperationException">Thrown if a strategy with the same name already exists.</exception>
        public Strategy RegisterStrategy(string name, Func<MarketData, TradeDecision> logic)
        {
            if (string.IsNullOrWhiteSpace(name))
                throw new ArgumentException("Strategy name cannot be null or empty.", nameof(name));

            if (logic == null)
                throw new ArgumentNullException(nameof(logic), "Strategy logic must not be null.");

            lock (_lock)
            {
                if (_strategies.Any(s => s.Name.Equals(name, StringComparison.OrdinalIgnoreCase)))
                    throw new InvalidOperationException($"A strategy with the name '{name}' already exists.");

                var strategy = new Strategy
                {
                    Id = Guid.NewGuid(),
                    Name = name,
                    ExecuteLogic = logic,
                    CreatedAt = DateTime.UtcNow
                };

                _strategies.Add(strategy);

                return strategy;
            }
        }

        /// <summary>
        /// Retrieves all registered strategies.
        /// </summary>
        /// <returns>A list of registered strategies.</returns>
        public IEnumerable<Strategy> GetStrategies()
        {
            lock (_lock)
            {
                return _strategies.ToList();
            }
        }

        /// <summary>
        /// Executes a strategy in a live market environment using a real-time market data stream.
        /// </summary>
        /// <param name="strategyId">The ID of the strategy to execute.</param>
        /// <param name="marketDataStream">An asynchronous enumerable of market data.</param>
        /// <exception cref="KeyNotFoundException">Thrown if the strategy with the given ID does not exist.</exception>
        public void ExecuteStrategy(Guid strategyId, IAsyncEnumerable<MarketData> marketDataStream)
        {
            var strategy = GetStrategyById(strategyId);
            if (strategy == null)
                throw new KeyNotFoundException($"Strategy with ID {strategyId} not found.");

            _ = ExecuteStrategyAsync(strategy, marketDataStream);
        }

        /// <summary>
        /// Asynchronously executes a strategy in a live market environment.
        /// </summary>
        /// <param name="strategy">The strategy to execute.</param>
        /// <param name="marketDataStream">An asynchronous enumerable of market data.</param>
        private async Task ExecuteStrategyAsync(Strategy strategy, IAsyncEnumerable<MarketData> marketDataStream)
        {
            await foreach (var data in marketDataStream)
            {
                try
                {
                    var decision = strategy.ExecuteLogic(data);

                    if (decision.Action != TradeAction.Hold)
                    {
                        Console.WriteLine($"Strategy '{strategy.Name}' Decision: {decision.Action} at {data.Timestamp}");
                    }
                }
                catch (Exception ex)
                {
                    Logger.LogError($"Error executing strategy '{strategy.Name}': {ex.Message}");
                }
            }
        }

        /// <summary>
        /// Retrieves a strategy by its unique ID.
        /// </summary>
        /// <param name="strategyId">The ID of the strategy to retrieve.</param>
        /// <returns>The strategy object, or null if not found.</returns>
        private Strategy GetStrategyById(Guid strategyId)
        {
            lock (_lock)
            {
                return _strategies.FirstOrDefault(s => s.Id == strategyId);
            }
        }
    }
}
