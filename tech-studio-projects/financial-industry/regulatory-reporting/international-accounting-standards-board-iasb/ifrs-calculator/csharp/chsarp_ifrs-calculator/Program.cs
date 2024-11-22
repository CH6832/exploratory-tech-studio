using chsarp_ifrs_calculator.Calculations;
using chsarp_ifrs_calculator.Utils;
using IFRSCalculator.Calculations;

namespace IFRSCalculator
{
    /// <summary>
    /// The main entry point for the IFRS Calculator tool.
    /// This application provides various IFRS-related financial calculations through a user-friendly command-line interface.
    /// </summary>
    class Program
    {
        static void Main(string[] args)
        {
            Console.Title = "IFRS Calculator Tool";

            try
            {
                // Log the start of the application
                ShowWelcomeMessage();
                bool keepRunning = true;

                while (keepRunning)
                {
                    DisplayMainMenu();
                    string? choice = Console.ReadLine()?.Trim();

                    try
                    {
                        // Main menu options and calculation logic
                        switch (choice)
                        {
                            case "1":
                                Logger.LogInfo("Fair Value Calculation selected.");
                                FairValueCalculator.Run();
                                break;
                            case "2":
                                Logger.LogInfo("Revenue Allocation selected.");
                                RevenueAllocator.Run();
                                break;
                            case "3":
                                Logger.LogInfo("Lease Liability Calculation selected.");
                                LeaseLiabilityCalculator.Run();
                                break;
                            case "4":
                                Logger.LogInfo("Defined Benefit Obligation Calculation selected.");
                                DefinedBenefitCalculator.Run();
                                break;
                            case "5":
                                Logger.LogInfo("Deferred Tax Calculation selected.");
                                DeferredTaxCalculator.Run();
                                break;
                            case "6":
                                Logger.LogInfo("Goodwill Calculation selected.");
                                GoodwillCalculator.Run();
                                break;
                            case "7":
                                Logger.LogInfo("Recoverable Amount Calculation selected.");
                                RecoverableAmountCalculator.Run();
                                break;
                            case "0":
                                Console.WriteLine("\nThank you for using the IFRS Calculator. Goodbye!");
                                Logger.LogInfo("Application exited successfully.");
                                keepRunning = false;
                                break;
                            default:
                                Console.WriteLine("\nInvalid option. Please try again.");
                                Logger.LogWarning("Invalid menu option selected.");
                                break;
                        }
                    }
                    catch (Exception ex)
                    {
                        // Handle errors in the calculation logic
                        Console.WriteLine("\nAn error occurred while processing your request. Please try again.");
                        Logger.LogError("Error during calculation.", ex);
                    }
                }
            }
            catch (Exception ex)
            {
                // Catch critical errors in the application
                Console.WriteLine("A critical error occurred. Please contact support.");
                Logger.LogError("Critical error in application.", ex);
            }
        }

        /// <summary>
        /// Displays the welcome message to the user.
        /// </summary>
        static void ShowWelcomeMessage()
        {
            Console.ForegroundColor = ConsoleColor.Green;
            Console.WriteLine(new string('=', 50));
            Console.WriteLine("Welcome to the IFRS Calculator");
            Console.WriteLine("Effortlessly perform IFRS calculations!");
            Console.WriteLine(new string('=', 50));
            Console.ResetColor();

            Logger.LogInfo("IFRS Calculator started.");
        }

        /// <summary>
        /// Displays the main menu to the user.
        /// </summary>
        static void DisplayMainMenu()
        {
            Console.WriteLine("\nMain Menu:");
            Console.WriteLine("1. Calculate Fair Value of Bonds");
            Console.WriteLine("2. Allocate Revenue");
            Console.WriteLine("3. Compute Lease Liability");
            Console.WriteLine("4. Calculate Defined Benefit Obligation");
            Console.WriteLine("5. Determine Deferred Tax");
            Console.WriteLine("6. Compute Goodwill");
            Console.WriteLine("7. Calculate Recoverable Amount");
            Console.WriteLine("0. Exit");
            Console.Write("Enter your choice: ");
        }
    }
}
