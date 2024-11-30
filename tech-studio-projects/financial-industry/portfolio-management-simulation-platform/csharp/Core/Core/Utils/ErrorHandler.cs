using System;

namespace Core.Utils
{
    public static class ErrorHandler
    {
        public static void HandleException(Exception ex)
        {
            Logger.LogError(ex.Message);
            Console.WriteLine("An error occurred. Please check the logs for more details.");
        }
    }
}
