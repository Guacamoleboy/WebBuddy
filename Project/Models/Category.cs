/*
 
    C# uses PascalCase instead of camelCase on property fields such as DB fields.. Don't ask me why.
    No getter or setter either. It simply uses get; and set;.
    
    Crazy.. 
 
*/

// Package
namespace Project.Models;

public class Category {
    
    // Attributes
    public int Id { get; set; }                                                 // INTEGER PRIMARY KEY AUTOINCREMENT
    public string Name { get; set; } = null!;                                   // TEXT NOT NULL UNIQUE
    
    // _________________________________________________________
    
    public Category(string name) {
        Name = name;
    }
    
    // _________________________________________________________
    
    public Category() { }
    
}