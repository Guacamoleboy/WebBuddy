// Imports
using Project.Data.Local.Mapper;

// Package
namespace Project.Services;

public class ValidationService {
    
    // Attributes
    private readonly WebsiteMapper websiteMapper;

    // ________________________________________________________
    
    public ValidationService(WebsiteMapper websiteMapper) {
        this.websiteMapper = websiteMapper;
    }
    
    // ________________________________________________________

    public bool IsWebsiteBlocked(string domain) {
        
        // Check from method
        var site = this.websiteMapper.GetWebsiteByDomain(domain);

        // If no site found
        if (site == null)
            return false; 

        // Return true if site is unsafe
        return !site.IsSafe;
        
    }
    
}