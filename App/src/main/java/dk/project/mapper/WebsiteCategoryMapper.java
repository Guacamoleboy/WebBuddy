// Package
package dk.project.mapper;

// Imports
import dk.project.entity.WebsiteCategory;
import dk.project.db.Database;
import dk.project.exception.DatabaseException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WebsiteCategoryMapper {

    // Attributes

    // __________________________________________________________________________________

    public void addCategoryToWebsite(int websiteId, int categoryId) throws DatabaseException {
        String sql = "INSERT INTO website_category (website_id, category_id) VALUES (?, ?)";
        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, websiteId);
            stmt.setInt(2, categoryId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error adding category to website", e);
        }
    }

    // __________________________________________________________________________________

    public void removeCategoryFromWebsite(int websiteId, int categoryId) throws DatabaseException {
        String sql = "DELETE FROM website_category WHERE website_id = ? AND category_id = ?";
        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, websiteId);
            stmt.setInt(2, categoryId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseException("Error removing category from website", e);
        }
    }

    // __________________________________________________________________________________

    public List<Integer> getCategoriesByWebsiteId(int websiteId) throws DatabaseException {
        String sql = "SELECT category_id FROM website_category WHERE website_id = ?";
        List<Integer> categoryIds = new ArrayList<>();
        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, websiteId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    categoryIds.add(rs.getInt("category_id"));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching categories for website", e);
        }
        return categoryIds;
    }

}