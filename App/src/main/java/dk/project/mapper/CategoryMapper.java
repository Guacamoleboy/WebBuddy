// Package
package dk.project.mapper;

// Imports
import dk.project.entity.Category;
import dk.project.db.Database;
import dk.project.exception.DatabaseException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {

    // Attributes

    // _____________________________________________________________________

    public void newCategory(Category category) throws DatabaseException {
        String sql = "INSERT INTO category (name) VALUES (?)";

        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, category.getName());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    category.setId(keys.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error creating category", e);
        }
    }

    // _____________________________________________________________________

    public Category getById(int id) throws DatabaseException {
        String sql = "SELECT * FROM category WHERE id = ?";

        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return toCategory(rs);
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching category by ID", e);
        }
    }

    // _____________________________________________________________________

    public Category getByName(String name) throws DatabaseException {
        String sql = "SELECT * FROM category WHERE LOWER(name) = ?";

        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name.toLowerCase());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return toCategory(rs);
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching category by name", e);
        }
    }

    // _____________________________________________________________________

    public List<Category> getAll() throws DatabaseException {
        String sql = "SELECT * FROM category ORDER BY id";
        List<Category> categories = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                categories.add(toCategory(rs));
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching all categories", e);
        }

        return categories;
    }

    // _____________________________________________________________________

    public void updateCategory(Category category) throws DatabaseException {
        String sql = "UPDATE category SET name = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, category.getName());
            stmt.setInt(2, category.getId());

            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new DatabaseException("Category not found with ID: " + category.getId());
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error updating category", e);
        }
    }

    // _____________________________________________________________________

    public void deleteCategory(int id) throws DatabaseException {
        String sql = "DELETE FROM category WHERE id = ?";

        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new DatabaseException("Category not found with ID: " + id);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error deleting category", e);
        }
    }

    // _____________________________________________________________________

    public void deleteCategory(Category category) throws DatabaseException {
        deleteCategory(category.getId());
    }

    // _____________________________________________________________________

    public Category toCategory(ResultSet rs) throws SQLException {
        return new Category(
                rs.getInt("id"),
                rs.getString("name")
        );
    }

}