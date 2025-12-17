// Package
package dk.project.mapper;

// Imports
import dk.project.db.Database;
import dk.project.entity.EndingRisk;
import dk.project.exception.DatabaseException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EndingRiskMapper {

    // Attributes

    // _________________________________________________________________

    public void newEndingRisk(EndingRisk risk) throws DatabaseException {
        String sql = "INSERT INTO ending_risk (ending, risk) VALUES (?, ?)";
        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, risk.getEnding());
            stmt.setInt(2, risk.getRisk());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    risk.setId(keys.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error creating EndingRisk", e);
        }
    }

    // _________________________________________________________________

    public EndingRisk getById(int id) throws DatabaseException {
        String sql = "SELECT * FROM ending_risk WHERE id = ?";
        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return toEndingRisk(rs);
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching EndingRisk by ID", e);
        }
    }

    // _________________________________________________________________

    public List<EndingRisk> getAll() throws DatabaseException {
        String sql = "SELECT * FROM ending_risk ORDER BY id";
        List<EndingRisk> list = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(toEndingRisk(rs));
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching all EndingRisk", e);
        }
        return list;
    }

    // _________________________________________________________________

    private EndingRisk toEndingRisk(ResultSet rs) throws SQLException {
        return new EndingRisk(
                rs.getInt("id"),
                rs.getString("ending"),
                rs.getInt("risk")
        );
    }

}