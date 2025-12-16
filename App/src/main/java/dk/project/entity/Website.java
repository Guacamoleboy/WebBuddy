package dk.project.entity;

import java.time.LocalDateTime;

public class Website {

    // Attributes
    private int id;
    private String domain;
    private boolean isSafe;
    private int confidence;
    private String reason;
    private int category;
    private LocalDateTime validated;
    private LocalDateTime lastValidated;

    // ________________________________________________________________

    public Website(){} // Unit Tests

    // ________________________________________________________________

    public Website(int id, String domain, boolean isSafe, int confidence, String reason,
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

    public int getConfidence() {
        return confidence;
    }

    // ________________________________________________________________

    public void setConfidence(int confidence) {
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

    public int getCategory() {
        return category;
    }

    // ________________________________________________________________

    public void setCategory(int category) {
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