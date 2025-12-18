// Package
package dk.project.Scanner;

public class URLData {

    // Attributes
    private final String fullUrl;
    private final String domainOnly;
    private final String domainName;
    private final String domainEnding;
    private final String prefix;

    // _______________________________________________________________

    public URLData(String fullUrl) {
        this.fullUrl = fullUrl;
        this.prefix = domainPrefix(fullUrl);
        this.domainOnly = domainOnlyMethod(fullUrl);
        this.domainEnding = domainEndingMethod(domainOnly);
        this.domainName = domainNameMethod(domainOnly);
    }

    // _______________________________________________________________

    private String domainOnlyMethod(String url) {
        url = url.toLowerCase();
        url = removeProtocol(url);
        if (url.contains("/")) {
            url = url.substring(0, url.indexOf("/"));
        }
        return url;
    }

    // _______________________________________________________________

    private String removeProtocol(String url) {

        String prefix = "";

        if (url.startsWith("http://")) {
            url = url.substring(7);
        } else if (url.startsWith("https://")) {
            url = url.substring(8);
        }

        return url;

    }

    // _______________________________________________________________

    private String domainPrefix(String url) {
        if (url.startsWith("http://")) return "http://";
        if (url.startsWith("https://")) return "https://";
        return "unknown";
    }

    // _______________________________________________________________

    private String domainEndingMethod(String domain) {
        String[] parts = domain.split("\\.");
        return parts[parts.length - 1];
    }

    // _______________________________________________________________

    private String domainNameMethod(String domain) {
        String[] parts = domain.split("\\.");
        if (parts.length >= 2) {
            return parts[parts.length - 2] + "." + parts[parts.length - 1];
        } else {
            return domain;
        }
    }

    // _______________________________________________________________

    public String getFullUrl() {
        return fullUrl;
    }

    // _______________________________________________________________

    public String getDomainOnly() {
        return domainOnly;
    }

    // _______________________________________________________________

    public String getDomainEnding() {
        return domainEnding;
    }

    // _______________________________________________________________

    public String getDomainName() {
        return domainName;
    }

    // _______________________________________________________________

    public String getPrefix() {
        return prefix;
    }

}