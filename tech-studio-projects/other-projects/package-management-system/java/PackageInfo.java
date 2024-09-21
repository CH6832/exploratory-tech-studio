import java.util.Map;

public class PackageInfo {
    private Map<String, String> info;

    public PackageInfo(Map<String, String> info) {
        this.info = info;
    }

    public String get(String key) {
        return info.get(key);
    }

    public Map<String, String> toMap() {
        return info;
    }
}
