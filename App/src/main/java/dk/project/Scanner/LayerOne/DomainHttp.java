// Package
package dk.project.Scanner.LayerOne;

// Imports
import dk.project.Scanner.ScannerScore;

public class DomainHttp {

    // Attributes

    // _____________________________________________

    public ScannerScore scan(String prefix) {

        if (prefix.equals("https://")) {                                            // SSL
            return new ScannerScore(0);
        } else if (prefix.equals("http://")) {                                      // No SSL
            return new ScannerScore(100);
        } else if (prefix.equals("")) {                                             // Blank (2025 it's safe)
            return new ScannerScore(0);
        } else {                                                                    // Unknown
            return new ScannerScore(100);
        }

    }

}