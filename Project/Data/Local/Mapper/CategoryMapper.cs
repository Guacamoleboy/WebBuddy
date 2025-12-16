// Imports
using System.Collections.Generic;
using Microsoft.Data.Sqlite;
using Project.Models;

namespace Project.Data.Local.Mapper {
    
    public class CategoryMapper {
        
        // Attributes
        private readonly string Connection;

        // ____________________________________________________
        
        public CategoryMapper(string connection) {
            Connection = connection;
        }

        // ____________________________________________________
        
        public List<Category> GetAllCategories() {
            
            // List of categories
            var categories = new List<Category>();
            
            // Connection
            using var connection = new SqliteConnection(Connection);
            connection.Open();

            // @ -> Multi Line String
            var command = connection.CreateCommand();
            command.CommandText = @"
                SELECT id, name
                FROM Category";
            
            // ResultSet (rs) in Java
            using var reader = command.ExecuteReader();
            
            // While Scanner has new line
            while (reader.Read()) {
                var category = new Category {
                    Id = reader.GetInt32(0),
                    Name = reader.GetString(1)
                };
                
                // Add
                categories.Add(category);
            }

            return categories;
            
        }
        
        // ____________________________________________________
        
        public void InsertCategory(Category category) {
            
            // Connection
            using var connection = new SqliteConnection(Connection);
            connection.Open();
            
            // Query
            var command = connection.CreateCommand();
            
            // @ -> Multi Line String
            command.CommandText = @"
                INSERT INTO Category (name) VALUES 
                ($name)";
            
            // Add $name -> category.Name
            command.Parameters.AddWithValue("$name", category.Name);
            
            // Execute Command
            command.ExecuteNonQuery();
            
        }
    }
}