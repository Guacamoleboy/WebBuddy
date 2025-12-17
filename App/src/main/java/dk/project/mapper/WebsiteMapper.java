// Package
package dk.project.mapper;

// Imports
import dk.project.entity.Website;
import dk.project.db.Database;
import dk.project.exception.DatabaseException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WebsiteMapper {

    // Attributes

    // _____________________________________________________________________

    public void newWebsite(Website website) throws DatabaseException {
        String sql = "INSERT INTO website (domain, is_safe, confidence, reason, category_id, validated, last_validated) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, website.getDomain());
            stmt.setBoolean(2, website.isSafe());
            stmt.setObject(3, website.getConfidence() != null ? website.getConfidence() : null, Types.INTEGER);
            stmt.setString(4, website.getReason());
            stmt.setObject(5, website.getCategory() != null ? website.getCategory() : null, Types.INTEGER);
            stmt.setTimestamp(6, Timestamp.valueOf(website.getValidated()));
            stmt.setTimestamp(7, Timestamp.valueOf(website.getLastValidated()));

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    website.setId(keys.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error creating website", e);
        }
    }

    // _____________________________________________________________________

    public Website getById(int id) throws DatabaseException {
        String sql = "SELECT * FROM website WHERE id = ?";
        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return toWebsite(rs);
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching website by ID", e);
        }
    }

    // _____________________________________________________________________

    public Website getWebsiteByDomain(String domain) throws DatabaseException {
        String sql = "SELECT * FROM website WHERE LOWER(domain) = ?";
        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, domain.toLowerCase());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return toWebsite(rs);
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching website by domain", e);
        }
    }

    // _____________________________________________________________________

    public List<Website> getAll() throws DatabaseException {
        String sql = "SELECT * FROM website ORDER BY id";
        List<Website> websites = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                websites.add(toWebsite(rs));
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching all websites", e);
        }

        return websites;
    }

    // _____________________________________________________________________

    public void updateWebsite(Website website) throws DatabaseException {
        String sql = "UPDATE website SET domain = ?, is_safe = ?, confidence = ?, reason = ?, category_id = ?, validated = ?, last_validated = ? WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, website.getDomain());
            stmt.setBoolean(2, website.isSafe());
            stmt.setObject(3, website.getConfidence() != null ? website.getConfidence() : null, Types.INTEGER);
            stmt.setString(4, website.getReason());
            stmt.setObject(5, website.getCategory() != null ? website.getCategory() : null, Types.INTEGER);
            stmt.setTimestamp(6, Timestamp.valueOf(website.getValidated()));
            stmt.setTimestamp(7, Timestamp.valueOf(website.getLastValidated()));
            stmt.setInt(8, website.getId());

            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new DatabaseException("Website not found with ID: " + website.getId());
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error updating website", e);
        }
    }

    // _____________________________________________________________________

    public void deleteWebsite(int id) throws DatabaseException {
        String sql = "DELETE FROM website WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new DatabaseException("Website not found with ID: " + id);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error deleting website", e);
        }
    }

    // _____________________________________________________________________

    public void deleteWebsite(Website website) throws DatabaseException {
        deleteWebsite(website.getId());
    }

    // _____________________________________________________________________

    public Website toWebsite(ResultSet rs) throws SQLException {
        return new Website(
                rs.getInt("id"),
                rs.getString("domain"),
                rs.getBoolean("is_safe"),
                rs.getObject("confidence", Integer.class),
                rs.getString("reason"),
                rs.getObject("category", Integer.class),
                rs.getTimestamp("validated").toLocalDateTime(),
                rs.getTimestamp("last_validated").toLocalDateTime()
        );
    }
}