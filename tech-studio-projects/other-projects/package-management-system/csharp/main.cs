using System;

class Program
{
    static void Main(string[] args)
    {
        var pkgManager = new PkgManager();

        if (args.Length < 1)
        {
            Console.WriteLine("Usage: <command> [package_id]");
            Console.WriteLine("Commands: install, uninstall, reset");
            return;
        }

        string command = args[0];
        if (command.Equals("install", StringComparison.OrdinalIgnoreCase))
        {
            if (args.Length < 2)
            {
                Console.WriteLine("Usage: install <package_id>");
                return;
            }
            string pkgId = args[1];
            pkgManager.Install(pkgId);
        }
        else if (command.Equals("uninstall", StringComparison.OrdinalIgnoreCase))
        {
            if (args.Length < 2)
            {
                Console.WriteLine("Usage: uninstall <package_id>");
                return;
            }
            string pkgId = args[1];
            pkgManager.Uninstall(pkgId);
        }
        else if (command.Equals("reset", StringComparison.OrdinalIgnoreCase))
        {
            pkgManager.Reset();
        }
        else
        {
            Console.WriteLine($"Unknown command: {command}");
            Console.WriteLine("Commands: install, uninstall, reset");
        }
    }
}
