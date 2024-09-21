using System.IO;
using Newtonsoft.Json;
using NUnit.Framework;

[TestFixture]
public class PkgManagerTests
{
    private const string PKG_JSON_PATH = "packages.json";
    private const string LOG_FILE = "log.txt";
    private PkgManager pkgManager;

    [SetUp]
    public void Setup()
    {
        // Clean up before each test
        if (File.Exists(PKG_JSON_PATH))
            File.Delete(PKG_JSON_PATH);
        
        if (File.Exists(LOG_FILE))
            File.Delete(LOG_FILE);
        
        pkgManager = new PkgManager();
    }

    [TearDown]
    public void TearDown()
    {
        // Clean up after each test
        if (File.Exists(PKG_JSON_PATH))
            File.Delete(PKG_JSON_PATH);
        
        if (File.Exists(LOG_FILE))
            File.Delete(LOG_FILE);
    }

    [Test]
    public void LoadPackages_EmptyFile_NoPackagesLoaded()
    {
        pkgManager = new PkgManager();
        Assert.IsTrue(pkgManager.PackageCount == 0);
    }

    [Test]
    public void SavePackages_AddPackages_PackagesSavedCorrectly()
    {
        var packageInfo = new PackageInfo(new Dictionary<string, string>
        {
            { "_pkgurl", "example.zip" },
            { "version", "1.0" }
        });
        
        pkgManager.AddPackage("example", packageInfo);
        pkgManager.SavePackages();

        var json = File.ReadAllText(PKG_JSON_PATH);
        var savedPackages = JsonConvert.DeserializeObject<Dictionary<string, Dictionary<string, string>>>(json);

        Assert.IsTrue(savedPackages.ContainsKey("example"));
        Assert.AreEqual("example.zip", savedPackages["example"]["_pkgurl"]);
        Assert.AreEqual("1.0", savedPackages["example"]["version"]);
    }

    [Test]
    public void Install_PackageNotFound_ErrorLogged()
    {
        pkgManager.Install("nonexistent");
        Assert.IsTrue(File.ReadAllText(LOG_FILE).Contains("Package nonexistent not found."));
    }

    [Test]
    public void Install_ValidPackage_PackageInstalled()
    {
        var packageInfo = new PackageInfo(new Dictionary<string, string>
        {
            { "_pkgurl", "example.zip" }
        });

        pkgManager.AddPackage("example", packageInfo);
        Directory.CreateDirectory("storage");
        File.WriteAllText(Path.Combine("storage", "example.zip"), "dummy data");

        pkgManager.Install("example");
        Assert.IsTrue(File.Exists(Path.Combine("installed", "example.zip")));
    }

    [Test]
    public void Uninstall_InstalledPackage_PackageRemoved()
    {
        var packageInfo = new PackageInfo(new Dictionary<string, string>
        {
            { "_pkgurl", "example.zip" }
        });

        pkgManager.AddPackage("example", packageInfo);
        Directory.CreateDirectory("storage");
        File.WriteAllText(Path.Combine("storage", "example.zip"), "dummy data");
        pkgManager.Install("example");

        pkgManager.Uninstall("example");
        Assert.IsFalse(File.Exists(Path.Combine("installed", "example.zip")));
    }

    [Test]
    public void Reset_InstalledPackages_AllPackagesRemoved()
    {
        var packageInfo = new PackageInfo(new Dictionary<string, string>
        {
            { "_pkgurl", "example.zip" }
        });

        pkgManager.AddPackage("example", packageInfo);
        Directory.CreateDirectory("storage");
        File.WriteAllText(Path.Combine("storage", "example.zip"), "dummy data");
        pkgManager.Install("example");

        pkgManager.Reset();
        Assert.IsFalse(Directory.Exists("installed"));
    }
}
