using Core.Services;
using Core.Utils;
using System;

namespace Core.Commands.Portfolio
{
    public class AddPortfolioCommand
    {
        /// <summary>
        /// Executes the add portfolio command.
        /// </summary>
        /// <param name="args">Command-line arguments</param>
        public static void Execute(string[] args)
        {
            try
            {
                Console.WriteLine("Enter Portfolio Name:");
                string name = Console.ReadLine();
                Console.WriteLine("Enter Initial Balance:");
                decimal balance = Convert.ToDecimal(Console.ReadLine());

                var service = new PortfolioService();
                var portfolio = service.CreatePortfolio(name, balance);

                Console.WriteLine($"Portfolio '{portfolio.Name}' created successfully with balance {portfolio.Balance:C}.");
            }
            catch (FormatException ex)
            {
                ErrorHandler.HandleException(new ApplicationException("Invalid input. Please enter numeric values for balance.", ex));
            }
            catch (Exception ex)
            {
                ErrorHandler.HandleException(ex);
            }
        }
    }
}
