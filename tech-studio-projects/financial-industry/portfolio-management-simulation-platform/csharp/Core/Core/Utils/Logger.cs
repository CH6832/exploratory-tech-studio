using System;

namespace Core.Utils
{
    public static class Logger
    {
        public static void Log(string message)
        {
            Console.WriteLine($"[INFO] {DateTime.UtcNow}: {message}");
        }

        public static void LogError(string message)
        {
            Console.WriteLine($"[ERROR] {DateTime.UtcNow}: {message}");
        }
    }
}
