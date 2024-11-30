using Core.Services;
using Core.Utils;
using System;
using System.Collections.Generic;
using System.IO;

namespace Core.Commands.DataIngestion
{
    public class FetchMarketDataCommand
    {
        public static void Execute(string[] args)
        {
            try
            {
                Console.WriteLine("Fetching market data...");

                // Check if the file path argument is provided
                if (args.Length == 0)
                {
                    Console.WriteLine("Usage: corecli fetch-market-data <csv-file-path>");
                    return;
                }

                var filePath = args[0];

                // Ensure the file exists
                if (!File.Exists(filePath))
                {
                    Console.WriteLine("Error: File not found.");
                    return;
                }

                // Read and parse the CSV file
                var marketData = ReadCsv(filePath);

                if (marketData.Count == 0)
                {
                    Console.WriteLine("No data found in the CSV file.");
                    return;
                }

                // Assuming DataService is the class to process this data
                var service = new DataService();

                // Process the fetched data (just an example, this can be customized)
                foreach (var record in marketData)
                {
                    Console.WriteLine($"Processed {record.Asset} in {record.PortfolioName} with quantity change: {record.QuantityChange}, value change: {record.ValueChange:C}");
                }

                Console.WriteLine("Market data fetched and processed.");
            }
            catch (Exception ex)
            {
                ErrorHandler.HandleException(ex);
            }
        }

        /// <summary>
        /// Reads and parses the CSV file into a list of market data records.
        /// </summary>
        /// <param name="filePath">Path to the CSV file</param>
        /// <returns>List of MarketData records</returns>
        private static List<MarketDataRecord> ReadCsv(string filePath)
        {
            var marketDataRecords = new List<MarketDataRecord>();

            // Read all lines from the CSV file
            var lines = File.ReadAllLines(filePath);

            // Loop through each line, skip the header
            for (int i = 1; i < lines.Length; i++)
            {
                var line = lines[i].Split(',');

                // Ensure the line has the correct number of columns
                if (line.Length != 6)
                    continue;

                var record = new MarketDataRecord
                {
                    Date = DateTime.Parse(line[0]),
                    PortfolioName = line[1],
                    Action = line[2],
                    Asset = line[3],
                    QuantityChange = int.Parse(line[4]),
                    ValueChange = decimal.Parse(line[5])
                };

                marketDataRecords.Add(record);
            }

            return marketDataRecords;
        }
    }

    /// <summary>
    /// Represents a record from the market data CSV.
    /// </summary>
    public class MarketDataRecord
    {
        public DateTime Date { get; set; }
        public string PortfolioName { get; set; }
        public string Action { get; set; }
        public string Asset { get; set; }
        public int QuantityChange { get; set; }
        public decimal ValueChange { get; set; }
    }
}
