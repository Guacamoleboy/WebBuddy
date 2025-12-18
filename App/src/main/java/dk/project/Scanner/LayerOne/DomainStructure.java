// Package
package dk.project.Scanner.LayerOne;

// Imports
import dk.project.Scanner.ScannerScore;
import java.util.ArrayList;
import java.util.List;

public class DomainStructure {

    // Attributes

    // _____________________________________________________________

    public ScannerScore scan(String domain, String url, String name) {

        // Number of steps (Dynamic setup)
        List<Integer> stepScores = new ArrayList<>();

        stepScores.add(checkLength(name));
        stepScores.add(checkSubdomains(domain, name));
        stepScores.add(checkHyphens(domain));
        stepScores.add(checkNumbers(domain));
        stepScores.add(checkNonLetters(domain));
        stepScores.add(checkSubdomainSize(domain, name));
        stepScores.add(checkSubdomainLength(domain, name));
        stepScores.add(checkQueryParams(url));
        stepScores.add(checkDirectoryDepth(url));
        stepScores.add(checkSequentialRepeatedCharacters(domain));
        stepScores.add(checkUrlShortener(domain));

        // If all steps worked out
        int total = 0;

        // Add score
        for (int score : stepScores) {
            total += score;
        }

        // Average from size of ArrayList
        int averageConfidence = total / stepScores.size();

        // Safety
        if (averageConfidence > 100) averageConfidence = 100;

        return new ScannerScore(averageConfidence);

    }

    // ________________________________________________________________
    // urlstructur.dk

    private int checkLength(String name) {
        int length = name.length();
        if (length < 50 && length > 30) return 60;                              // Ultra weird
        if (length < 30 && length > 15) return 20;                              // Slightly strange
        if (length < 15) return 0;                                              // Normal URL
        return 0;
    }

    // ________________________________________________________________
    // fog.guacamoleboy.dk

    private int checkSubdomains(String domainOnly, String domainName) {

        String subdomainPart = domainOnly.replace("." + domainName, "");
        String[] subdomains = subdomainPart.isEmpty() ? new String[0] : subdomainPart.split("\\.");

        if (subdomains.length == 0) return 0;                                   // Normal
        if (subdomains.length == 1) return 10;                                  // Normal but still weird
        if (subdomains.length == 2) return 40;                                  // Strange
        if (subdomains.length == 3) return 60;                                  // Weird ahh
        if (subdomains.length <= 4) return 80;                                  // Uh... FBI?
        return 100;                                                             // WTF

    }

    // ________________________________________________________________
    // bog-4-ide-lol.dk

    private int checkHyphens(String domain) {
        int count = 0;

        for (int i = 0; i < domain.length(); i++) {                             // Number of "-" in the domain
            if (domain.charAt(i) == '-') count++;
        }

        if (count < 1) return 0;                                                // Avg website
        if (count < 2 && count > 1) return 20;                                  // Normal but unusual still
        if (count < 3 && count > 2) return 50;                                  // Uhh..
        if (count > 3) return 100;                                              // Fucking weird
        return 0;

    }

    // ________________________________________________________________
    // bog4551ide.dk

    private int checkNumbers(String domain) {
        int count = 0;
        for (int i = 0; i < domain.length(); i++) {                             // Checks for numbers over domain.length
            if (Character.isDigit(domain.charAt(i))) count++;
        }
        if (count < 1) return 0;                                                // Avg website
        if (count > 1 && count < 2) return 40;                                  // Weird
        if (count > 2) return 100;                                              // Y'all buggin'
        return 0;
    }

    // ________________________________________________________________
    // bog_4_ide!.dk

    private int checkNonLetters(String domain) {
        int count = 0;

        for (int i = 0; i < domain.length(); i++) {
            char c = domain.charAt(i);
            if (!Character.isLetter(c) && !Character.isDigit(c) && c != '.' && c != '-') {
                count++;
            }
        }

        if (count > 0) return 100;

        return 0;
    }

    // ________________________________________________________________
    // fog.guacamoleboy.dk/test/test/test/test

