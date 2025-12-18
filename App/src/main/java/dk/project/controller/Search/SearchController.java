// Package
package dk.project.controller.Search;

// Imports
import dk.project.entity.Category;
import dk.project.mapper.CategoryMapper;
import dk.project.mapper.WebsiteCategoryMapper;
import dk.project.server.ThymeleafSetup;
import dk.project.entity.Website;
import dk.project.mapper.WebsiteMapper;
import dk.project.exception.DatabaseException;
import io.javalin.Javalin;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

        // Initial
        WebsiteMapper websiteMapper = new WebsiteMapper();
        WebsiteCategoryMapper websiteCategoryMapper = new WebsiteCategoryMapper();
        CategoryMapper categoryMapper = new CategoryMapper();
        String url = ctx.queryParam("url");
        String domain;

        // Validation
        if (url == null || url.isEmpty()) {
            ctx.status(400).json(Map.of("error", "missing url"));
            return;
        }

        // Get URL only without param or prefix
        try {
            domain = new java.net.URL(url).getHost();
        } catch (Exception e) {
            ctx.status(400).json(Map.of("error", "invalid url"));
            return;
        }

        // Remove before deployment | DEBUG
        System.out.println(url);
        System.out.println(domain);

        // Extension start
        try {

            Website website = websiteMapper.getWebsiteByDomain(domain);

            // If no website was found -> Safe website (not scanned yet)
            if (website == null) {
                ctx.json(Map.of(
                        "safe", true,
                        "domain", domain
                ));
                return;
            }

            // Get categories pr website / domain
            List<Integer> categoryIds = websiteCategoryMapper.getCategoriesByWebsiteId(website.getId());
            List<String> categoryNames = new ArrayList<>();
            for (int catId : categoryIds) {
                Category cat = categoryMapper.getById(catId);
                if (cat != null) categoryNames.add(cat.getName());
            }

            // Correct formatting of validation
            String lastValidatedFormatted = website.getLastValidated()
                    .format(DateTimeFormatter.ofPattern("MM/dd/yy"));

            // Send information to our extension (blocker.js)
            ctx.json(Map.of(
                    "safe", website.isSafe(),
                    "domain", domain,
                    "categories", categoryNames,
                    "confidence", website.getConfidence(),
                    "lastValidated", lastValidatedFormatted
            ));

        } catch (DatabaseException e) {
            e.printStackTrace();
            ctx.status(500).json(Map.of("error", "db error"));
        }
    }

}