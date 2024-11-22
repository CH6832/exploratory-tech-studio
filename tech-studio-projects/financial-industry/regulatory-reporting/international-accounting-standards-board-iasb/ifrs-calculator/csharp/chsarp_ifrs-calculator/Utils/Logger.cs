using Microsoft.Extensions.Logging;

namespace chsarp_ifrs_calculator.Utils
{
    /// <summary>
    /// Provides a platform-independent logging mechanism using Microsoft.Extensions.Logging.
    /// </summary>
    public static class Logger
    {
        private static readonly ILoggerFactory LoggerFactory;
        private static readonly ILogger LoggerInstance;

        static Logger()
        {
            // Configure the logger factory
            LoggerFactory = Microsoft.Extensions.Logging.LoggerFactory.Create(static builder =>
            {
                builder.AddConsole(); // Log to the console
                builder.AddDebug();  // Log to the debug output
            });

            LoggerInstance = LoggerFactory.CreateLogger("IFRSCalculator");
        }

        /// <summary>
        /// Logs an informational message.
        /// </summary>
        /// <param name="message">The message to log.</param>
        public static void LogInfo(string message)
        {
            LoggerInstance.LogInformation(message);
        }

        /// <summary>
        /// Logs a warning message.
        /// </summary>
        /// <param name="message">The warning message to log.</param>
        public static void LogWarning(string message)
        {
            LoggerInstance.LogWarning(message);
        }

        /// <summary>
        /// Logs an error message.
        /// </summary>
        /// <param name="message">The error message to log.</param>
        /// <param name="exception">Optional exception details.</param>
        public static void LogError(string message, Exception? exception = null)
        {
            if (exception != null)
            {
                LoggerInstance.LogError(exception, message);
            }
            else
            {
                LoggerInstance.LogError(message);
            }
        }
    }
}
