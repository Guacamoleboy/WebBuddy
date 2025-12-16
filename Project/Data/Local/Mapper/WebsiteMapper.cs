// Imports
using System.Collections.Generic;
using Microsoft.Data.Sqlite;
using Project.Models;

namespace Project.Data.Local.Mapper {
    
    public class WebsiteMapper {
        
        // Attributes
        private readonly string Connection;

        // ____________________________________________________
        
        public WebsiteMapper(string connection) {
            Connection = connection;
        }
        
        // ____________________________________________________
        
        public List<Website> GetAllWebsites() {
            
            // List of websites
            var websites = new List<Website>();

            // Connection
            using var connection = new SqliteConnection(Connection);
            connection.Open();
            
            // Query
            var command = connection.CreateCommand();
            command.CommandText = @"
                SELECT id, domain, isSafe, confidence, reason, category, validated, lastValidated
                FROM Website";
            
            // ResultSet (rs) in Java
            using var reader = command.ExecuteReader();
            while (reader.Read()) {
                var website = new Website(
                    domain: reader.GetString(1),
                    isSafe: reader.GetInt32(2) != 0,
                    confidence: reader.IsDBNull(3) ? null : reader.GetInt32(3),
                    reason: reader.IsDBNull(4) ? null : reader.GetString(4),
                    category: reader.IsDBNull(5) ? null : reader.GetInt32(5),
                    validated: reader.GetString(6),
                    lastValidated: reader.GetString(7)
                ){
                    Id = reader.GetInt32(0)
                };
                
                // Adds websites to ArrayList (list in C#)
                websites.Add(website);
            }

            return websites;
        }

        // ____________________________________________________
        
        public void InsertWebsite(Website website) {
            
            // Connection
            using var connection = new SqliteConnection(Connection);
            connection.Open();

            // @ -> Multi Line String (SQLite)
            var command = connection.CreateCommand();
            command.CommandText = @"
                INSERT INTO Website (domain, isSafe, confidence, reason, category, validated, lastValidated) VALUES 
                ($domain, $isSafe, $confidence, $reason, $category, $validated, $lastValidated)";
            
            command.Parameters.AddWithValue("$domain", website.Domain);
            command.Parameters.AddWithValue("$isSafe", website.IsSafe ? 1 : 0);
            command.Parameters.AddWithValue("$confidence", website.Confidence ?? (object)DBNull.Value);
            command.Parameters.AddWithValue("$reason", website.Reason ?? (object)DBNull.Value);
            command.Parameters.AddWithValue("$category", website.Category ?? (object)DBNull.Value);
            command.Parameters.AddWithValue("$validated", website.Validated);
            command.Parameters.AddWithValue("$lastValidated", website.LastValidated);
            
            // Execute Command
            command.ExecuteNonQuery();
            
        }
        
        // ____________________________________________________
        
        public Website? GetWebsiteByDomain(string domain) { // ? allows it to be nulled
            using var connection = new SqliteConnection(Connection);
            connection.Open();

            var command = connection.CreateCommand();
            command.CommandText = @"
                        SELECT id, domain, isSafe, confidence, reason, category, validated, lastValidated
                        FROM Website
                        WHERE LOWER(domain) = LOWER($domain)
                        LIMIT 1";

            command.Parameters.AddWithValue("$domain", domain);
            
            using var reader = command.ExecuteReader();
            if (reader.Read()) {
                return new Website(
                    domain: reader.GetString(1),
                    isSafe: reader.GetInt32(2) != 0,
                    confidence: reader.IsDBNull(3) ? null : reader.GetInt32(3),
                    reason: reader.IsDBNull(4) ? null : reader.GetString(4),
                    category: reader.IsDBNull(5) ? null : reader.GetInt32(5),
                    validated: reader.GetString(6),
                    lastValidated: reader.GetString(7)
                ){
                    Id = reader.GetInt32(0)
                };
            }

            return null;
            
        }
        
    }
}