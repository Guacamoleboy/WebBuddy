// Package
package dk.project.Scanner;

// Imports
import java.util.List;

public class ScannerCalculator {

    // Attributes

    // ______________________________________________________________________________

    public static int calculateTotalConfidence(List<ScannerScore> scores) {

        // No scores found
        if (scores == null || scores.isEmpty()) return 0;

        // Initial
        int total = 0;

        // Loop through scores for List scores
        for (ScannerScore score : scores) {
            total += score.getConfidence();
        }

        // Average depending on scores size (total)
        int average = total / scores.size();

        // Limit to 100
        if (average > 100) average = 100;

        // Return the value
        return average;

    }

}