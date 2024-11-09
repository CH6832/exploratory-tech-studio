package com.system.browser;

// Arrays for list manipulation.
import java.util.Arrays;
// List for using collection.
import java.util.List;

public class URLAutoComplete {

    // List of common domain extensions that can be appended to URLs.
    private static final List<String> DOMAIN_EXTENSIONS = Arrays.asList(
            ".com", ".org", ".net", ".edu", ".gov", ".co", ".io", ".info", ".biz", ".tv", ".me"
            // Add more extensions as needed
    );

    /**
     * Suggests a completed URL based on the given partial URL.
     * It ensures the URL has a protocol, adds "www." if missing, and appends a domain extension if needed.
     *
     * @param partialUrl The partially entered URL by the user.
     * @return A suggested complete URL.
     */
    public static String suggestCompletion(String partialUrl) {
        // Check if the partial URL starts with "https://"
        if (!partialUrl.startsWith("https://")) {
            // Suggest completing with "https://" prefix
            partialUrl = "https://" + partialUrl;
        }

        // Check if the partial URL contains "www."
        if (!partialUrl.contains("www.")) {
            // Suggest completing with "www." prefix
            partialUrl = addWwwPrefix(partialUrl);
        }

        // Check if the partial URL ends with a domain extension
        boolean hasExtension = false;
        for (String extension : DOMAIN_EXTENSIONS) {
            if (partialUrl.endsWith(extension)) {
                hasExtension = true;
                break;
            }
        }

        // If not, suggest completing with the first domain extension
        if (!hasExtension && !DOMAIN_EXTENSIONS.isEmpty()) {
            String firstExtension = DOMAIN_EXTENSIONS.getFirst();
            if (!partialUrl.endsWith(firstExtension)) {
                partialUrl += firstExtension;
            }
        }

        // Return the completed URL
        return partialUrl;
    }

    /**
     * Adds the "www." prefix to a given URL if it is not already present.
     *
     * @param url The URL to which "www." may be added.
     * @return The URL with "www." prefix added.
     */
    static String addWwwPrefix(String url) {
        // Split the URL into protocol and the rest of the URL
        int protocolEndIndex = url.indexOf("://") + 3;
        String protocol = url.substring(0, protocolEndIndex);
        String restOfUrl = url.substring(protocolEndIndex);

        // Check if "www." is already present
        if (!restOfUrl.startsWith("www.")) {
            restOfUrl = "www." + restOfUrl;
        }

        return protocol + restOfUrl;
    }
}

