using System;

namespace chsarp_ifrs_calculator.Utils
{
    /// <summary>
    /// A helper class that provides methods to get validated user input, ensuring that the input is positive and of the correct type.
    /// </summary>
    public static class InputHelper
    {
        /// <summary>
        /// Prompts the user for a positive double input, retrying until valid input is received.
        /// Logs any invalid input attempts and ensures the input is positive.
        /// </summary>
        /// <param name="prompt">The message to display to the user.</param>
        /// <returns>A valid positive double value entered by the user.</returns>
        public static double GetPositiveDoubleInput(string prompt)
        {
            Console.Write(prompt);
            double input;

            while (true)
            {
                // Read user input
                string userInput = Console.ReadLine();

                // Validate input
                if (double.TryParse(userInput, out input) && input >= 0)
                {
                    // Log successful input
                    Logger.LogInfo($"User entered valid positive double: {input}");
                    return input;
                }
                else
                {
                    // Log invalid input attempt
                    Logger.LogWarning($"Invalid input received for double value. User input: {userInput}");
                    Console.Write("Invalid input. Please enter a positive number: ");
                }
            }
        }

        /// <summary>
        /// Prompts the user for a positive integer input, retrying until valid input is received.
        /// Logs any invalid input attempts and ensures the input is positive.
        /// </summary>
        /// <param name="prompt">The message to display to the user.</param>
        /// <returns>A valid positive integer value entered by the user.</returns>
        public static int GetPositiveIntInput(string prompt)
        {
            Console.Write(prompt);
            int input;

            while (true)
            {
                // Read user input
                string userInput = Console.ReadLine();

                // Validate input
                if (int.TryParse(userInput, out input) && input > 0)
                {
                    // Log successful input
                    Logger.LogInfo($"User entered valid positive integer: {input}");
                    return input;
                }
                else
                {
                    // Log invalid input attempt
                    Logger.LogWarning($"Invalid input received for integer value. User input: {userInput}");
                    Console.Write("Invalid input. Please enter a positive integer: ");
                }
            }
        }
    }
}
