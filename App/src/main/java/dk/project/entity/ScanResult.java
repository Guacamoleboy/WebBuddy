// Package
package dk.project.entity;

// Imports
import java.time.LocalDateTime;

public class ScanResult {

    // Attributes
    private int id;
    private ScanRequest scanRequest;
    private String domain;
    private boolean isSafe;
    private Integer confidence;
    private String reason;
    private Integer category;
    private LocalDateTime scannedAt;

    // _________________________________________________
    // UNIT TEST

    public ScanResult(){}

    // _________________________________________________

    public ScanResult(int id, ScanRequest scanRequest, String domain, boolean isSafe, Integer confidence, String reason, Integer category, LocalDateTime scannedAt) {
        this.id = id;
        this.scanRequest = scanRequest;
        this.domain = domain;
        this.isSafe = isSafe;
        this.confidence = confidence;
        this.reason = reason;
        this.category = category;
        this.scannedAt = scannedAt;
    }

    // _________________________________________________

    public ScanResult(ScanRequest scanRequest, String domain, boolean isSafe, Integer confidence, String reason, Integer category, LocalDateTime scannedAt) {
        this.scanRequest = scanRequest;
        this.domain = domain;
        this.isSafe = isSafe;
        this.confidence = confidence;
        this.reason = reason;
        this.category = category;
        this.scannedAt = scannedAt;
    }

    // _________________________________________________

    public int getId() {
        return id;
    }

    // _________________________________________________

    public void setId(int id) {
        this.id = id;
    }

    // _________________________________________________

    public ScanRequest getScanRequest() {
        return scanRequest;
    }

    // _________________________________________________

    public void setScanRequest(ScanRequest scanRequest) {
        this.scanRequest = scanRequest;
    }

    // _________________________________________________

    public String getDomain() {
        return domain;
    }

    // _________________________________________________

    public void setDomain(String domain) {
        this.domain = domain;
    }

    // _________________________________________________

    public boolean isSafe() {
        return isSafe;
    }

    // _________________________________________________

    public void setSafe(boolean safe) {
        isSafe = safe;
    }

    // _________________________________________________

    public Integer getConfidence() {
        return confidence;
    }

    // _________________________________________________

    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
    }

    // _________________________________________________

    public String getReason() {
        return reason;
    }

    // _________________________________________________

    public void setReason(String reason) {
        this.reason = reason;
    }

    // _________________________________________________

    public Integer getCategory() {
        return category;
    }

    // _________________________________________________

    public void setCategory(Integer category) {
        this.category = category;
    }

    // _________________________________________________

    public LocalDateTime getScannedAt() {
        return scannedAt;
    }

    // _________________________________________________

    public void setScannedAt(LocalDateTime scannedAt) {
        this.scannedAt = scannedAt;
    }

}