using System;
using System.Collections.Generic;
using System.IO;
using Newtonsoft.Json;

public class PkgManager
{
    private const string PKG_JSON_PATH = "packages.json";
    private const string STORAGE_FOLDER = "storage";
    private const string INSTALLED_FOLDER = "installed";
    private const string LOG_FILE = "log.txt";

    private Dictionary<string, PackageInfo> packages = new Dictionary<string, PackageInfo>();

    public PkgManager()
    {
        LoadPackages();
    }

    ~PkgManager()
    {
        SavePackages();
    }

    private void LoadPackages()
    {
        if (File.Exists(PKG_JSON_PATH))
        {
            var json = File.ReadAllText(PKG_JSON_PATH);
            var root = JsonConvert.DeserializeObject<Dictionary<string, Dictionary<string, string>>>(json);

            foreach (var pkg in root)
            {
                packages[pkg.Key] = new PackageInfo(pkg.Value);
            }
        }
    }

    private void SavePackages()
    {
        var root = new Dictionary<string, Dictionary<string, string>>();

        foreach (var pkg in packages)
        {
            root[pkg.Key] = pkg.Value.ToDictionary();
        }

        var json = JsonConvert.SerializeObject(root, Formatting.Indented);
        File.WriteAllText(PKG_JSON_PATH, json);
    }

    public void Install(string pkgId)
    {
        if (!packages.ContainsKey(pkgId))
        {
            Log($"Package {pkgId} not found.", "ERROR");
            Console.WriteLine("Package ID not found.");
            return;
        }

        var pkgInfo = packages[pkgId];
        var pkgUrl = pkgInfo["_pkgurl"];

        CreateDirectory(STORAGE_FOLDER);
        CreateDirectory(INSTALLED_FOLDER);

        var pkgPath = Path.Combine(STORAGE_FOLDER, pkgUrl);
        if (!File.Exists(pkgPath))
        {
            Log("Package file not found.");
            Console.WriteLine("Package file not found.");
            return;
        }

        var destPath = Path.Combine(INSTALLED_FOLDER, pkgUrl);
        if (File.Exists(destPath))
        {
            Log($"Package {pkgId} is already installed.");
            Console.WriteLine($"Package {pkgId} is already installed.");
            return;
        }

        try
        {
            File.Copy(pkgPath, destPath);
            Log($"Package {pkgId} installed successfully.");
            Console.WriteLine($"Package {pkgId} installed successfully.");
        }
        catch (Exception e)
        {
            Log($"Failed to install package {pkgId}: {e.Message}", "ERROR");
            Console.WriteLine($"Failed to install package {pkgId}: {e.Message}");
        }
    }

    public void Uninstall(string pkgId)
    {
        var installedPath = Path.Combine(INSTALLED_FOLDER, $"{pkgId}.zip");
        if (File.Exists(installedPath))
        {
            try
            {
                File.Delete(installedPath);
                Log($"Package {pkgId} uninstalled successfully.");
                Console.WriteLine($"Package {pkgId} uninstalled successfully.");
            }
            catch (Exception e)
            {
                Log($"Failed to uninstall package {pkgId}: {e.Message}", "ERROR");
                Console.WriteLine($"Failed to uninstall package {pkgId}: {e.Message}");
            }
        }
        else
        {
            Log("Package is not installed.", "ERROR");
            Console.WriteLine("Package is not installed.");
        }
    }

    public void Reset()
    {
        if (Directory.Exists(INSTALLED_FOLDER))
        {
            try
            {
                Directory.Delete(INSTALLED_FOLDER, true);
                Log("All packages uninstalled.");
                Console.WriteLine("All packages uninstalled.");
            }
            catch (Exception e)
            {
                Log($"Failed to reset package storage: {e.Message}", "ERROR");
                Console.WriteLine($"Failed to reset package storage: {e.Message}");
            }
        }
        else
        {
            Log("No packages installed.", "ERROR");
            Console.WriteLine("No packages installed.");
        }
    }

    private void Log(string message, string level = "INFO")
    {
        using (var logFile = new StreamWriter(LOG_FILE, true))
        {
            logFile.WriteLine($"{level}: {message}");
        }
    }

    private void CreateDirectory(string path)
    {
        if (!Directory.Exists(path))
        {
            Directory.CreateDirectory(path);
            Log($"Folder '{path}' created successfully.");
            Console.WriteLine($"Folder '{path}' created successfully.");
        }
        else
        {
            Log($"Folder '{path}' already exists.");
            Console.WriteLine($"Folder '{path}' already exists.");
        }
    }
}

public class PackageInfo
{
    private Dictionary<string, string> info;

    public PackageInfo(Dictionary<string, string> info)
    {
        this.info = info;
    }

    public string this[string key]
    {
        get => info.ContainsKey(key) ? info[key] : null;
    }

    public Dictionary<string, string> ToDictionary()
    {
        return new Dictionary<string, string>(info);
    }
}