    private int checkDirectoryDepth(String url) {

        // Initial URL Setup
        url = url.replaceFirst("^https?://", "");

        // Remove query params
        url = url.split("\\?")[0];
        url = url.split("#")[0];

        // Find first "/"
        int firstSlash = url.indexOf("/");
        if (firstSlash == -1) {
            return 0;
        }

        // Path
        String path = url.substring(firstSlash + 1);

        if (path.isEmpty()) return 0;

        // Split path
        String[] parts = path.split("/");

        int depth = 0;
        for (String part : parts) {
            if (!part.isEmpty()) depth++;
        }

        // Scoring
        if (depth == 0) return 0;
        if (depth == 1) return 5;
        if (depth == 2) return 10;
        if (depth == 3) return 25;
        if (depth == 4) return 50;
        if (depth <= 6) return 75;
        return 100;

    }

    // ________________________________________________________________
    // Checks if the domain is a known URL shortener (bit.ly, tinyurl.com, etc.)

    private int checkUrlShortener(String domain) {

        // Lowercase
        String d = domain.toLowerCase();

        // List of known & approved shorteners
        String[] shorteners = {
                "bit.ly",
                "tinyurl.com",
                "t.co",
                "goo.gl",
                "ow.ly",
                "buff.ly",
                "is.gd",
                "adf.ly",
                "cutt.ly",
                "shorte.st",
                "tr.im",
                "cli.gs",
                "soo.gd",
                "s.id",
                "mcaf.ee",
                "budurl.com",
                "lnkd.in",
                "rb.gy",
                "bl.ink",
                "po.st",
                "qr.ae",
                "v.gd",
                "tiny.cc"
        };

        for (String s : shorteners) {
            if (d.equals(s)) {
                return 100;
            }
        }

        return 0;
    }

    // ________________________________________________________________
    // aaaaaapple.com, bbbbanana123.dk

    private int checkSequentialRepeatedCharacters(String domain) {

        int maxRepeat = 1;
        int currentRepeat = 1;

        for (int i = 1; i < domain.length(); i++) {
            char prev = domain.charAt(i - 1);
            char curr = domain.charAt(i);
            if (prev == curr && Character.isLetterOrDigit(curr)) {
                currentRepeat++;
                if (currentRepeat > maxRepeat) {
                    maxRepeat = currentRepeat;
                }
            } else {
                currentRepeat = 1;
            }
        }

        // Score
        if (maxRepeat > 10) return 100;
        if (maxRepeat > 7) return 60;
        if (maxRepeat > 5) return 40;
        if (maxRepeat > 3) return 20;
        if (maxRepeat > 1) return 10;

        return 0;

    }

    // ________________________________________________________________
    // fog.fog.domain.dk

    private int checkSubdomainSize(String domainOnly, String domainName) {

        String subdomainPart = domainOnly.replace("." + domainName, "");
        String[] subdomains = subdomainPart.isEmpty() ? new String[0] : subdomainPart.split("\\.");

        if (subdomains.length > 1) return 50;                                   // Strange!
        if (subdomains.length > 2) return 100;                                  // You need help..
        return 0; // Normal
    }

    // ________________________________________________________________
    // longsubdomainname.domain.dk

    private int checkSubdomainLength(String domainOnly, String domainName) {

        String subdomainPart = domainOnly.replace("." + domainName, "");
        String[] subdomains = subdomainPart.isEmpty() ? new String[0] : subdomainPart.split("\\.");

        int confidence = 0;

        for (String sub : subdomains) {
            if (sub.length() > 20) confidence = Math.max(confidence, 60);
            else if (sub.length() > 15) confidence = Math.max(confidence, 40);
            else if (sub.length() > 10) confidence = Math.max(confidence, 20);
            else if (sub.length() > 5) confidence = Math.max(confidence, 10);
        }

        return confidence;
    }

    // ________________________________________________________________
    // url.dk?search=term&other=123

    private int checkQueryParams(String url) {

        if (url.contains("?")) {

            // Entire URL query
            String query = url.substring(url.indexOf("?") + 1);

            // Split on & to get each param into Array
            String[] params = query.split("&");

            switch (params.length) {
                case 1: return 10;                                              // Normal but still odd
                case 2: return 20;                                              // Unusual but normal
                case 3: return 40;                                              // Weird
                case 4: return 60;                                              // Wtf? Selling your soul fr.
                default: return 100;                                            // Over 3
            }

        }

        // No params
        return 0;

    }

}