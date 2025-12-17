// Package
package dk.project.mapper;

// Imports
import dk.project.db.Database;
import dk.project.entity.ScanRequest;
import dk.project.exception.DatabaseException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ScanRequestMapper {

    // Attributes

    // _________________________________________________

    public void newScanRequest(ScanRequest request) throws DatabaseException {
        String sql = "INSERT INTO scan_request (domain, requested_at, status, source) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, request.getDomain());
            stmt.setTimestamp(2, Timestamp.valueOf(request.getRequestedAt()));
            stmt.setString(3, request.getStatus());
            stmt.setString(4, request.getSource());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    request.setId(keys.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error creating ScanRequest", e);
        }
    }

    // _________________________________________________

    public ScanRequest getById(int id) throws DatabaseException {
        String sql = "SELECT * FROM scan_request WHERE id = ?";
        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return toScanRequest(rs);
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching ScanRequest by ID", e);
        }
    }

    // _________________________________________________

    public List<ScanRequest> getAll() throws DatabaseException {
        String sql = "SELECT * FROM scan_request ORDER BY id";
        List<ScanRequest> list = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(toScanRequest(rs));
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching all ScanRequests", e);
        }
        return list;
    }

    // _________________________________________________

    private ScanRequest toScanRequest(ResultSet rs) throws SQLException {
        return new ScanRequest(
                rs.getInt("id"),
                rs.getString("domain"),
                rs.getTimestamp("requested_at").toLocalDateTime(),
                rs.getString("status"),
                rs.getString("source")
        );
    }

}