// Package
package dk.project.controller.Scan;

// Imports
import dk.project.Scanner.LayerOne.DomainEnding;
import dk.project.Scanner.LayerOne.DomainHttp;
import dk.project.Scanner.LayerOne.DomainStructure;
import dk.project.Scanner.ScannerCalculator;
import dk.project.Scanner.ScannerScore;
import dk.project.Scanner.URLData;
import io.javalin.Javalin;
import java.util.ArrayList;
import java.util.List;

public class ScanController {

    // Attributes
    private static final DomainHttp httpStep = new DomainHttp();
    private static final DomainEnding domainEndingStep = new DomainEnding();
    private static final DomainStructure domainStructureStep = new DomainStructure();

    // ______________________________________________________________________

    public static void registerRoutes(Javalin app) {

        app.get("/scan", ctx -> {

            // input
            String input = ctx.queryParam("scanner-domain");

            if (input == null || input.isEmpty()) {
                ctx.redirect("/?scanner=missingFields");
                return;
            }

            // URLData
            URLData urlData = new URLData(input);

            // Layers
            List<ScannerScore> scores = new ArrayList<>();
            scores.addAll(layerOne(urlData));
            // scores.addAll(layerTwo(urlData));
            // scores.addAll(layerThree(urlData));

            // Confidence calculations from ArrayList of scores
            int totalConfidence = ScannerCalculator.calculateTotalConfidence(scores);

            // DEBUG
            System.out.println("User input: " + input);
            System.out.println("Domain: " + urlData.getDomainOnly());
            System.out.println("Total Confidence: " + totalConfidence);

            ctx.status(200).result("Scan executed correctly");

        });

    }

    // ______________________________________________________________________

    private static List<ScannerScore> layerOne(URLData urlData) {

        // Initial ArrayList
        List<ScannerScore> scores = new ArrayList<>();

        // Sub Steps
        scores.add(domainEndingStep.scan(urlData.getDomainEnding()));
        scores.add(httpStep.scan(urlData.getPrefix()));
        scores.add(domainStructureStep.scan(urlData.getDomainOnly(), urlData.getFullUrl(), urlData.getDomainName()));

        // Return score
        return scores;

    }

    public static List<ScannerScore> layerTwo(URLData urlData){

        // Initial ArrayList
        List<ScannerScore> scores = new ArrayList<>();

        // Sub Steps


        // Return score
        return scores;

    }

}