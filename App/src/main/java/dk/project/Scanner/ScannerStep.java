// Package
package dk.project.Scanner;

public interface ScannerStep {

    // Attributes
    ScannerScore scan(String domain);
    String getName();

}