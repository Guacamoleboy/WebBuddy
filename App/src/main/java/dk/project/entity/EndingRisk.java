// Package
package dk.project.entity;

public class EndingRisk {

    // Attributes
    private int id;
    private String ending;
    private int risk;

    // _______________________________________

    public EndingRisk(){}

    // _______________________________________

    public EndingRisk(int id, String ending, int risk) {
        this.id = id;
        this.ending = ending;
        this.risk = risk;
    }

    // _______________________________________

    public EndingRisk(String ending, int risk) {
        this.id = id;
        this.ending = ending;
        this.risk = risk;
    }

    // _______________________________________

    public int getId() {
        return id;
    }

    // _______________________________________

    public void setId(int id) {
        this.id = id;
    }

    // _______________________________________

    public String getEnding() {
        return ending;
    }

    // _______________________________________

    public void setEnding(String ending) {
        this.ending = ending;
    }

    // _______________________________________

    public int getRisk() {
        return risk;
    }

    // _______________________________________

    public void setRisk(int risk) {
        this.risk = risk;
    }

}