// Package
package dk.project.Scanner.LayerOne;

// Imports
import dk.project.Scanner.ScannerScore;
import dk.project.entity.EndingRisk;
import dk.project.mapper.EndingRiskMapper;
import dk.project.exception.DatabaseException;
import java.util.List;

public class DomainEnding {

    // Attributes
    private EndingRiskMapper endingRiskMapper = new EndingRiskMapper();

    // _____________________________________________________________

    public ScannerScore scan(String ending) {

        /* System.out.println(ending); // DEBUG */

        // Ending Risk DB data
        List<EndingRisk> allRisks;
        try {
            allRisks = endingRiskMapper.getAll();
        } catch (DatabaseException e) {
            return new ScannerScore(100);
        }

        // For-each loop over allRisks (DB Ending) to see if there's a match and then sets the score 1-5
        EndingRisk match = null;
        for (EndingRisk endingRisk : allRisks) {
            if (endingRisk.getEnding().equalsIgnoreCase(ending)) {
                match = endingRisk;
                break;
            }
        }

        // Unsafe if not found in DB or score is 5
        if (match == null || match.getRisk() == 5) {
            return new ScannerScore(100);
        }

        // Trusted website endings
        if (match.getRisk() == 1) {
            return new ScannerScore(0);
        }

        // Match 2-4 * 20 (To get between 20-100)
        int confidence = match.getRisk() * 20;

        // Return confidence from this step
        return new ScannerScore(confidence);

    }

}