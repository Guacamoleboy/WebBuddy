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