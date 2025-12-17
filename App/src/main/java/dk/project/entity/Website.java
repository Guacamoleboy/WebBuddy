// Package
package dk.project.entity;

// Imports
import java.time.LocalDateTime;

public class Website {

    // Attributes
    private int id;
    private String domain;
    private boolean isSafe;
    private Integer confidence;
    private String reason;
    private Integer category;
    private LocalDateTime validated;
    private LocalDateTime lastValidated;

    // ________________________________________________________________
    // Unit Tests

    public Website(){}

    // ________________________________________________________________

    public Website(String domain, boolean isSafe, Integer  confidence, String reason,
    int category, LocalDateTime validated, LocalDateTime lastValidated) {
        this.domain = domain;
        this.isSafe = isSafe;
        this.confidence = confidence;
        this.reason = reason;
        this.category = category;
        this.validated = validated;
        this.lastValidated = lastValidated;
    }

    // ________________________________________________________________

    public Website(int id, String domain, boolean isSafe, Integer  confidence, String reason,
    int category, LocalDateTime validated, LocalDateTime lastValidated) {
        this.id = id;
        this.domain = domain;
        this.isSafe = isSafe;
        this.confidence = confidence;
        this.reason = reason;
        this.category = category;
        this.validated = validated;
        this.lastValidated = lastValidated;
    }

    // ________________________________________________________________

    public String getDomain() {
        return domain;
    }

    // ________________________________________________________________

    public void setDomain(String domain) {
        this.domain = domain;
    }

    // ________________________________________________________________

    public int getId() {
        return id;
    }

    // ________________________________________________________________

    public void setId(int id) {
        this.id = id;
    }

    // ________________________________________________________________

    public boolean isSafe() {
        return isSafe;
    }

    // ________________________________________________________________

    public void setSafe(boolean safe) {
        isSafe = safe;
    }

    // ________________________________________________________________

    public Integer getConfidence() {
        return confidence;
    }

    // ________________________________________________________________

    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
    }

    // ________________________________________________________________

    public String getReason() {
        return reason;
    }

    // ________________________________________________________________

    public void setReason(String reason) {
        this.reason = reason;
    }

    // ________________________________________________________________

    public Integer  getCategory() {
        return category;
    }

    // ________________________________________________________________

    public void setCategory(Integer  category) {
        this.category = category;
    }

    // ________________________________________________________________

    public LocalDateTime getValidated() {
        return validated;
    }

    // ________________________________________________________________

    public void setValidated(LocalDateTime validated) {
        this.validated = validated;
    }

    // ________________________________________________________________

    public LocalDateTime getLastValidated() {
        return lastValidated;
    }

    // ________________________________________________________________

    public void setLastValidated(LocalDateTime lastValidated) {
        this.lastValidated = lastValidated;
    }

}