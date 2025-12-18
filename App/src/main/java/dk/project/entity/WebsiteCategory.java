package dk.project.entity;

public class WebsiteCategory {

    // Attributes
    private int websiteId;
    private int categoryId;

    // ___________________________________________________

    public WebsiteCategory() {}

    // ___________________________________________________

    public WebsiteCategory(int websiteId, int categoryId) {
        this.websiteId = websiteId;
        this.categoryId = categoryId;
    }

    // ___________________________________________________

    public int getCategoryId() {
        return categoryId;
    }

    // ___________________________________________________

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    // ___________________________________________________

    public int getWebsiteId() {
        return websiteId;
    }

    // ___________________________________________________

    public void setWebsiteId(int websiteId) {
        this.websiteId = websiteId;
    }

}