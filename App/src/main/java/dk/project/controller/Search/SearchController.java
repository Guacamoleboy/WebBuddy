// Package
package dk.project.controller.Search;

// Imports
import dk.project.server.ThymeleafSetup;
import dk.project.entity.Website;
import dk.project.mapper.WebsiteMapper;
import dk.project.exception.DatabaseException;
import io.javalin.Javalin;
import java.util.HashMap;
import java.util.Map;
import io.javalin.http.Context;

public class SearchController {

    // Attributes

    // _________________________________________________________________

    public static void registerRoutes(Javalin app) {

        app.get("/api/check", ctx -> checkDomain(ctx));

        // _________________________________________________________________

        app.get("/search", ctx -> {

            String domain = ctx.queryParam("domain");
            WebsiteMapper mapper = new WebsiteMapper();
            Map<String, Object> model = new HashMap<>();

            if (domain == null || domain.isEmpty()) {
                ctx.redirect("/?error=missingFields");
                return;
            }

            try {

                Website website = mapper.getWebsiteByDomain(domain);
                model.put("website", website);

                if (website == null) {
                    ctx.redirect("/?error=domainNotFound");
                    return;
                }

                ctx.html(ThymeleafSetup.render("search.html", model));

            } catch (DatabaseException e) {
                e.printStackTrace();
                ctx.redirect("/?error=dbError");
            }
        });

    }

    // _________________________________________________________________

    private static void checkDomain(Context ctx) {

        String url = ctx.queryParam("url");

        System.out.println(url);

        if (url == null || url.isEmpty()) {
            ctx.status(400).json(Map.of("error", "missing url"));
            return;
        }

        // Normaliser til kun domain
        String domain;
        try {
            domain = new java.net.URL(url).getHost(); // "fog.guacamoleboy.dk"
        } catch (Exception e) {
            ctx.status(400).json(Map.of("error", "invalid url"));
            return;
        }

        System.out.println(domain);

        WebsiteMapper mapper = new WebsiteMapper();
        try {
            Website website = mapper.getWebsiteByDomain(domain); // nu matcher DB
            if (website == null) {
                ctx.status(200).json(Map.of("safe", true));
                return;
            }

            boolean isSafe = website.isSafe();
            ctx.status(200).json(Map.of(
                    "safe", isSafe,
                    "domain", domain,
                    "category", website.getCategory()
            ));
        } catch (DatabaseException e) {
            e.printStackTrace();
            ctx.status(500).json(Map.of("error", "db error"));
        }
    }

}