// Package
package dk.project.entity;

// Imports
import java.time.LocalDateTime;

public class ScanRequest {

    // Attributes
    private int id;
    private String domain;
    private LocalDateTime requestedAt;
    private String status;
    private String source;

    // ______________________________________________________

    public ScanRequest(){}

    // ______________________________________________________

    public ScanRequest(String domain, LocalDateTime requestedAt, String status, String source) {
        this.id = id;
        this.domain = domain;
        this.requestedAt = requestedAt;
        this.status = status;
        this.source = source;
    }

    // ______________________________________________________

    public ScanRequest(int id, String domain, LocalDateTime requestedAt, String status, String source) {
        this.id = id;
        this.domain = domain;
        this.requestedAt = requestedAt;
        this.status = status;
        this.source = source;
    }

    // ______________________________________________________

    public int getId() {
        return id;
    }

    // ______________________________________________________

    public void setId(int id) {
        this.id = id;
    }

    // ______________________________________________________

    public String getDomain() {
        return domain;
    }

    // ______________________________________________________

    public void setDomain(String domain) {
        this.domain = domain;
    }

    // ______________________________________________________

    public LocalDateTime getRequestedAt() {
        return requestedAt;
    }

    // ______________________________________________________

    public void setRequestedAt(LocalDateTime requestedAt) {
        this.requestedAt = requestedAt;
    }

    // ______________________________________________________

    public String getStatus() {
        return status;
    }

    // ______________________________________________________

    public void setStatus(String status) {
        this.status = status;
    }

    // ______________________________________________________

    public String getSource() {
        return source;
    }

    // ______________________________________________________

    public void setSource(String source) {
        this.source = source;
    }

}