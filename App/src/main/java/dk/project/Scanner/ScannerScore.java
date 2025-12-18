// Package
package dk.project.Scanner;

public class ScannerScore {

    // Attributes
    private final int confidence;

    // _________________________________________________________________

    public ScannerScore(int confidence) {
        this.confidence = confidence;
    }

    // _________________________________________________________________

    public int getConfidence() {
        return confidence;
    }

}