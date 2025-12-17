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

public class SearchController {

    // Attributes

    // _________________________________________________________________

    public static void registerRoutes(Javalin app) {

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

}