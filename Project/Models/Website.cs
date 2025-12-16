/*
 
    C# uses PascalCase instead of camelCase on property fields such as DB fields.. Don't ask me why.
    No getter or setter either. It simply uses get; and set;.
    
    Crazy.. 
 
*/

// Package
namespace Project.Models;

public class Website {
    
    // Attributes
    public int Id { get; set; }                                             // INTEGER PRIMARY KEY AUTOINCREMENT
    public string Domain { get; set; } = null!;                             // TEXT NOT NULL
    public bool IsSafe { get; set; }                                        // INTEGER NOT NULL
    public int? Confidence { get; set; }                                    // INTEGER 
    public string? Reason { get; set; }                                     // TEXT 
    public int? Category { get; set; }                                      // INTEGER 
    public string Validated { get; set; } = null!;                          // TEXT NOT NULL
    public string LastValidated { get; set; } = null!;                      // TEXT NOT NULL
    
    // _________________________________________________________

    public Website(string domain, bool isSafe, int? confidence, string reason, int? category, string validated, string lastValidated) {
        Domain = domain;
        IsSafe = isSafe;
        Confidence = confidence;
        Reason = reason;
        Category = category;
        Validated = validated;
        LastValidated = lastValidated;
    }
    
}