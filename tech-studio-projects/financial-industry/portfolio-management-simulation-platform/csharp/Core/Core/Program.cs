using System;
using Core.Utils;

namespace Core
{
    public class Program
    {
        // Properly typing the command handlers to an interface or class that includes the `Handle` method
        public static ICommandHandler? PortfolioCommandHandler { get; private set; }
        public static ICommandHandler? StrategyCommandHandler { get; private set; }
        public static ICommandHandler? DataIngestionCommandHandler { get; private set; }

        /// <summary>
        /// Entry point for the CLI application.
        /// </summary>
        /// <param name="args">Command-line arguments</param>
        static void Main(string[] args)
        {
            try
            {
                Console.WriteLine("Starting the Portfolio Management CLI Tool...");
                Logger.Log("Initializing application...");

                if (args.Length == 0)
                {
                    Console.WriteLine("No command provided.");
                    Console.WriteLine("Usage: corecli <command> [options]");
                    return;
                }

                Console.WriteLine("Initializing command handlers...");
                InitializeHandlers();

                Console.WriteLine("Command handlers initialized successfully.");

                string command = args[0].ToLower();
                Console.WriteLine($"Processing command: {command}");

                switch (command)
                {
                    case "portfolio":
                        Console.WriteLine("Delegating to PortfolioCommandHandler...");
                        PortfolioCommandHandler?.Handle(args);
                        break;
                    case "strategy":
                        Console.WriteLine("Delegating to StrategyCommandHandler...");
                        StrategyCommandHandler?.Handle(args);
                        break;
                    case "data":
                        Console.WriteLine("Delegating to DataIngestionCommandHandler...");
                        DataIngestionCommandHandler?.Handle(args);
                        break;
                    default:
                        Console.WriteLine($"Unknown command: {command}");
                        break;
                }

                Console.WriteLine("Command processing completed.");
            }
            catch (Exception ex)
            {
                Console.WriteLine("An error occurred during execution. See details below:");
                Console.WriteLine(ex.Message);
                ErrorHandler.HandleException(ex);
            }
            finally
            {
                Console.WriteLine("Exiting the Portfolio Management CLI Tool.");
            }
        }

        /// <summary>
        /// Initializes the command handlers.
        /// </summary>
        private static void InitializeHandlers()
        {
            Console.WriteLine("Creating PortfolioCommandHandler...");
            PortfolioCommandHandler = new PortfolioCommandHandler();
            Console.WriteLine("PortfolioCommandHandler created.");

            Console.WriteLine("Creating StrategyCommandHandler...");
            StrategyCommandHandler = new StrategyCommandHandler();
            Console.WriteLine("StrategyCommandHandler created.");

            Console.WriteLine("Creating DataIngestionCommandHandler...");
            DataIngestionCommandHandler = new DataIngestionCommandHandler();
            Console.WriteLine("DataIngestionCommandHandler created.");
        }
    }

    /// <summary>
    /// Defines a common interface for command handlers.
    /// </summary>
    public interface ICommandHandler
    {
        void Handle(string[] args);
    }

    /// <summary>
    /// Example implementation for Portfolio commands.
    /// </summary>
    public class PortfolioCommandHandler : ICommandHandler
    {
        public void Handle(string[] args)
        {
            Console.WriteLine("PortfolioCommandHandler is processing the request...");
            Console.WriteLine($"Arguments: {string.Join(" ", args)}");
            // Simulate work
            Console.WriteLine("Portfolio operations in progress...");
            System.Threading.Thread.Sleep(500); // Simulated delay
            Console.WriteLine("Portfolio operations completed.");
        }
    }

    /// <summary>
    /// Example implementation for Strategy commands.
    /// </summary>
    public class StrategyCommandHandler : ICommandHandler
    {
        public void Handle(string[] args)
        {
            Console.WriteLine("StrategyCommandHandler is processing the request...");
            Console.WriteLine($"Arguments: {string.Join(" ", args)}");
            // Simulate work
            Console.WriteLine("Strategy operations in progress...");
            System.Threading.Thread.Sleep(500); // Simulated delay
            Console.WriteLine("Strategy operations completed.");
        }
    }

    /// <summary>
    /// Example implementation for Data Ingestion commands.
    /// </summary>
    public class DataIngestionCommandHandler : ICommandHandler
    {
        public void Handle(string[] args)
        {
            Console.WriteLine("DataIngestionCommandHandler is processing the request...");
            Console.WriteLine($"Arguments: {string.Join(" ", args)}");
            // Simulate work
            Console.WriteLine("Data ingestion operations in progress...");
            System.Threading.Thread.Sleep(500); // Simulated delay
            Console.WriteLine("Data ingestion operations completed.");
        }
    }
}
