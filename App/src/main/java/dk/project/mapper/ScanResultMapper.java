// Package
package dk.project.mapper;

// Imports
import dk.project.db.Database;
import dk.project.entity.ScanRequest;
import dk.project.entity.ScanResult;
import dk.project.exception.DatabaseException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ScanResultMapper {

    // Attributes

    // ____________________________________________________________

    public void newScanResult(ScanResult result) throws DatabaseException {
        String sql = "INSERT INTO scan_result (scan_request_id, domain, is_safe, confidence, reason, category, scanned_at) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, result.getScanRequest().getId());
            stmt.setString(2, result.getDomain());
            stmt.setBoolean(3, result.isSafe());
            stmt.setObject(4, result.getConfidence() != null ? result.getConfidence() : null, Types.INTEGER);
            stmt.setString(5, result.getReason());
            stmt.setObject(6, result.getCategory() != null ? result.getCategory() : null, Types.INTEGER);
            stmt.setTimestamp(7, Timestamp.valueOf(result.getScannedAt()));

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    result.setId(keys.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error creating ScanResult", e);
        }
    }

    // ____________________________________________________________

    public ScanResult getById(int id) throws DatabaseException {
        String sql = "SELECT * FROM scan_result WHERE id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return toScanResult(rs);
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching ScanResult by ID", e);
        }
    }

    // ____________________________________________________________

    public List<ScanResult> getAll() throws DatabaseException {
        String sql = "SELECT * FROM scan_result ORDER BY id";
        List<ScanResult> list = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(toScanResult(rs));
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching all ScanResults", e);
        }
        return list;
    }

    // ____________________________________________________________

    private ScanResult toScanResult(ResultSet rs) throws SQLException {
        ScanRequest scanRequest = new ScanRequest();
        scanRequest.setId(rs.getInt("scan_request_id"));
        return new ScanResult(
                rs.getInt("id"),
                scanRequest,
                rs.getString("domain"),
                rs.getBoolean("is_safe"),
                rs.getObject("confidence", Integer.class),
                rs.getString("reason"),
                rs.getObject("category", Integer.class),
                rs.getTimestamp("scanned_at").toLocalDateTime()
        );
    }

}