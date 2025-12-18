// Package
package dk.project.controller.Scan;

// Imports
import dk.project.Scanner.LayerOne.DomainEnding;
import dk.project.Scanner.LayerOne.DomainHttp;
import dk.project.Scanner.LayerOne.DomainStructure;
import dk.project.Scanner.ScannerCalculator;
import dk.project.Scanner.ScannerScore;
import dk.project.Scanner.URLData;
import dk.project.server.ThymeleafSetup;
import io.javalin.Javalin;
import java.util.ArrayList;
import java.util.List;

public class ScanController {

    // Attributes
    private static final DomainHttp httpStep = new DomainHttp();
    private static final DomainEnding domainEndingStep = new DomainEnding();
    private static final DomainStructure domainStructureStep = new DomainStructure();

    // _______________________________________________

    public static void registerRoutes(Javalin app) {

        app.get("/scan", ctx -> {

            // User input
            String input = ctx.queryParam("scanner-domain");

            // Error Handle
            if (input == null || input.isEmpty()) {
                ctx.redirect("/?scanner=missingFields");
                return;
            }

            // URL Data from input
            URLData urlData = new URLData(input);

            // Steps being done and calculated
            ScannerScore endingScore = domainEndingStep.scan(urlData.getDomainEnding());
            ScannerScore httpScore = httpStep.scan(urlData.getPrefix());
            ScannerScore structureScore = domainStructureStep.scan(urlData.getDomainOnly(), urlData.getFullUrl(), urlData.getDomainName());

            // List of all scores
            List<ScannerScore> scores = new ArrayList<>();
            scores.add(endingScore);
            scores.add(httpScore);
            scores.add(structureScore);

            // Calculate totalConfidence
            int totalConfidence = ScannerCalculator.calculateTotalConfidence(scores);

            // DEBUG
            System.out.println("User input: " + input);
            System.out.println("Domain: " + urlData.getDomainOnly());
            System.out.println("Ending: " + urlData.getDomainEnding() + ", Ending score: " + endingScore.getConfidence());
            System.out.println("Prefix: " + urlData.getPrefix() + ", HTTP score: " + httpScore.getConfidence());
            System.out.println("Structure Score: " + structureScore.getConfidence());
            System.out.println("Total Confidence: " + totalConfidence);

            // Redirect will be added later. For now only backend.
            ctx.status(200).result("Scan executed correctly");

        });

    }

} // PageController end